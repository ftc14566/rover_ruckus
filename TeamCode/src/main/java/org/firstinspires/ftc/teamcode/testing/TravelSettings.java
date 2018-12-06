package org.firstinspires.ftc.teamcode.testing;

public class TravelSettings extends Settings {

	private DoubleParam speed = new DoubleParam("Speed(%)",60.0, 5.0, 100.0, 5.0);
	private DoubleParam inches = new DoubleParam("Dist(in)",12, 0.0, 1000.0, 1.0);
	private DoubleParam timeoutS = new DoubleParam("timeout(s)",3.0, 1.0, 15.0, 1.0);

	public TravelSettings(){
		params = new DoubleParam[]{ speed, inches, timeoutS };
	}

	@Override
	public void Run( AutoBot robot ){
		robot.DRIVE_SPEED = speed.getCur() * 0.01;
		robot.travel( inches.getCur(), timeoutS.getCur() );
	}

}
