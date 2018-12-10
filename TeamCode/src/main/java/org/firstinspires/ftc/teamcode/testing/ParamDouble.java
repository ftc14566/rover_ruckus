package org.firstinspires.ftc.teamcode.testing;

// Allows the user to manually pick a value to feed into the AutoBot
public class ParamDouble implements Param {
	String _label;
	String _units;
	double _cur;
	double _min;
	double _max;
	double _step;
	double _scale;

	public ParamDouble(String label, String units, double init, double min, double max, double step){
		_label = label;
		_units = units;
		_cur = init;
		_min = min;
		_max = max;
		_step = step;
		_scale = 1.0;
	}

	public ParamDouble(String label, String units, double init, double min, double max, double step, double scale){
		_label = label;
		_units = units;
		_cur = init;
		_min = min;
		_max = max;
		_step = step;
		_scale = scale;
	}


	@Override
	public void inc(){ _cur = Math.min(_cur+_step, _max); }
	@Override
	public void dec(){ _cur = Math.max(_cur-_step, _min); }

	public double getCur(){ return _cur * _scale; }

	@Override
	public void appendSummary(StringBuilder builder){
		builder.append(String.format("%.2f",getCur()));
		builder.append(' ');
		builder.append(_units);
	}

	@Override
	public void showDetails(org.firstinspires.ftc.robotcore.external.Telemetry telemetry){

		// telemetry.addData(_label,  "%.2f (%s)  (Range: %.2f to %.2f)",getCur(),_units,_min,_max);
		telemetry.addData(_label,  "%.2f",getCur());
		telemetry.addData("Units",  "%s",_units);
		telemetry.addData("Range",  "%.2f to %.2f",_min,_max);
	}
}
