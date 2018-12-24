package org.firstinspires.ftc.teamcode.testing.config;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.testing.*;

public class LifterSettings extends Settings {

	private ParamDouble liftPower = new ParamDouble("Lift Power","%",30.0, -100.0, 100.0, 1, 0.01);
	private ParamDouble liftDuration = new ParamDouble("Lift Duration","s",1.0, 0.0, 10.0, 0.2);

	public LifterSettings(){
		super("Lifter");
		params = new Param[]{liftPower, liftDuration };
	}

	@Override
	public Cmd buildCommand() {
		return new LifterCmd( liftPower.getCur(), "Lifter", liftDuration.getCur());
	}

	public void init(LifterCmd cmd ){
		_label = cmd.getLabe();
		liftPower.setCur( cmd.getPower() );
		liftDuration.setCur( cmd.getTimeout() );
	}

}
