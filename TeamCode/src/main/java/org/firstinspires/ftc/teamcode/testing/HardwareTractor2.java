package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.HardwareTractor;
import org.firstinspires.ftc.teamcode.testing.Wheel;

public class HardwareTractor2 extends HardwareTractor {

	public Wheel leftWheel;
	public Wheel rightWheel;

	@Override
	public void init(HardwareMap hardwareMap) {
		super.init(hardwareMap);
		leftWheel = new Wheel(left_drive,"Left", true);
		rightWheel = new Wheel(right_drive, "Right", false);
	}
}
