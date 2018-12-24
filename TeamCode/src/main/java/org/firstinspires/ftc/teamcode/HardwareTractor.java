package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.*;

// All the hardware on our Tractor-bot
public class HardwareTractor {

	public DcMotor left_drive;
	public DcMotor right_drive;

	public Servo marker_servo;
	public Servo lifter_lock;

	public DcMotor lifter;
	public DcMotor collector;

	public ColorSensor left_eye;
	public ColorSensor right_eye;

	public void init(HardwareMap hardwareMap){

		left_drive = hardwareMap.get(DcMotor.class, "left_drive");
		right_drive = hardwareMap.get(DcMotor.class, "right_drive");

		left_drive.setDirection(DcMotor.Direction.REVERSE);

		lifter = hardwareMap.get(DcMotor.class, "lifter");
		lifter.setDirection(DcMotor.Direction.REVERSE); // so positive power used to 'lift'

		collector = hardwareMap.get(DcMotor.class, "collector");

		marker_servo = hardwareMap.get(Servo.class, "token");
		lifter_lock = hardwareMap.get(Servo.class, "lifter_lock");

		left_eye = hardwareMap.get(ColorSensor.class,"left_eye");
		right_eye = hardwareMap.get(ColorSensor.class, "right_eye");

	}

}

