package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.testing.config.EyeColorSettings;

// Used to test different times / speeds / powers for autonomouse mode.
@TeleOp(name="Metrics Tester", group="Tractorbot")
public class MetricsMode extends LinearOpMode {

	AutoBot robot   = new AutoBot();

	@Override
	public void runOpMode() {

		telemetry.addData("Status", "Initializing Autobot"); telemetry.update();
		robot.init(hardwareMap);
		robot.OpMode = this;

		EyeColorSettings.register(hardwareMap);

		SettingsSelector controller = new SettingsSelector(robot);
		PressController pad = new PressController( gamepad1 );
		pad.Listener = controller;

		// Wait for the game to start (driver presses PLAY)
		telemetry.addData("Status", "waiting for start"); telemetry.update();
		waitForStart();

		while(opModeIsActive()){
			pad.exec();
			controller.s.show(telemetry);
		}

		telemetry.addData("Path", "Complete");
		telemetry.update();
	}

}
