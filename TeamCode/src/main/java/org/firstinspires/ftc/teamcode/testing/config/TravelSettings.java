package org.firstinspires.ftc.teamcode.testing.config;

import org.firstinspires.ftc.teamcode.testing.*;

public class TravelSettings extends Settings {

	private ParamDouble inches = new ParamDouble("Dist", "in",12, -1000.0, 1000.0, 1.0);
	private ParamDouble power = new ParamDouble("Power","%",30.0, 5.0, 100.0, 5.0,0.01);
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
	public void execute(ExecutionContext ctx ){
		TravelCmd cmd = new TravelCmd( inches.getCur(), power.getCur(), timeoutS.getCur() );
		cmd.exec(ctx);
	}

}
