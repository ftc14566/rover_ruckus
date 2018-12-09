package org.firstinspires.ftc.teamcode.testing;

// Allows users to configure how to call .arc(...) on the Autobot
public class ArcSettings extends Settings {

	private DoubleParam leftInches = new DoubleParam("Left","in",12, 0.0, 1000.0, 1.0);
	private DoubleParam rightInches = new DoubleParam("Right","in",12, 0.0, 1000.0, 1.0);
	private DoubleParam power = new DoubleParam("power","%",60.0, 5.0, 100.0, 5.0);
	private DoubleParam timeoutS = new DoubleParam("timeout","s",3.0, 1.0, 15.0, 1.0);

	public ArcSettings(){
		super("ArcPath");
		params = new DoubleParam[]{ leftInches, rightInches, power, timeoutS };
	}

	@Override
	public void execute(AutoBot robot ){
		robot.DRIVE_SPEED = power.getCur() * 0.01;
		robot.arc( power.getCur()*0.01, leftInches.getCur(), rightInches.getCur(), timeoutS.getCur() );
	}

}
