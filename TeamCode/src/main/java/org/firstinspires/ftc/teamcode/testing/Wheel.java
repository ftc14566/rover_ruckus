package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Hardware;
import org.firstinspires.ftc.robotcore.external.Telemetry;


public class Wheel {

	static final double     COUNTS_PER_MOTOR_REV    = 1440 ;    // eg: TETRIX Motor Encoder
	static final double     DRIVE_GEAR_REDUCTION    = 2.0 ;     // This is < 1.0 if geared UP
	static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
	static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
			(WHEEL_DIAMETER_INCHES * 3.1415);

	public DcMotor _dcMotor;
	private String _label;
	private int _currentTarget;

	public Wheel(DcMotor motor, String label){
		_dcMotor = motor;
		_label = label;
		resetEncoder();
	}

	public void initDrive(double speed, double inches) {
		int newTarget = _dcMotor.getCurrentPosition() + (int) (inches * COUNTS_PER_INCH);
		_dcMotor.setTargetPosition(newTarget);
		_dcMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
		_dcMotor.setPower(Math.abs(speed));
		_currentTarget = newTarget;
	}

	public double getCurrentPosition(){
		return _dcMotor.getCurrentPosition();
	}

	private void resetEncoder() {
		_dcMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		_dcMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
	}

	public void showTelemetry(Telemetry telemetry) {
		double rightAt = _dcMotor.getCurrentPosition();
		telemetry.addData(_label, "At %7d going to:%7d", rightAt, _currentTarget);
	}

	public boolean isBusy(){
		return _dcMotor.isBusy();
	}

	public void stop(){
		_dcMotor.setPower(0);
		_dcMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
	}

}
