package org.firstinspires.ftc.teamcode.testing.config;

import org.firstinspires.ftc.teamcode.testing.AutoBot;
import org.firstinspires.ftc.teamcode.testing.ParamDouble;
import org.firstinspires.ftc.teamcode.testing.Param;
import org.firstinspires.ftc.teamcode.testing.Settings;

public class TurnSettings extends Settings {

	public TurnSettings(){
		super("Turn");
		params = new Param[]{
				degrees,
				power,
				wheelSeparation,
				timeoutS
		};
	}

	private ParamDouble power = new ParamDouble("Power","%",50.0, 5.0, 100.0, 5.0);
	private ParamDouble degrees = new ParamDouble("Turn Right (CW)", "degrees", 90, -360, 360, 5);
	private ParamDouble wheelSeparation = new ParamDouble("Wheel Separation", "in", 16.0, 10, 24, 0.1);
	private ParamDouble timeoutS = new ParamDouble("timeout","s",3.0, 1.0, 15.0, 1.0);

	@Override
	public void execute(AutoBot bot){
		bot.TURN_SPEED = power.getCur() * 0.01;
		bot.wheelSeparation = wheelSeparation.getCur();
		bot.turn(degrees.getCur(),timeoutS.getCur());
	}

}
