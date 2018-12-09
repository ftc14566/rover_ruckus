package org.firstinspires.ftc.teamcode.testing;

public class SettingsController extends NullButtonPressListener implements ButtonPressListener {

	private AutoBot _robot;
	private int settingsIndex = 0;
	private Settings[] settings = new Settings[]{
			new TravelSettings(),
			new TurnSettings(),
			new ArcSettings()
	};
	public Settings s;

	public SettingsController(AutoBot robot){
		_robot = robot;
		changeSettingsIndex(0); // initialize s;
	}


	@Override
	public void onRightTriggerPressed() { changeSettingsIndex(1); }
	@Override
	public void onLeftTriggerPressed() { changeSettingsIndex(-1);	}

	void changeSettingsIndex(int delta){
		settingsIndex = (settingsIndex+delta+settings.length) % settings.length;
		s = settings[settingsIndex];
	}

	@Override
	public void onPadRightPressed() { s.nextParam(); }

	@Override
	public void onPadLeftPressed() { s.prevParam(); }

	@Override
	public void onPadDownPressed() { s.decParam(); }

	@Override
	public void onPadUpPressed() { s.incParam(); }

	@Override
	public void onAButtonPressed() { s.execute(_robot); }
}
