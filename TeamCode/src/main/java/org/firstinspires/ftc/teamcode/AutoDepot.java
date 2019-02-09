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
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


// This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 // the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 // of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 // class is instantiated on the Robot Controller and executed.


@Autonomous(name="AutoDepot", group="Linear Opmode")
//Disabled
public class AutoDepot extends LinearOpMode {

    protected HardwareTractor robot;
    private ElapsedTime runtime = new ElapsedTime();

    static final double     COUNTS_PER_MOTOR_REV    = 288 ;
    static final double     DRIVE_GEAR_REDUCTION    = 2 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 6.75 ;// For figuring circumference
    static final double     WHEEL_SEPARATION = 15.25 ;
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 0.45;
    static final double     TURN_SPEED              = 0.3;
    static final double     TIME_PER_INCH           = 0.5;
    int pathSel = 1;
    public double leftAbort;
    public double rightAbort;
    public double abortTime;


    public void markerDropArm(){
        robot.collector.setPower(-1);
        sleep(50);
        robot.collector.setPower(-.2);
        sleep(100);
        robot.collector.setPower(.5);
        sleep(500);
        //Does it work?
        robot.collector.setPower(-.5);
        sleep(300);
        robot.collector.setPower(-.2);
    }

    public void markerDrop(){
        robot.marker_servo.setPosition(4.5);
        sleep(1200);
        robot.marker_servo.setPosition(.5);
        robotDrive(.75, -73,-73,0);
    }

    public void Lock(){
        telemetry.addData("locking", "");
        telemetry.update();
        robot.lifter_lock.setPosition(.43);
    }
    public void unlock(){
        robot.lifter_lock.setPosition(.56);
        telemetry.addData("unlocking",robot.lifter_lock.getPosition());
        telemetry.update();

    }


    public void LowerRobot() {
        robot.lifter.setPower(.01);
        unlock();
        telemetry.addData("lowering", "");
        telemetry.update();
        ElapsedTime runtime = new ElapsedTime();
        while (opModeIsActive() && (runtime.seconds() < 4.3)) {
            telemetry.addData("lowering", runtime.seconds());
            telemetry.update();
            sleep(100);
        }
    }
    public void upLifter(){
        robot.lifter.setPower(-.25);
        sleep(170);
        robot.lifter.setPower(0);
        }

    public void robotLifter(){
        LowerRobot();
        upLifter();
    }

    public void robotDrive(double driveSpeed, double leftInches, double rightInches, double timeout) {

        //if(timeout == 0){
         //   leftAbort = (leftInches/driveSpeed)*TIME_PER_INCH;
          //  rightAbort = (rightInches/driveSpeed)*TIME_PER_INCH;
       // } else {
        //    leftAbort = timeout;
        //    rightAbort = timeout;
        //}

        robot.left_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.right_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.left_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.right_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        int newLeftTarget = robot.left_drive.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
        int newRightTarget = robot.right_drive.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
         robot.left_drive.setTargetPosition(newLeftTarget);
        robot.right_drive.setTargetPosition(newRightTarget);

        robot.left_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.right_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.left_drive.setPower(Math.abs(driveSpeed));
        robot.right_drive.setPower(Math.abs(driveSpeed));

        abortTime = getRuntime();
        while(opModeIsActive() && (robot.left_drive.isBusy() || robot.right_drive.isBusy())) {
            sleep(100);
        }

        robot.left_drive.setPower(0);
        robot.right_drive.setPower(0);
        //sleep(0020);
        robot.left_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.right_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void robotTurn(double DRIVE_SPEED,double rightDegrees ){
        double inches = (rightDegrees * WHEEL_SEPARATION/2 * 3.1415926 / 180)/2;
        robotDrive(DRIVE_SPEED, inches*2, -inches*2,0);
    }

    private void pathFour() {
        robotDrive(DRIVE_SPEED,3,3,0);
        robotTurn(TURN_SPEED,90);
        sleep(5000);
        robotDrive(DRIVE_SPEED,45,45,0);
        robot.marker_servo.setPosition(0);
        sleep(2000);
        robotDrive(DRIVE_SPEED,-22,-22,0);
    }

    private void pathThree() {
        robotDrive(DRIVE_SPEED,3,3,0);
        robotTurn(TURN_SPEED,90);
        robotDrive(DRIVE_SPEED,45,45,0);
        robot.marker_servo.setPosition(0);
        sleep(2000);
        robotDrive(DRIVE_SPEED,-22,-22,0);
    }



    public void robotSteps() {
        robotDrive(.2,2,2,5);
        robotTurn(.3,45);
        robotDrive(DRIVE_SPEED, 38,38,10);
        robotTurn(TURN_SPEED,80);
        robotDrive(DRIVE_SPEED, 15,15,5);

        markerDropArm();
        //marker dropped

        //Spin
        robotDrive(.3,3,-3,0);

        //Drive
        robotDrive(.7, -60,-60,0);

    }

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        robot = new HardwareTractor();
        robot.init(hardwareMap);
        robot.marker_servo.setPosition(.5);





        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        robotLifter();
        robotSteps();
        //keep rover on creator
        while(opModeIsActive()){
            robot.left_drive.setPower(-.4);
            robot.left_drive.setPower(-.4);
            sleep(100);
        }
    }
}
