package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.HardwareTractor;

@Autonomous(name="AutoLifter", group="Linear Opmode")
//@Disabled
public class AutoLifter extends LinearOpMode {
    private HardwareTractor robot = new HardwareTractor();

    @Override
    public void runOpMode(){
        robot.init(hardwareMap);
        waitForStart();
        //Lifter Lock
        Unlock();
        LowerLifter();
        boostLifter();

        telemetry.addData("all done", "done");
        telemetry.update();


    }

    public void Lock(){
        telemetry.addData("locking", "");
        telemetry.update();
        robot.lifter_lock.setPosition(100);
    }

    public void Unlock(){
        robot.lifter_lock.setPosition(.45);
        telemetry.addData("unlocking",robot.lifter_lock.getPosition());
        telemetry.update();

    }

    public void boostLifter(){
        robot.lifter.setPower(.5);
    }

    public void LowerLifter(){
        telemetry.addData("lowering","");
        telemetry.update();
        ElapsedTime runtime = new ElapsedTime();
        while (opModeIsActive() && (runtime.seconds() < 4.3)){
            telemetry.addData("lowering",runtime.seconds());
            telemetry.update();
            sleep(100);
            robot.lifter.setPower(.01);
        }
        robot.lifter.setPower(0);

        telemetry.addData("lowering", "done");
        telemetry.update();


    }
}
