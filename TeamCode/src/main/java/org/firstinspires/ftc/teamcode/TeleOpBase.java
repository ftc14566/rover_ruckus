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

import android.text.style.BulletSpan;

//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

/**
 * This file contains an example of an iterative (Non-Linear) "OpMode".
 * An OpMode is a 'program' that runs in either the autonomous or the teleop period of an FTC match.
 * The names of OpModes appear on the menu of the FTC Driver Station.
 * Roses are red, So is the state, Comrade you are great.
 * When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all iterative OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="TeleOpBase", group="Iterative Opmode")
public class TeleOpBase extends OpMode
{
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor left_drive = null;
    private DcMotor right_drive = null;
    private DcMotor lifter = null;
    private DcMotor CollAjust = null;
    private Servo lifter_lock = null;
    private Servo marker_servo = null;

    /*
     * Code to run ONCE when the driver hits INIT
     */

    @Override
    public void init() {
        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        left_drive  = hardwareMap.get(DcMotor.class, "left_drive");
        right_drive = hardwareMap.get(DcMotor.class, "right_drive");
        lifter = hardwareMap.get(DcMotor.class,"lifter");
        CollAjust = hardwareMap.get(DcMotor.class, "collector");
        lifter_lock = hardwareMap.get(Servo.class, "lifter_lock");
        marker_servo = hardwareMap.get(Servo.class, "marker_servo");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        left_drive.setDirection(DcMotor.Direction.FORWARD);
        right_drive.setDirection(DcMotor.Direction.REVERSE);
        lifter.setDirection(DcMotor.Direction.REVERSE);

        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Self Destruction Sequence Activated");
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */

    @Override
    public void loop() {

        move();
        doLifter();
        doCollector();
        lock_lifter();
        dropMarker();
    }

    private void doCollector() {
        float CollAjustraw = gamepad2.right_stick_y;
        float CollAjustbackraw = -gamepad2.right_stick_y;

        double CollAjustData = Range.clip(CollAjustraw + 0, -1, 1);
        double CollAjustBackData = Range.clip(CollAjustbackraw + 0, -1, 1);
        double CollMot = Range.clip(CollAjustData - CollAjustBackData, -.5, .5);

        if(CollMot == 0){
            CollMot = -.2;
        }

        CollAjust.setPower(CollMot);
    }

     private void doLifter() {
         //Lifter To-Do:
         //Get lifter design from builders
         //Write code that operates the lifter
         //Connect the lifter to a button on the controller

         float LifterYes = gamepad2.left_stick_y;
         float LifterOpp = 0;
         double LiftScale = .8;

         double LifterActivate = Range.clip(LifterYes * LiftScale - LifterOpp * LiftScale, -1, 1);

         lifter.setPower(LifterActivate);

     }

     private void lock_lifter() {
         boolean lock = gamepad2.dpad_right;
         boolean unlock = gamepad2.dpad_left;

         if (lock == true) {
             lifter.setPower(.3);
             lifter_lock.setPosition(.43);
         }
         if (unlock == true) {
             lifter_lock.setPosition(.55);
         }
     }

     private void dropMarker(){
        boolean markerUp = gamepad2.dpad_up;
        boolean markerDown = gamepad2.dpad_down;

        if(markerUp == true){
            marker_servo.setPosition(100);
        }
        if(markerDown == true){
            marker_servo.setPosition(0);
        }
     }

    private void move() {
        double scale = .5; //Sets scale to .5

        double Drive = gamepad1.left_stick_y; //Sets Drive to how far the left joystick is pushed on the Y axis
        double Turn = -gamepad1.left_stick_x; //Sets Turn to how far the left joystick is pushed on the X axis
        double speedMode = gamepad1.right_trigger;

        if(speedMode > .0000000001){
            scale = 1;
        }

        double LeftMotPower = Range.clip(Drive + Turn, -1, 1) * scale; //Keeps the motor power inside -1 and 1
        double RightMotPower = Range.clip(Drive - Turn, -1, 1) * scale; //Keeps motor power inside -1 and 1


        left_drive.setPower(LeftMotPower); //Renames variables to work with RC phone's config
        right_drive.setPower(RightMotPower); //Renames variables to work with RC phone's config
    }
    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
