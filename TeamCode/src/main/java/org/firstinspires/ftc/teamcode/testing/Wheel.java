package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public class Wheel {

	static final double     COUNTS_PER_MOTOR_REV    = 288 ;    // eg: TETRIX Motor Encoder
	static final double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // This is < 1.0 if geared UP
	static final double     WHEEL_DIAMETER_INCHES   = 6.75 ;     // For figuring circumference

	static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
			(WHEEL_DIAMETER_INCHES * 3.1415);

	public DcMotor _dcMotor;
	private String _label;
	private int _target;
	private int _startPos;

	public Wheel(DcMotor motor, String label){
		_dcMotor = motor;
		_label = label;
	}

	public double getCurrentPosition(){
		return _dcMotor.getCurrentPosition();
	}

	public boolean isBusy(){
		return _dcMotor.isBusy();
	}

	public void startDriving(double speed, double inches) {
		_dcMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

		_startPos = _dcMotor.getCurrentPosition();
		_target = _startPos + (int) (inches * COUNTS_PER_INCH);

		_dcMotor.setTargetPosition(_target);
		_dcMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
		_dcMotor.setPower(Math.abs(speed));
	}

	public void stop(){
		_dcMotor.setPower(0);
		_dcMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
	}

	public void showTelemetry(Telemetry telemetry) {
		int current = _dcMotor.getCurrentPosition();
		telemetry.addData(_label, "%d => %d => %d", _startPos, current, _target);
	}

	// Sets power, auto clipping between -1 and 1
	public void setPower(double power) {
		_dcMotor.setPower( Range.clip(power, -1, 1) );
	}


}
