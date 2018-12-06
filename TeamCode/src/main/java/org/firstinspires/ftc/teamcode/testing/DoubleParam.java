package org.firstinspires.ftc.teamcode.testing;

// Allows the user to manually pick a value to feed into the AutoBot
public class DoubleParam {
	String _label;
	double _cur;
	double _min;
	double _max;
	double _step;
	public DoubleParam(String label, double init, double min, double max, double step){
		_label = label;
		_cur = init; _min = min; _max = max; _step = step;
	}
	public void inc(){ _cur = Math.min(_cur+_step, _max); }
	public void dec(){ _cur = Math.max(_cur-_step, _min); }
	public double getCur(){ return _cur; }
}
