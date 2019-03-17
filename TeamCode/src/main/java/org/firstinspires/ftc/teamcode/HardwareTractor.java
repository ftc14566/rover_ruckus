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

//	public ColorSensor left_eye;
//	public ColorSensor right_eye;

	public void init(HardwareMap hardwareMap){

		left_drive = hardwareMap.get(DcMotor.class, "left_drive");
		right_drive = hardwareMap.get(DcMotor.class, "right_drive");

		left_drive.setDirection(DcMotor.Direction.REVERSE);

		lifter = hardwareMap.get(DcMotor.class, "lifter");
		lifter.setDirection(DcMotor.Direction.REVERSE); // so positive power used to 'lift'

		collector = hardwareMap.get(DcMotor.class, "collector");

		marker_servo = hardwareMap.get(Servo.class, "marker_servo");
		lifter_lock = hardwareMap.get(Servo.class, "lifter_lock");

	//	left_eye = hardwareMap.get(ColorSensor.class,"left_eye");
	//	right_eye = hardwareMap.get(ColorSensor.class, "right_eye");

	}
	static final double lockposition = 0.43;
	static final double UnlockPosition = 0.56;

	public void UnlockLiftrer() {
		lifter_lock.setPosition(UnlockPosition);
	}

	public void LockLifter() {
		lifter_lock.setPosition(lockposition);
	}
	public void Lift() {
		lifter.setPower(0.5);
	}
	public void Lower() {
		lifter.setPower(LoweringPower );

	}
	public void LifterOff() {
		lifter.setPower(0);
	}
	public void ExtendLifter() {
		lifter.setPower(-.2);
	}


	static final double LoweringPower = 0.02;


}