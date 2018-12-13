package org.firstinspires.ftc.teamcode.testing.config;

import org.firstinspires.ftc.teamcode.testing.Wheel;

public class TravelCmd extends WheelCmd implements Cmd {

	private double _inches;
	private double _power;

	protected TravelCmd(double inches, double power, double timeoutS) {
		super(timeoutS);
		_inches = inches;
		_power = power;
	}

	@Override
	protected void init(Wheel leftWheel, Wheel rightWheel) {
		leftWheel.startDriving(_power, _inches);
		rightWheel.startDriving(_power, _inches);
	}

}

