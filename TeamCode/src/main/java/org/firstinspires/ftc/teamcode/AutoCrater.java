package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="AutoCrater", group="Linear Opmode")
public class AutoCrater extends AutoDepot {

    @Override
    public void robotSteps(){
        robotDrive(.2,2,2,3,"Geting off lander");
        robotTurn(.3,43,1.5,"Turning towards wall");

        robotDrive(DRIVE_SPEED, 38, 38,10,"\"Going to wall\"");

        robotTurn(TURN_SPEED,-85 ,3,"\"Turning Left to Face Deopo\"");

        robotDrive(DRIVE_SPEED,50,50,10,"\"Going to Depot\"");

        Show("Droping Marker");
        markerDropArm();

        robotDrive(.3,-1.5,1.5,5,"\"Lining up\"");

        robotDrive(.7, -60, -60, 10,"\"Backing into Crator\"");

    }
}
