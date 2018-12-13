package org.firstinspires.ftc.teamcode.testing.config;

import org.firstinspires.ftc.teamcode.testing.Wheel;

public class TurnCmd extends WheelCmd implements Cmd {

	private double _degreesClockwise;
	private double _wheelSeparation;
	private double _power;

	protected TurnCmd(double degreesClockwise, double power, double wheelSeparation, double timeoutS) {
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

}
