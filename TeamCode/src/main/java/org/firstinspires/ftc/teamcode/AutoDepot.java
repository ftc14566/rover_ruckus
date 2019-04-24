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

import org.firstinspires.ftc.robotcore.external.Telemetry;



@Autonomous(name="AutoDepot", group="Linear Opmode")
//Disabled
public class AutoDepot extends LinearOpMode {

    protected HardwareTractor robot;
    private ElapsedTime runtime = new ElapsedTime();

    static final double     COUNTS_PER_MOTOR_REV    = 288 ;
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


    public void markerDropArm(double robotPath){
        robot.collector.setPower(-1);
        sleep(50);
        robot.collector.setPower(-.2);
        sleep(100);
        robot.collector.setPower(.5);
        sleep(2000);
        robot.collector.setPower(-.5);
        sleep(300);
        robot.collector.setPower(-.2);
        if(robotPath == 1){
            //robotTurn(.5, 15);
            robot.robotDrive(this, .3,3,-3,0);
            robot.robotDrive(this, .7, -60,-60,0);
           // robotTurn(.3, -5);
            //robotDrive(.7, -20,-20,0);
        }
        if(robotPath == 2) {
            robot.robotDrive(this, .3,-1.5,1.5,0);
            //robotTurn(.5, -12);
            robot.robotDrive(this, .7, -60, -60, 0);
            //robotTurn(.3, 5);
            //robotDrive(.7, -20, -20, 0);
        }
    }

    public void markerDrop(){
        robot.marker_servo.setPosition(4.5);
        sleep(1200);
        robot.marker_servo.setPosition(.5);
        robot.robotDrive(this, .75, -73,-73,0);
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
        //Lock();
    }

    public void robotSteps(){

      pathOne();

    }

    private void pathOne() {
        robotDrive(.2,2,2,0);
        robot.robotTurn(this, .3,45);
        robot.robotDrive(this, DRIVE_SPEED, 38,38,0);
        robot.robotTurn(this, TURN_SPEED,80);
        robot.robotDrive(this, DRIVE_SPEED, 15,15,0);
        markerDropArm(1);
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
        while(opModeIsActive()){
            robot.left_drive.setPower(-.4);
            robot.left_drive.setPower(-.4);
            sleep(100);
        }
    }
}
