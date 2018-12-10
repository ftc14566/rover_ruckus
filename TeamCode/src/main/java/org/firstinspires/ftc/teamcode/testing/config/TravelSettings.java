package org.firstinspires.ftc.teamcode.testing.config;

import org.firstinspires.ftc.teamcode.testing.AutoBot;
import org.firstinspires.ftc.teamcode.testing.ParamDouble;
import org.firstinspires.ftc.teamcode.testing.Param;
import org.firstinspires.ftc.teamcode.testing.Settings;

public class TravelSettings extends Settings {

	private ParamDouble inches = new ParamDouble("Dist", "in",12, -1000.0, 1000.0, 1.0);
	private ParamDouble power = new ParamDouble("Power","%",30.0, 5.0, 100.0, 5.0);
	private ParamDouble timeoutS = new ParamDouble("timeout","s",3.0, 1.0, 15.0, 0.5);

	public TravelSettings(){
		super("Travel");
		params = new Param[]{
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
