/**
//package org.firstinspires.ftc.teamcode;
//import com.qualcomm.robotcore.eventloop.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Robot_Init {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor lifter = null;
    private DcMotor CollAdjust = null;

    public void data() {
        leftDrive  = hardwareMap.get(DcMotor .class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        lifter = hardwareMap.get(DcMotor.class,"lifter");
        CollAdjust = hardwareMap.get(DcMotor.class, "collector");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
    }

    class robot {

        String name;

        public void addTelemetry() {

        }

    }


}
**/