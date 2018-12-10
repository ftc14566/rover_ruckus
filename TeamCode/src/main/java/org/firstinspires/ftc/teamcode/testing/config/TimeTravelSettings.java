package org.firstinspires.ftc.teamcode.testing.config;

import org.firstinspires.ftc.teamcode.testing.AutoBot;
import org.firstinspires.ftc.teamcode.testing.ParamDouble;
import org.firstinspires.ftc.teamcode.testing.Param;
import org.firstinspires.ftc.teamcode.testing.Settings;

public class TimeTravelSettings extends Settings {

	private ParamDouble power = new ParamDouble("Power","%",30.0, 5.0, 100.0, 5.0, 0.01);
	private ParamDouble duration = new ParamDouble("duration","s",3.0, 1.0, 15.0, 0.5 );

	public TimeTravelSettings(){
		super("Timed Travel");
		params = new Param[]{ power, duration };
	}

	@Override
	public void execute(AutoBot bot) {
		bot.driveForTime( power.getCur(), duration.getCur() );
	}

}
