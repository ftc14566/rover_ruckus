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
    static final double     DRIVE_GEAR_REDUCTION    = 2 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 6.75 ;// For figuring circumference
    static final double     WHEEL_SEPARATION = 15.25 ;
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 0.45;
    static final double     TURN_SPEED              = 0.3;
    public int allianceSel;
    public int pathSel;

    public int allianceSelection(int alliance) {
        while (!opModeIsActive()) {
            if (alliance == 1) {
                telemetry.addData("Select an alliance\n>>>Red\nBlue", "");
                telemetry.update();
            } else if (alliance == 2) {
                telemetry.addData("Select an alliance\nRed\n>>>Blue", "");
                telemetry.update();
            }

            while ((!gamepad1.dpad_down && !gamepad1.dpad_up) && !gamepad1.a) {
                sleep(10);
            }
            if (gamepad1.a) {
                //Break out of loop
                break;
            } else if (gamepad1.dpad_down) {
                alliance = 2;
            } else if (gamepad1.dpad_up) {
                alliance = 1;
            }
        }
        return alliance;
    }

    public int pathSelection(int path) {
        while (!opModeIsActive()) {
            if (path == 1) {
                telemetry.addData("Select a path\n>>>Path A\nPath B\nPath C\nPath D", "");
                telemetry.update();
            } else if (path == 2) {
                telemetry.addData("Select a path\nPath A\n>>>Path B\nPath C\nPath D", "");
                telemetry.update();
            } else if (path == 3) {
                telemetry.addData("Select a path\nPath A\nPath B\n>>>Path C\nPath D", "");
                telemetry.update();
            } else if (path == 4) {
                telemetry.addData("Select a path\nPath A\nPath B\nPath C\n>>>Path D", "");
                telemetry.update();
            }
            while ((!gamepad1.dpad_down && !gamepad1.dpad_up) && !gamepad1.a) {
                sleep(100);
            }
            if(gamepad1.a){
                break;
            } else if(gamepad1.dpad_down && path <4){
                path = path + 1;
            } else if(gamepad1.dpad_up && path > 1){
                path = path - 1;
            }
            sleep(250);
        }
        return path;
    }

    public void preTelemetry(int allianceSel, int pathSel){
        if(allianceSel == 1){
            telemetry.addData("Your alliance is","Red");
        } else if(allianceSel == 2){
            telemetry.addData("Your alliance is","Blue");
        }

        if(pathSel == 1){
            telemetry.addData("Your path is","A");
        } else if(pathSel == 2){
            telemetry.addData("Your path is","B");
        } else if(pathSel == 3){
            telemetry.addData("Your path is","C");
        } else if(pathSel == 4){
            telemetry.addData("Your path is","D");
        }

        telemetry.update();
    }

    public void markerDrop(){
        robot.marker_servo.setPosition(0);
        sleep(1200);
        robotDrive(.7, -90,-90);
    }

    public void Lock(){
        telemetry.addData("locking", "");
        telemetry.update();
        robot.lifter_lock.setPosition(.43);
    }
    public void Unlock(){
        robot.lifter_lock.setPosition(.55);
        telemetry.addData("unlocking",robot.lifter_lock.getPosition());
        telemetry.update();

    }
    public void boostLifter(){
        robot.lifter.setPower(.5);
    }

    public void LowerLifter() {
        telemetry.addData("lowering", "");
        telemetry.update();
        ElapsedTime runtime = new ElapsedTime();
        while (opModeIsActive() && (runtime.seconds() < 4.3)) {
            telemetry.addData("lowering", runtime.seconds());
            telemetry.update();
            sleep(100);
            robot.lifter.setPower(.01);
        }
    }
    public void upLifter(){
        robot.lifter.setPower(0);
        robot.lifter.setPower(-.25);
        sleep(170);
        robot.lifter.setPower(0);
        sleep(1000);
    }

    public void robotLifter(){
        Unlock();
        LowerLifter();
        boostLifter();
        upLifter();
    }

    public void robotDrive(double driveSpeed, double leftInches, double rightInches) {

        //while(opModeIsActive() && gamepad1.a == false){
       //     sleep(100);
      //  }
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

        while(opModeIsActive() && (robot.left_drive.isBusy() || robot.right_drive.isBusy())){
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
        robotDrive(DRIVE_SPEED, inches*2, -inches*2);
    }
    public void robotSteps(int pathSel){
        if(pathSel == 1){
            robotDrive(DRIVE_SPEED,3,3);
            robotTurn(TURN_SPEED,90);
            robotDrive(DRIVE_SPEED, 30,30);
            robotTurn(TURN_SPEED, -45);
            //robotDrive(DRIVE_SPEED, 15,15);
            //robotTurn(DRIVE_SPEED, 45);
            robotDrive(DRIVE_SPEED, 28,28);
            robotDrive(DRIVE_SPEED,-3,-3);
            robotTurn(TURN_SPEED,90);
            robotDrive(DRIVE_SPEED, 17,17);
            markerDrop();
        }
        if (pathSel == 2){
            robotDrive(DRIVE_SPEED,3,3);
            robotTurn(TURN_SPEED,90);
            robotDrive(DRIVE_SPEED,45,45);
            robot.marker_servo.setPosition(0);
            sleep(2000);
            //robotDrive(DRIVE_SPEED,-22,-22);
        }
        if (pathSel == 3){
            robotDrive(DRIVE_SPEED,3,3);
            robotTurn(TURN_SPEED,90);
            robotDrive(DRIVE_SPEED,45,45);
            robot.marker_servo.setPosition(0);
            sleep(2000);
            robotDrive(DRIVE_SPEED,-22,-22);
        }
        if (pathSel == 4){
            robotDrive(DRIVE_SPEED,3,3);
            robotTurn(TURN_SPEED,90);
            sleep(5000);
            robotDrive(DRIVE_SPEED,45,45);
            robot.marker_servo.setPosition(0);
            sleep(2000);
            robotDrive(DRIVE_SPEED,-22,-22);
        }
    }

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        robot = new HardwareTractor();
        robot.init(hardwareMap);
        robot.marker_servo.setPosition(1);
        allianceSel = allianceSelection(1);
        sleep(2000);
        pathSel = pathSelection(1);
        preTelemetry(allianceSel,pathSel);



        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
                robotLifter();
                robotSteps(pathSel);
                while(opModeIsActive() && !gamepad1.a){
                     sleep(100);
                }
        }
    }
}
