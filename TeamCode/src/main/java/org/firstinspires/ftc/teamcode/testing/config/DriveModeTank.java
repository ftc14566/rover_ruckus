package org.firstinspires.ftc.teamcode.testing.config;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import org.firstinspires.ftc.teamcode.testing.Wheel;

public class DriveModeTank extends DriveMode {

	private double _scale;


	public DriveModeTank(DcMotor.RunMode runMode,double scale){
		super(runMode);
		_scale = scale;
	}

	@Override
	public void drive(Gamepad gamepad1, Wheel leftWheel, Wheel rightWheel) {
		double scale = gamepad1.right_trigger < .01 ? _scale : 1;
		leftWheel.setPower( -gamepad1.left_stick_y * scale );
		rightWheel.setPower( -gamepad1.right_stick_y * scale );
	}

}
