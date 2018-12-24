package org.firstinspires.ftc.teamcode.testing;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.testing.config.*;

public class SettingsSelector extends NullButtonPressListener implements ButtonPressListener {

	private ExecutionContext _ctx;
	private int settingsIndex = 0;
	private Settings[] settings = new Settings[]{
			new TravelSettings(),
			new TurnSettings(),
			new ArcSettings(),
			new EyeColorSettings(),
			new DoubleLifterSettings(),
			new CollectorSettings(),
			new ServoSettings(),
			new DriverSettings()
	};
	public Settings s;

	public SettingsSelector(ExecutionContext ctx){
		_ctx = ctx;
		changeSettingsIndex(0); // initialize s;
	}

	@Override
	public void onRightBumperPressed() { changeSettingsIndex(1); }
	@Override
	public void onLeftBumperPressed() { changeSettingsIndex(-1);	}

	void changeSettingsIndex(int delta){
		settingsIndex = (settingsIndex+delta+settings.length) % settings.length;
		s = settings[settingsIndex];
		s.resetIndex();
	}

	@Override
	public void onPadRightPressed() { s.nextParam(); }

	@Override
	public void onPadLeftPressed() { s.prevParam(); }

	@Override
	public void onPadDownPressed(int count) { for(int i=0;i<count;++i) s.decParam(); }

	@Override
	public void onPadUpPressed(int count) { for(int i=0;i<count;++i) s.incParam(); }

	@Override
	public void onAButtonPressed() {
		s.execute(_ctx);
	}

	public void showStatus( Telemetry telemetry ){
		s.show(telemetry);
	}

}
