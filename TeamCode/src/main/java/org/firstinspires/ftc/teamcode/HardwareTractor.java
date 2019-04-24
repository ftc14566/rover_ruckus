package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.eventloop.opmode.*;
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

	static final double     COUNTS_PER_MOTOR_REV    = 288 ;
	static final double     WHEEL_DIAMETER_INCHES   = 6.75 ;// For figuring circumference
	static final double     WHEEL_SEPARATION = 15.25 ;
	static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV);
	public void init(HardwareMap hardwareMap) {

		left_drive = hardwareMap.get(DcMotor.class, "left_drive");
		right_drive = hardwareMap.get(DcMotor.class, "right_drive");

		left_drive.setDirection(DcMotor.Direction.REVERSE);

		lifter = hardwareMap.get(DcMotor.class, "lifter");
		lifter.setDirection(DcMotor.Direction.REVERSE); // so positive power used to 'lift'

		collector = hardwareMap.get(DcMotor.class, "collector");

		marker_servo = hardwareMap.get(Servo.class, "marker_servo");
		lifter_lock = hardwareMap.get(Servo.class, "lifter_lock");

		left_eye = hardwareMap.get(ColorSensor.class, "left_eye");
		right_eye = hardwareMap.get(ColorSensor.class, "right_eye");
	}

		public void DropArmDown() {
			marker_servo.setPosition(0);
		}

		public void RaisServoArm () {
			marker_servo.setPosition(100);
		}



	public void UnlockLifter() {
		lifter_lock.setPosition(.56);
	}

	public void LockLifter() {
		lifter_lock.setPosition(.43);
	}
	public void robotDrive(LinearOpMode linearOpMode, double driveSpeed, double leftInches, double rightInches, double timeout) {

		//if(timeout == 0){
		//   leftAbort = (leftInches/driveSpeed)*TIME_PER_INCH;
		//  rightAbort = (rightInches/driveSpeed)*TIME_PER_INCH;
		// } else {
		//    leftAbort = timeout;
		//    rightAbort = timeout;
		//}

		left_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		right_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

		left_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
		right_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

		int newLeftTarget = left_drive.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
		int newRightTarget = right_drive.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
		left_drive.setTargetPosition(newLeftTarget);
		right_drive.setTargetPosition(newRightTarget);

		left_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
		right_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

		left_drive.setPower(Math.abs(driveSpeed));
		right_drive.setPower(Math.abs(driveSpeed));

		double abortTime = linearOpMode.getRuntime();
		while(linearOpMode.opModeIsActive() && (left_drive.isBusy() || right_drive.isBusy())) {
			linearOpMode.sleep(100);
		}

		left_drive.setPower(0);
		right_drive.setPower(0);
		//sleep(0020);
		left_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
		right_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
	}
	public void robotTurn(LinearOpMode linearOpMode, double DRIVE_SPEED,double rightDegrees ){
		double inches = (rightDegrees * WHEEL_SEPARATION/2 * 3.1415926 / 180)/2;
		robotDrive(linearOpMode,DRIVE_SPEED, inches*2, -inches*2,0);
	}

}

