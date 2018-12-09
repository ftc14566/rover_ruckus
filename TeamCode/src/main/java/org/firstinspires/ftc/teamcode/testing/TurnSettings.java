package org.firstinspires.ftc.teamcode.testing;

public class TurnSettings extends Settings {

	public TurnSettings(){
		super("Turn");
		params = new Parameter[]{
				degrees,
				power,
				wheelSeparation,
				timeoutS
		};
	}

	private DoubleParam power = new DoubleParam("Power","%",50.0, 5.0, 100.0, 5.0);
	private DoubleParam degrees = new DoubleParam("Turn Right (CW)", "degrees", 90, -360, 360, 5);
	private DoubleParam wheelSeparation = new DoubleParam("Wheel Separation", "in", 16.0, 10, 24, 0.1);
	private DoubleParam timeoutS = new DoubleParam("timeout","s",3.0, 1.0, 15.0, 1.0);

	@Override
	public void execute(AutoBot bot){
		bot.TURN_SPEED = power.getCur() * 0.01;
		bot.wheelSeparation = wheelSeparation.getCur();
		bot.turn(degrees.getCur(),timeoutS.getCur());
	}

}
