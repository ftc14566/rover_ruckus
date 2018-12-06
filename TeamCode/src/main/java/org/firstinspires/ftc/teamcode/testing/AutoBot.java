package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

// Used for autonomous mode
// provides simple move/turn commands for the robot
public class AutoBot extends TractorHardware {

	public LinearOpMode OpMode;

	public double     DRIVE_SPEED             = 0.6;
	public double     TURN_SPEED              = 0.5;
	final double wheelSeparation = 13/2;

	public void travel(double inches, double timeoutS){
		encoderDrive(DRIVE_SPEED, inches, inches, timeoutS);
	}

	public void turn(double degreesClockwise, double timeoutS){
		double inches = degreesClockwise * (wheelSeparation/2) * 3.1415926 / 180;
		encoderDrive(TURN_SPEED, inches, -inches, timeoutS);
	}

	public void encoderDrive(double speed, double leftInches, double rightInches, double timeoutS) {

		if (!OpMode.opModeIsActive()) { return; }

		// Determine new target position, and pass to motor controller
		leftWheel.initDrive(speed, leftInches);
		rightWheel.initDrive(speed, rightInches);

		doWheelsUntilDone(timeoutS);

	}

	public void arc(double speed, double leftInches, double rightInches, double timeoutS) {

		if (!OpMode.opModeIsActive()) { return; }

		double leftMag = Math.abs(leftInches);
		double rightMag = Math.abs(rightInches);
		double averageMag = (leftMag + rightMag)/2;
		leftWheel.initDrive(speed * leftMag/averageMag, leftInches);
		rightWheel.initDrive(speed* rightMag/averageMag, rightInches);

		doWheelsUntilDone(timeoutS);

	}


	private void doWheelsUntilDone(double timeoutS) {
		ElapsedTime runtime = new ElapsedTime();
		while (OpMode.opModeIsActive() && (runtime.seconds() < timeoutS)
				&& (leftWheel.isBusy() || rightWheel.isBusy())) {
			leftWheel.showTelemetry(OpMode.telemetry);
			rightWheel.showTelemetry(OpMode.telemetry);
			OpMode.telemetry.update();
		}

		leftWheel.stop();
		rightWheel.stop();
	}

	// =============================================================================
	// time
	// =============================================================================

	public void DriveForTime(double power, double durationS ){
		ArcForTime( power, power, durationS );
	}

	public void TurnRightForTime(double power, double durationS ){
		ArcForTime( power, -power, durationS );
	}

	public void ArcForTime(double leftPower, double rightPower, double durationS){
		leftWheel._dcMotor.setPower(leftPower);
		rightWheel._dcMotor.setPower(rightPower);
		ElapsedTime runtime = new ElapsedTime();
		while (OpMode.opModeIsActive() && (runtime.seconds() < durationS)) {
			OpMode.telemetry.addData("Path", "%2.5f S Elapsed", runtime.seconds());
			OpMode.telemetry.update();
		}
	}

}

