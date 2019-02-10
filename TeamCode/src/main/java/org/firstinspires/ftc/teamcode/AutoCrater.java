package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="AutoCrater", group="Linear Opmode")
public class AutoCrater extends AutoDepot {

    @Override
    public void robotSteps(){
        Show("Geting off lander");
        robotDrive(.2,2,2,3);
        robotTurn(.3,43,1.5);

        Show("Going to wall");
        robotDrive(DRIVE_SPEED, 38, 38,10);

        Show("Turning Left to Face Deopo");
        robotTurn(TURN_SPEED,-85 ,3);

        Show("Going to Depot");
        robotDrive(DRIVE_SPEED,50,50,10);

        Show("Droping Marker");
        markerDropArm();

        Show("Lining up");
        robotDrive(.3,-1.5,1.5,5);

        Show("Backing into Crator");
        robotDrive(.7, -60, -60, 10);

    }
}
