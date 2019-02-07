package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="AutoCrater", group="Linear Opmode")
public class AutoCrater extends AutoDepot {
    @Override
    public void robotSteps(){

        pathTwo();
    }
    protected void pathTwo() {
        robotDrive(.2,2,2,0);
        robotTurn(.3,43);
        //robotDrive(DRIVE_SPEED, 30,30);
        //robotTurn(TURN_SPEED, -45);
        //robotDrive(DRIVE_SPEED, 15,15);
        //robotTurn(DRIVE_SPEED, 45);
        robotDrive(DRIVE_SPEED, 38, 38,0);
        //robotDrive(DRIVE_SPEED,-5,-5);
        robotTurn(TURN_SPEED,-85 );
        robotDrive(DRIVE_SPEED,50,50,0);
        //robotTurn(.3, 40);

        markerDropArm(2);
    }
}
