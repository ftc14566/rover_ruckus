package org.firstinspires.ftc.teamcode.testing;

public class TurnSettings extends Settings {

	private DoubleParam speed = new DoubleParam("Speed(%)",50.0, 5.0, 100.0, 5.0);
	private DoubleParam degrees = new DoubleParam("Deg CW", 90, -360, 360, 5);
	private DoubleParam timeoutS = new DoubleParam("driveSpeed",3.0, 1.0, 15.0, 1.0);

	@Override
	public void Run(AutoBot bot){
		bot.TURN_SPEED = speed.getCur() * 0.01;
		bot.turn(degrees.getCur(),timeoutS.getCur());
	}

}
