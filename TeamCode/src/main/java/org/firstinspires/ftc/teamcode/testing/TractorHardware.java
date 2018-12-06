package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

// All the hardware on our Tractor-bot
class TractorHardware {
	public Wheel leftWheel;
	public Wheel rightWheel;

	public void init(HardwareMap harwareMap){
		leftWheel = new Wheel(harwareMap.get(DcMotor.class, "left_drive"),"Left");
		rightWheel = new Wheel(harwareMap.get(DcMotor.class, "right_drive"), "Right");
	}
}
