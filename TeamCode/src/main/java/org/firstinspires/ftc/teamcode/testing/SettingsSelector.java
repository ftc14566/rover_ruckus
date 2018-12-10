package org.firstinspires.ftc.teamcode.testing;

import org.firstinspires.ftc.teamcode.testing.config.ArcSettings;
import org.firstinspires.ftc.teamcode.testing.config.CollectorSettings;
import org.firstinspires.ftc.teamcode.testing.config.EyeColorSettings;
import org.firstinspires.ftc.teamcode.testing.config.LifterSettings;
import org.firstinspires.ftc.teamcode.testing.config.ServoSettings;
import org.firstinspires.ftc.teamcode.testing.config.TimeTravelSettings;
import org.firstinspires.ftc.teamcode.testing.config.TimeTurnSettings;
import org.firstinspires.ftc.teamcode.testing.config.TravelSettings;
import org.firstinspires.ftc.teamcode.testing.config.TurnSettings;

public class SettingsSelector extends NullButtonPressListener implements ButtonPressListener {

	private AutoBot _robot;
	private int settingsIndex = 0;
	private Settings[] settings = new Settings[]{
			new TravelSettings(),
			new TurnSettings(),
			new ArcSettings(),
			new EyeColorSettings(),
			new LifterSettings(),
			new CollectorSettings(),
			new ServoSettings(),
			new TimeTravelSettings(),
			new TimeTurnSettings()
	};
	public Settings s;

	public SettingsSelector(AutoBot robot){
		_robot = robot;
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
	public void onAButtonPressed() { s.execute(_robot); }
}
