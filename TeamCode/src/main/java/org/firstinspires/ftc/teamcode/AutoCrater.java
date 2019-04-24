package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="AutoCrater", group="Linear Opmode")
public class AutoCrater extends AutoDepot {
    @Override
    public void robotSteps(){

        pathTwo();
    }
    protected void pathTwo() {
        robot.robotDrive(this, .2, 2, 2, 0);
        robot.robotTurn(this, .3,43);
        robot.robotDrive(this, DRIVE_SPEED, 38, 38,0);
        robot.robotTurn(this, TURN_SPEED,-85 );
        robot.robotDrive(this, DRIVE_SPEED, 50, 50, 0);

        markerDropArm(2);
    }
}
