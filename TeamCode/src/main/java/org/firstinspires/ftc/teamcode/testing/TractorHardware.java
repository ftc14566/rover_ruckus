package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ColorSensor;


// All the hardware on our Tractor-bot
class TractorHardware {
	public Wheel leftWheel;
	public Wheel rightWheel;
	public DualPositionServo markerServo;
	public DualPositionServo pinServo;
	public DcMotor lifter;
	public DcMotor collector;
	public ColorSensor leftEye;
	public ColorSensor rightEye;

	public void init(HardwareMap hardwareMap){

		leftWheel = new Wheel(hardwareMap.get(DcMotor.class, "left_drive"),"Left", true);
		rightWheel = new Wheel(hardwareMap.get(DcMotor.class, "right_drive"), "Right", false);

		lifter = hardwareMap.get(DcMotor.class, "lifter");
		collector = hardwareMap.get(DcMotor.class, "collector");

		markerServo = new DualPositionServo(hardwareMap.get(Servo.class, "token"),0.0, 90);
		pinServo = new DualPositionServo( hardwareMap.get(Servo.class, "latch"), 90, 180);

		leftEye = hardwareMap.get(ColorSensor.class,"left_eye");
		rightEye = hardwareMap.get(ColorSensor.class, "right_eye");
	}
}
