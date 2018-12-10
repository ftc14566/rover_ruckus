package org.firstinspires.ftc.teamcode.testing;

public interface ButtonPressListener {

	void onAButtonPressed();
	void onBButtonPressed();
	void onXButtonPressed();
	void onYButtonPressed();

	void onPadLeftPressed();
	void onPadRightPressed();
	void onPadUpPressed(int count);
	void onPadDownPressed(int count);

	void onLeftBumperPressed();
	void onRightBumperPressed();
	void onLeftTriggerPressed();
	void onRightTriggerPressed();

	void onBackPressed();
	void onStartPressed();
	void onGuidePressed();

}

