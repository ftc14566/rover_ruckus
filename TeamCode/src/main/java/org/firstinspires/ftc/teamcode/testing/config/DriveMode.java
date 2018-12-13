package org.firstinspires.ftc.teamcode.testing.config;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.testing.Wheel;

abstract public class DriveMode {

	private DcMotor.RunMode runMode;

	public DriveMode(DcMotor.RunMode runMode ){
		this.runMode = runMode;
	}

	public void init(Wheel leftWheel, Wheel rightWheel){
		leftWheel._dcMotor.setMode( runMode );
		rightWheel._dcMotor.setMode( runMode );
	}

	abstract public void drive(Gamepad gamepad1, Wheel leftWheel, Wheel rightWheel);

}
