package org.firstinspires.ftc.teamcode.testing.config;

import org.firstinspires.ftc.teamcode.testing.*;

public class TimeTurnSettings extends Settings {

	private ParamDouble power = new ParamDouble("Power","%",30.0, 5.0, 100.0, 5.0);
	private ParamDouble duration = new ParamDouble("duration","s",3.0, 1.0, 15.0, 0.5, 0.01);


	public TimeTurnSettings() {
		super("Timed Turn");
		params = new Param[]{ power, duration};
	}

	@Override
	public void execute(ExecutionContext ctx) {
//		ctx.turnRightForTime( power.getCur(), duration.getCur() );
	}
}
