package org.firstinspires.ftc.teamcode.testing.config;

import org.firstinspires.ftc.teamcode.testing.Wheel;

public class ArcCmd extends WheelCmd implements Cmd {

	double _leftInches;
	double _rightInches;
	double _averagePower;

	protected ArcCmd(double leftInches, double rightInches, double averagePower, double timeoutS) {
		super(timeoutS);
		_leftInches = leftInches;
		_rightInches = rightInches;
		_averagePower = averagePower;
	}

	@Override
	protected void init(Wheel leftWheel, Wheel rightWheel) {

		double leftMag = Math.abs(_leftInches);
		double rightMag = Math.abs(_rightInches);
		double averageMag = (leftMag + rightMag)/2;
		leftWheel.startDriving(_averagePower * leftMag/averageMag, _leftInches);
		rightWheel.startDriving(_averagePower * rightMag/averageMag, _rightInches);
	}
}
