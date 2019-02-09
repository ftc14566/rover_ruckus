package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="AutoCrater", group="Linear Opmode")
public class AutoCrater extends AutoDepot {

    @Override
    public void robotSteps(){
        // path 2
        robotDrive(.2,2,2,3);
        robotTurn(.3,43);

        robotDrive(DRIVE_SPEED, 38, 38,10);
        robotTurn(TURN_SPEED,-85 );
        robotDrive(DRIVE_SPEED,50,50,10);

        markerDropArm();

        robotDrive(.3,-1.5,1.5,5);
        robotDrive(.7, -60, -60, 10);

    }
}
