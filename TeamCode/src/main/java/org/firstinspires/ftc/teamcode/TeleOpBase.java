package org.firstinspires.ftc.teamcode;

import android.text.style.BulletSpan;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name="TeleOpBase", group="Iterative Opmode")
public class TeleOpBase extends OpMode
{

    private ElapsedTime runtime = new ElapsedTime();

    private  HardwareTractor hardware;

    @Override
    public void init() {
        HardwareTractor hardware = new HardwareTractor();
        hardware.init (hardwareMap);



        telemetry.addData("Status", "Self Destruction Sequence Activated");
    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
        runtime.reset();
    }

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

        hardware.collector.setPower(CollMot);
    }

     private void doLifter() {

         float LifterYes = gamepad2.left_stick_y;
         float LifterOpp = 0;
         double LiftScale = .8;

         double LifterActivate = Range.clip(LifterYes * LiftScale - LifterOpp * LiftScale, -1, 1);

         hardware.lifter.setPower(LifterActivate);

     }

     private void lock_lifter() {



         if (gamepad2.dpad_right) {
             hardware.lifter.setPower(.3);
             LockLifter();
         }

        else if (gamepad2.dpad_left) {
             UnlockLifter();
         }
     }

    private void UnlockLifter() {
        hardware.lifter_lock.setPosition(.56);
    }

    private void LockLifter() {
        hardware.lifter_lock.setPosition(.43);
    }

    private void dropMarker(){

        if(gamepad2.dpad_up){
            hardware.RaisServoArm();
        }
       else if(gamepad2.dpad_down){
            hardware.DropArmDown();
        }
     }

    private void DropArmDown() {
        hardware.marker_servo.setPosition(0);
    }

    private void RaisServoArm() {
        hardware.marker_servo.setPosition(100);
    }

    private void move() {
        double scale = .5;

        double Drive = gamepad1.left_stick_y; //Sets Drive to how far the left joystick is pushed on the Y axis
        double Turn = -gamepad1.left_stick_x; //Sets Turn to how far the left joystick is pushed on the X axis
        double speedMode = gamepad1.right_trigger;

        if(speedMode > .0000000001){
            scale = 1;
        }

        double LeftMotPower = Range.clip(Drive + Turn, -1, 1) * scale;
        double RightMotPower = Range.clip(Drive - Turn, -1, 1) * scale;


        hardware.left_drive.setPower(-LeftMotPower);
        hardware.right_drive.setPower(-RightMotPower);
    }

    @Override
    public void stop() {
    }

}
