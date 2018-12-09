package org.firstinspires.ftc.teamcode.testing;

import org.firstinspires.ftc.robotcore.external.Telemetry;

// Allows the user to manually pick a value to feed into the AutoBot
public class DoubleParam implements Parameter {
	String _label;
	String _units;
	double _cur;
	double _min;
	double _max;
	double _step;
	public DoubleParam(String label, String units, double init, double min, double max, double step){
		_label = label;
		_units = units;
		_cur = init;
		_min = min;
		_max = max;
		_step = step;
	}

	@Override
	public void inc(){ _cur = Math.min(_cur+_step, _max); }
	@Override
	public void dec(){ _cur = Math.max(_cur-_step, _min); }

	public double getCur(){ return _cur; }
	@Override
	public void appendSummary(StringBuilder builder){
		builder.append(String.format("%.2f",_cur));
		builder.append(' ');
		builder.append(_units);
	}

	@Override
	public void showDetails(org.firstinspires.ftc.robotcore.external.Telemetry telemetry){

		// telemetry.addData(_label,  "%.2f (%s)  (Range: %.2f to %.2f)",_cur,_units,_min,_max);
		telemetry.addData(_label,  "%.2f",_cur);
		telemetry.addData("Units",  "%s",_units);
		telemetry.addData("Range",  "%.2f to %.2f",_min,_max);
	}
}
