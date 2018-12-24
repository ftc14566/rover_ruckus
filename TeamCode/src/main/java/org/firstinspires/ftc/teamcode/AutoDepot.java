/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.testing.Wheel;


// This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 // the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 // of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 // class is instantiated on the Robot Controller and executed.


@Autonomous(name="AutoDepot", group="Linear Opmode")
//Disabled
public class AutoDepot extends LinearOpMode {

    private HardwareTractor robot;
    private ElapsedTime runtime = new ElapsedTime();

    static final double     COUNTS_PER_MOTOR_REV    = 288 ;
    static final double     DRIVE_GEAR_REDUCTION    = 2.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 6.75 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 0.3;
    static final double     TURN_SPEED              = 0.3;

    static final double INCREMENT   = 0.01;     // amount to slew servo each CYCLE_MS cycle
    static final int    CYCLE_MS    =   50;     // period of each cycle
    static final double MAX_POS     =  1.0;     // Maximum rotational position
    static final double MIN_POS     =  0.0;     // Minimum rotational position
    private Servo lifter_lock;
    private Servo marker_servo;
    double  position = (MAX_POS - MIN_POS) / 2; // Start at halfway position
    boolean rampUp = true;

    public void robotDrive(double driveSpeed, double leftInches, double rightInches) {

        int  leftCounts = (int) (leftInches * COUNTS_PER_INCH);
        int rightCounts = (int)(rightInches * COUNTS_PER_INCH);

        driveCounts(driveSpeed, leftCounts, rightCounts);
    }

    public void robotTurn2(double turnSpeed, double degreesRight) {

        int countsForFullRotation = 682;
        int counts = (int)(degreesRight * countsForFullRotation / 360 ) ;

        driveCounts(turnSpeed, counts, -counts);
    }

    private void driveCounts(double driveSpeed, int deltaLeftCounts, int deltaRightCounts) {
        robot.left_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.right_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        int newLeftTarget = robot.left_drive.getCurrentPosition() + deltaLeftCounts;
        int newRightTarget = robot.right_drive.getCurrentPosition() + deltaRightCounts;
        robot.left_drive.setTargetPosition(newLeftTarget);
        robot.right_drive.setTargetPosition(newRightTarget);

        robot.left_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.right_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.left_drive.setPower(Math.abs(driveSpeed));
        robot.right_drive.setPower(Math.abs(driveSpeed));

        while(robot.left_drive.isBusy() || robot.right_drive.isBusy()){}
        robot.left_drive.setPower(0);
        robot.right_drive.setPower(0);
        sleep(20);
        robot.left_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.right_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        while( opModeIsActive() && !gamepad1.a){
            sleep(100);
        }
    }


    void robotTurn( double degrees ){
        double WheelSeparation = 15.0;
        double inches = degrees * WheelSeparation/2 * 3.1415926 / 180;
        robotDrive( 0.5, inches, -inches );
    }

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        robot = new HardwareTractor();
        robot.init(hardwareMap);
        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery


        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();
        String robotState = "dropping";


        double locked = 1;
        double unlocked = 0.75;

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            if (robotState == "dropping") {
                //robot.lifter_lock.setPosition(unlocked);
                //sleep(750);
                //robot.lifter.setPower(.3);
               // sleep(3000);
                //robot.lifter.setPower(0);
                robotState = "detach";
            }

            if (robotState == "detach") {
                robotDrive(DRIVE_SPEED,3,3);
                robotDrive(DRIVE_SPEED,12,-12);

                //robotState = "hitWall";
            }

            if (robotState == "hitWall") {
                robotDrive(DRIVE_SPEED, 15,15);
                robotDrive(DRIVE_SPEED, -12,12);
                robotDrive(DRIVE_SPEED, 15,15);
                robotDrive(DRIVE_SPEED, 12,-12);
                robotDrive(DRIVE_SPEED, 20,20);
                robotDrive(DRIVE_SPEED,6,-6);
                robotDrive(DRIVE_SPEED, 18,18);
                robotState = "driveDepot";

            }

            if (robotState == "driveDeot") {
                robotDrive(DRIVE_SPEED, -6,-6);
                robotDrive(DRIVE_SPEED, 12,-12);
                robotDrive(DRIVE_SPEED, 25,25);
                //robotDrive(DRIVE_SPEED, 36,36);

                robotState = "driveCrater";
            }

            if (robotState == "driveCrater") {
                robotDrive(DRIVE_SPEED, -90,-90);
                robotState = "complete";
            }


        }
    }
}
