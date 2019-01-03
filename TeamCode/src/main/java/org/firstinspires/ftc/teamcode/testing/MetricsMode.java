package org.firstinspires.ftc.teamcode.testing;

import android.content.Context;

import com.qualcomm.robotcore.eventloop.opmode.*;

import org.firstinspires.ftc.teamcode.testing.config.EyeColorSettings;

import java.io.*;

// Used to test different times / speeds / powers for autonomouse mode.
@TeleOp(name="Metrics Tester", group="Tractorbot")
public class MetricsMode extends LinearOpMode {

	HardwareTractor2 robot   = new HardwareTractor2();

	@Override
	public void runOpMode() {

		telemetry.addData("Status", "Initializing Autobot"); telemetry.update();
		robot.init(hardwareMap);

		EyeColorSettings.register(hardwareMap);

		// create the controller
		SettingsSelector menu = new SettingsSelector( new ExecutionContext(this,robot) );
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

	void x(String s){
//		String filename = "myfile";
//		String fileContents = "Hello world!";
//		FileOutputStream outputStream;

		try {
			FileOutputStream outStream = hardwareMap.appContext.openFileOutput("bob.txt", Context.MODE_PRIVATE);
			outStream.write(s.getBytes());
			outStream.close();
		} catch(Exception ex ){

		}
//		context.getFilesDir();
//		File file = new File(Context.getFilesDir(), filename);
//		file.

	}

}
