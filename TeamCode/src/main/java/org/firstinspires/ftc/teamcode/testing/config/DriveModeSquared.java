package org.firstinspires.ftc.teamcode.testing.config;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import org.firstinspires.ftc.teamcode.testing.Wheel;

public class DriveModeSquared extends DriveMode {

	private double _scale;

	public DriveModeSquared(DcMotor.RunMode runMode, double scale){
		super(runMode);
		_scale = scale;
	}

	@Override
	public void drive(Gamepad gamepad1, Wheel leftWheel, Wheel rightWheel) {
		double scale = gamepad1.right_trigger < .01 ? _scale : 1;
		leftWheel.setPower( square(-gamepad1.left_stick_y*scale) );
		rightWheel.setPower( square(-gamepad1.right_stick_y*scale) );
	}

	double square(double d){
		return d*(d<0?-d:d);
	}

}
