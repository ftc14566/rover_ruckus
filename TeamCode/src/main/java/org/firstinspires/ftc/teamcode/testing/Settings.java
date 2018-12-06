package org.firstinspires.ftc.teamcode.testing;

import org.firstinspires.ftc.robotcore.external.Telemetry;

// base class for testing 1 function all on the AutoBot
public abstract class Settings {

	String _label;

	DoubleParam[] params = new DoubleParam[4];
	int paramIndex = 0;

	public void nextParam(){
		paramIndex = (paramIndex+1)%params.length;
	}

	public void prevParam(){
		paramIndex = (paramIndex-1+ params.length)% params.length;
	}

	public void incParam(){
		params[paramIndex].inc();
	}

	public void decParam(){
		params[paramIndex].dec();
	}

	public void showCurrentParameter(Telemetry telemetry){
		DoubleParam p = params[paramIndex];
		telemetry.addData(_label,  "%s = %3d  (%3d to %3d)",p._label,p.getCur(),p._min,p._max);
		telemetry.update();
	}

	abstract public void Run(AutoBot bot);
}
