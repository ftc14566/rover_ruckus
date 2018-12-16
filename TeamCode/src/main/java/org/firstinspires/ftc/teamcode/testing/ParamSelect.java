package org.firstinspires.ftc.teamcode.testing;

import android.text.TextUtils;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ParamSelect implements Param {

	private String _label;
	private String[] _options;
	private int _index = 0;

	public ParamSelect(String label, String[] options){
		_label = label;
		_options = options;
	}

	public String getCur(){ return _options[_index]; }

	@Override
	public void inc() {
		if( ++_index == _options.length ) _index = 0;
	}

	@Override
	public void dec() {
		if( _index == 0 ) _index = _options.length;
		--_index;
	}

	@Override
	public void appendSummary(StringBuilder builder) {
		builder.append( getCur() );
	}

	@Override
	public void showDetails(Telemetry telemetry) {
		telemetry.addData(_label, getCur() + " (" + TextUtils.join(" / ",_options) +")" );
	}
}
