package org.firstinspires.ftc.teamcode.testing;

import org.firstinspires.ftc.robotcore.external.Telemetry;

// base class for testing 1 function all on the AutoBot
public abstract class Settings {

	protected Settings(String label){
		_label = label;
	}

	public void nextParam(){
		++paramIndex;
		if(paramIndex==params.length) paramIndex = 0;
	}

	public void prevParam(){
		if(paramIndex==0) paramIndex = params.length;
		--paramIndex;
	}

	public void incParam(){
		params[paramIndex].inc();
	}

	public void decParam(){
		params[paramIndex].dec();
	}

	public void show(Telemetry telemetry){
		showSummary( telemetry );
		telemetry.addData("-","-");
		showParamDetails(telemetry);
		telemetry.update();
	}

	void showSummary(Telemetry telemetry){
		StringBuilder builder = new StringBuilder();
		for(int i=0;i<params.length;++i){
			builder.append(' ');
			if(i==paramIndex) builder.append("[");
			params[i].appendSummary(builder);
			if(i==paramIndex) builder.append("]");
		}
		telemetry.addData(_label,  builder.toString() );
	}

	void showParamDetails(Telemetry telemetry){
		params[paramIndex].showDetails(telemetry);
	}

	private String _label;
	protected Parameter[] params = new Parameter[4];
	private int paramIndex = 0;

	abstract public void execute(AutoBot bot);
}
