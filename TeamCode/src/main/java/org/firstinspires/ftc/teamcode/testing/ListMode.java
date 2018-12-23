package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.testing.config.EyeColorSettings;


@TeleOp(name="List", group="Tractorbot")
public class ListMode extends LinearOpMode {

	private HardwareTractor2 robot = new HardwareTractor2();

	@Override
	public void runOpMode() {

		telemetry.addData("Status", "Initializing Autobot"); telemetry.update();
		robot.init(hardwareMap);

		EyeColorSettings.register(hardwareMap);

		// create the controller
		ListManager menu = new ListManager( new ExecutionContext(this,robot) );
		// wire it up to game pad 1
		PressController pad = new PressController( gamepad1 );
		pad.Listener = menu;

		// Wait for the game to start (driver presses PLAY)
		telemetry.addData("Status", "waiting for start"); telemetry.update();
		waitForStart();

		while(opModeIsActive()){
			pad.exec();
			menu.showStatus(telemetry);
		}

		telemetry.addData("Path", "Complete");
		telemetry.update();


	}

}
