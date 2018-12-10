package org.firstinspires.ftc.teamcode.testing.config;

import org.firstinspires.ftc.teamcode.testing.AutoBot;
import org.firstinspires.ftc.teamcode.testing.ParamDouble;
import org.firstinspires.ftc.teamcode.testing.Param;
import org.firstinspires.ftc.teamcode.testing.Settings;

// Allows users to configure how to call .arc(...) on the Autobot
public class ArcSettings extends Settings {

	private ParamDouble leftInches = new ParamDouble("Left","in",12, 0.0, 1000.0, 1.0);
	private ParamDouble rightInches = new ParamDouble("Right","in",12, 0.0, 1000.0, 1.0);
	private ParamDouble power = new ParamDouble("power","%",60.0, 5.0, 100.0, 5.0);
	private ParamDouble timeoutS = new ParamDouble("timeout","s",3.0, 1.0, 15.0, 1.0);

	public ArcSettings(){
		super("ArcPath");
		params = new Param[]{ leftInches, rightInches, power, timeoutS };
	}

	@Override
	public void execute(AutoBot robot ){
		robot.DRIVE_SPEED = power.getCur() * 0.01;
		robot.arc( power.getCur()*0.01, leftInches.getCur(), rightInches.getCur(), timeoutS.getCur() );
	}

}
