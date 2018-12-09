package org.firstinspires.ftc.teamcode.testing;

public class TravelSettings extends Settings {

	private DoubleParam inches = new DoubleParam("Dist", "in",12, -1000.0, 1000.0, 1.0);
	private DoubleParam power = new DoubleParam("Power","%",30.0, 5.0, 100.0, 5.0);
	private DoubleParam timeoutS = new DoubleParam("timeout","s",3.0, 1.0, 15.0, 0.5);

	public TravelSettings(){
		super("Travel");
		params = new DoubleParam[]{
				inches,
				power,
				timeoutS
		};
	}

	@Override
	public void execute(AutoBot robot ){
		robot.DRIVE_SPEED = power.getCur() * 0.01;
		robot.travel( inches.getCur(), timeoutS.getCur() );
	}

}
