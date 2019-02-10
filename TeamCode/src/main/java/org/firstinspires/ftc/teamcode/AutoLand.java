package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="AutoLand", group="Linear Opmode")
public class AutoLand extends AutoDepot {
    @Override
    public void robotSteps(){

        pathFive();
    }
    protected void pathFive() {
        robotDrive(.2,2,2,0);
        robotTurn(.3,43,1.5);
        robotDrive(DRIVE_SPEED, 10, 10,0);

        while(opModeIsActive()){
            robot.left_drive.setPower(0);
            robot.left_drive.setPower(0);
            sleep(100);

        }
    }
}
