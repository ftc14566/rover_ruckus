package org.firstinspires.ftc.teamcode.testing;

// Allows users to configure how to call .arc(...) on the Autobot
public class ArcSettings extends Settings {

	private DoubleParam speed = new DoubleParam("Speed(%)",60.0, 5.0, 100.0, 5.0);
	private DoubleParam leftInches = new DoubleParam("L (in)",12, 0.0, 1000.0, 1.0);
	private DoubleParam rightInches = new DoubleParam("R (in)",12, 0.0, 1000.0, 1.0);
	private DoubleParam timeoutS = new DoubleParam("timeout(s)",3.0, 1.0, 15.0, 1.0);

	public ArcSettings(){
		params = new DoubleParam[]{ leftInches, rightInches, speed, timeoutS };
	}

	@Override
	public void Run( AutoBot robot ){
		robot.DRIVE_SPEED = speed.getCur() * 0.01;
		robot.arc( speed.getCur()*0.01, leftInches.getCur(), rightInches.getCur(), timeoutS.getCur() );
	}

}
