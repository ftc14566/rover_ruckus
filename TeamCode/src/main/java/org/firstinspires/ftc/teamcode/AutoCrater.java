package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="AutoCrater", group="Linear Opmode")
public class AutoCrater extends AutoDepot {
    @Override
    public void robotSteps(){
        pathTwo();
    }
    protected void pathTwo() {
        robotDrive(.2,2,2);
        robotTurn(.3,45);
        //robotDrive(DRIVE_SPEED, 30,30);
        //robotTurn(TURN_SPEED, -45);
        //robotDrive(DRIVE_SPEED, 15,15);
        //robotTurn(DRIVE_SPEED, 45);
        robotDrive(DRIVE_SPEED, 45,45);
        //robotDrive(DRIVE_SPEED,-3,-3);
        robotTurn(TURN_SPEED,-90);
        robotDrive(DRIVE_SPEED,55,55);
        markerDrop();
    }
}
