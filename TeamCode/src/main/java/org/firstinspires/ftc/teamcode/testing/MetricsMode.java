package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

// Used to test different times / speeds / powers for autonomouse mode.
@TeleOp(name="Tractorbot: Metrics Tester", group="Tractorbot")
public class MetricsMode extends LinearOpMode {

	AutoBot robot   = new AutoBot();

	@Override
	public void runOpMode() {

		robot.init(hardwareMap);
		robot.OpMode = this;

		// Send telemetry message to signify robot waiting;
		telemetry.addData("Status", "Resetting Encoders");    //
		telemetry.update();

		// Send telemetry message to indicate successful Encoder reset
		telemetry.addData("Path0",  "Starting at %7d :%7d",robot.leftWheel.getCurrentPosition(), robot.rightWheel.getCurrentPosition());
		telemetry.update();

		// Wait for the game to start (driver presses PLAY)
		waitForStart();

		boolean nextModePressed = false;
		boolean prevModePressed = false;
		boolean nextParamPressed = false;
		boolean prevParamPressed = false;
		boolean incParamPressed = false;
		boolean decParamPressed = false;
		boolean aPressed = false;

		while(opModeIsActive()){
			s = settings[settingsIndex];
			if(gamepad1.right_bumper && !nextModePressed ){ nextMode();    } nextModePressed  = gamepad1.right_bumper;
			if(gamepad1.left_bumper  && !prevModePressed ){ prevMode();    } prevModePressed  = gamepad1.left_bumper;
			if(gamepad1.dpad_right   && !nextParamPressed){ s.nextParam(); } nextParamPressed = gamepad1.dpad_right;
			if(gamepad1.dpad_left    && !prevParamPressed){ s.prevParam(); } prevParamPressed = gamepad1.dpad_left;
			if(gamepad1.dpad_up      && !incParamPressed ){ s.incParam();  } incParamPressed  = gamepad1.dpad_up;
			if(gamepad1.dpad_down    && !decParamPressed ){ s.decParam();  } decParamPressed  = gamepad1.dpad_down;

			s.showCurrentParameter(telemetry);

			if(gamepad1.a            && !aPressed        ){ s.Run(robot);  } aPressed         = gamepad1.a;

		}

		telemetry.addData("Path", "Complete");
		telemetry.update();
	}

	private int settingsIndex = 0;
	private Settings[] settings = new Settings[]{
			new TravelSettings(),
			new TurnSettings(),
			new ArcSettings()
	};
	private Settings s = null;

	private void nextMode(){
		if( ++settingsIndex == settings.length ) settingsIndex = 0;
	}
	private void prevMode(){
		if( settingsIndex == 0 ) settingsIndex = settings.length;
		--settingsIndex;
	}

//	public boolean b = false;  // stop
//	public boolean x = false;  // rewind? save?
//	public boolean y = false;  // show parameters

}
