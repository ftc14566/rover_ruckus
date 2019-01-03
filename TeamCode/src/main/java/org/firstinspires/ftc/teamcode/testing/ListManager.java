package org.firstinspires.ftc.teamcode.testing;


import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.testing.config.*;

import java.util.ArrayList;
import java.util.List;

public class ListManager  extends NullButtonPressListener implements ButtonPressListener {

	private ExecutionContext _ctx;
	private int _index = 0;
	private boolean editing = false;
	private int _topIndex = 0;

	ArrayList<Cmd> _list;

	public ListManager(ExecutionContext ctx) {
		_ctx = ctx;

		double wheelSeparation = 15.5;

		_list = new ArrayList<Cmd>();
		_list.add( new LifterCmd(0.01, "Lifter engage", 0.1)); // engage lifter
		_list.add( new ServoCmd("lifter_latch", 0.5));				// unlock
		_list.add( new LifterCmd(0.01, "Lifter engage", 3.0)); // wait
		_list.add( new LifterCmd(-0.1, "Lifter extend", 1.0)); // extend off of hook

		_list.add( new TravelCmd( 3, 0.3, 5 )); // move off of hook

		_list.add( new LifterCmd(0.2, "Lifter retract", 1.5)); // retract arm
		_list.add( new LifterCmd(0, "lifter off", 0.5)); // shut off lifter motor

		_list.add( new TurnCmd( 90, 0.3, wheelSeparation, 5 ));
		_list.add( new TravelCmd( 24, 0.3, 5 ));
		_list.add( new TurnCmd( 90, 0.3, wheelSeparation, 5 ));
		_list.add( new TravelCmd( -12, 0.3, 5 ));
		_list.add( new TurnCmd( -90, 0.3, wheelSeparation, 5 ));

		// drive
		// turn
		// move survo
		// power lifter
		//

	}

	@Override
	public void onBButtonPressed() {
		if(editing) {
			editing = false;
			_list.set(_index, _settings.buildCommand() );
			_settings = null;
		} else if( _index<_list.size() ) {
			editing = true;
			Cmd cmd = _list.get(_index);
			if( cmd instanceof TravelCmd ){
				TravelSettings settings = new TravelSettings();
				settings.init((TravelCmd)cmd);
				_settings = settings;
			} else if( cmd instanceof TurnCmd ){
				TurnSettings settings = new TurnSettings();
				settings.init((TurnCmd)cmd);
				_settings = settings;
			}
		}
	}

	@Override
	public void onYButtonPressed(){
		if( _settings == null )
			_list.add( _index, null );

		if( _settings instanceof TravelSettings)
			_settings = new TurnSettings();
		else if( _settings instanceof TurnSettings)
			_settings = new ServoSettings();
		else
			_settings = new TravelSettings();

		editing = true; // because cmd is missing from list
	}

	@Override
	public void onXButtonPressed(){
		if( !editing && _index < _list.size() )
			_list.remove(_index);
	}


	@Override
	public void onPadRightPressed() { if(_settings!=null) _settings.nextParam(); }

	@Override
	public void onPadLeftPressed() { if(_settings!=null) _settings.prevParam(); }

	@Override
	public void onPadDownPressed(int count) {
		if( _settings != null )
			for(int i=0;i<count;++i) _settings.decParam();
		else
			delta(count);
	}

	@Override
	public void onPadUpPressed(int count) {
		if( _settings != null )
			for(int i=0;i<count;++i) _settings.incParam();
		else
			delta(-count);
	}


	@Override
	public void onAButtonPressed() {
		Cmd cmd = _list.get(_index);
		cmd.exec(_ctx);
		delta(1);
	}

	private void delta(int delta){
		_index += delta;
		_index = Range.clip(_index,0,_list.size() );
		_topIndex = Range.clip(_topIndex, _index-5, _index);
	}

	Settings _settings;



	public void showStatus(Telemetry telemetry ){
		int max = 5;

		int end = Math.min(_topIndex+max,_list.size() );

		for(int i=_topIndex; i<end; ++i){
			if( i==_index){
				if( editing )
					_settings.showSummary(telemetry);
				else
					telemetry.addData(String.format("> %d", i), _list.get(i).toString() );
			} else {
				telemetry.addData(String.format("  %d",i), _list.get(i).toString() );
			}
		}
		telemetry.update();

	}


}