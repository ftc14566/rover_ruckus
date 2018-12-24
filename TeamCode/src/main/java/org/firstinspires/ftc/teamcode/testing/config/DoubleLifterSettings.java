package org.firstinspires.ftc.teamcode.testing.config;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.testing.*;

public class DoubleLifterSettings extends Settings {

	private ParamDouble liftPower = new ParamDouble("Lift Power","%",30.0, -100.0, 100.0, 1, 0.01);
	private ParamDouble liftDuration = new ParamDouble("Lift Duration","s",1.0, 0.0, 10.0, 0.2);
	private ParamDouble dropPower = new ParamDouble("Lower Power","%",5.0, -100.0, 100.0, 1, 0.01);
	private ParamDouble dropDuration = new ParamDouble("Lower Duration","s",5.0, 0.0, 10.0, 0.2);

	public DoubleLifterSettings(){
		super("Lifter");
		params = new Param[]{liftPower, liftDuration,
				dropPower, dropDuration,
				//zeroBehavior, zeroDuration
		};
	}

	@Override
	public Cmd buildCommand() {
		CmdList list = new CmdList();
		list._cmds.add( new LifterCmd( liftPower.getCur(), "Lift", liftDuration.getCur()) );
		list._cmds.add( new LifterCmd( this.dropPower.getCur(), "Lower", this.dropDuration.getCur() ));
		list._cmds.add( new LifterCmd( 0, "Off", 0.01 ));
		return list;

	}

}
