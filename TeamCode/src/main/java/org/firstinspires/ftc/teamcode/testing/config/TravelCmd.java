package org.firstinspires.ftc.teamcode.testing.config;

import org.firstinspires.ftc.teamcode.testing.Wheel;

public class TravelCmd extends WheelCmd implements Cmd {

	public double getInches(){ return _inches; }
	public double getPower(){ return _power; }

	private double _inches;
	private double _power;

	public TravelCmd(double inches, double power, double timeoutS) {
		super(timeoutS);
		_inches = inches;
		_power = power;
	}

	@Override
	protected void init(Wheel leftWheel, Wheel rightWheel) {
		leftWheel.startDriving(_power, _inches);
		rightWheel.startDriving(_power, _inches);
	}

	@Override
	public String toString() {
//		return "travel";
		return String.format("Travel %.0f(in) %.1f(%%) %.0f(s)", _inches, _power*100, getTimeoutS() );
	}

}

