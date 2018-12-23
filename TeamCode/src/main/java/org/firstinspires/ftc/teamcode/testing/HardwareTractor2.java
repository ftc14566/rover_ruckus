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
		leftWheel = new Wheel(left_drive,"Left");
		rightWheel = new Wheel(right_drive, "Right");
	}

	void supportLifterDrop(){
		lifter.setPower(0.01); // 1%
	}

	void removeLifterLock(){
		lifter_lock.setPosition(0.45);
	}

	void engateLifterLock(){
		lifter_lock.setPosition(0.6);
	}

	void extendLifter(){
		lifter.setPower(-0.10);
		// !!! wait 0.5 seconds
	}

}
