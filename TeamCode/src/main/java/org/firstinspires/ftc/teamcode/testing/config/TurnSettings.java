package org.firstinspires.ftc.teamcode.testing.config;

import org.firstinspires.ftc.teamcode.testing.*;

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

	private ParamDouble degrees = new ParamDouble("Turn Right (CW)", "degrees", 90, -360, 360, 5);
	private ParamDouble power = new ParamDouble("Power","%",50.0, 5.0, 100.0, 5.0, 0.01);
	private ParamDouble wheelSeparation = new ParamDouble("Wheel Separation", "in", 16.0, 10, 24, 0.1);
	private ParamDouble timeoutS = new ParamDouble("timeout","s",3.0, 1.0, 15.0, 1.0);

	@Override
	public Cmd buildCommand() {
		return new TurnCmd( degrees.getCur(), power.getCur(), wheelSeparation.getCur(), timeoutS.getCur() );
	}

	public void init(TurnCmd cmd){
		degrees.setCur( cmd.getDegreesClockwise() );
		power.setCur( cmd.getPower() );
		wheelSeparation.setCur( cmd.getWheelSeparation());
		timeoutS.setCur( cmd.getTimeoutS() );
	}

}
