package org.firstinspires.ftc.teamcode.testing.config;

import org.firstinspires.ftc.teamcode.testing.Wheel;

public class TurnCmd extends WheelCmd implements Cmd {

	public double getDegreesClockwise(){ return _degreesClockwise; }
	public double getPower(){ return _power; }
	public double getWheelSeparation(){ return _wheelSeparation; }

	private double _degreesClockwise;
	private double _power;
	private double _wheelSeparation;

	public TurnCmd(double degreesClockwise, double power, double wheelSeparation, double timeoutS) {
		super(timeoutS);
		_degreesClockwise = degreesClockwise;
		_power = power;
		_wheelSeparation = wheelSeparation;
	}

	@Override
	protected void init(Wheel leftWheel, Wheel rightWheel) {

		double inches = _degreesClockwise * (_wheelSeparation/2) * 3.1415926 / 180;
		leftWheel.startDriving(_power, inches);
		rightWheel.startDriving(_power, -inches);

	}

	@Override
	public String toString() {
//		return "turn";
		return String.format("Turn Right %.0f(deg) %.1f(%%) %.1f(in) %.0f(s)", _degreesClockwise, _power*100, _wheelSeparation, getTimeoutS() );
	}
}
