package org.firstinspires.ftc.teamcode.testing.config;

import com.qualcomm.robotcore.hardware.*;
import org.firstinspires.ftc.teamcode.testing.Wheel;

public class DriveModeHelicopter extends DriveMode {

	private double _scale;

	public DriveModeHelicopter(DcMotor.RunMode runMode, double scale){
		super( runMode );
		_scale = scale;
	}

	@Override
	public void drive(Gamepad gamepad1, Wheel leftWheel, Wheel rightWheel) {
		double drive = -gamepad1.left_stick_y;
		double turn = gamepad1.right_stick_x;
		double scale = gamepad1.right_trigger > .00001 ? 1 : _scale;
		leftWheel.setPower( (drive + turn) * scale );
		rightWheel.setPower( (drive - turn) * scale );
	}
}
