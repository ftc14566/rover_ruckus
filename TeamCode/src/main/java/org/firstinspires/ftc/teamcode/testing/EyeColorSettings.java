package org.firstinspires.ftc.teamcode.testing;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class EyeColorSettings extends Settings {

	private SelectParameter eye = new SelectParameter("Eye",new String[]{"left","right"});
	private SelectParameter led = new SelectParameter("LED",new String[]{"on","off"});
	private DoubleParam duration = new DoubleParam("duration","s",8.0, 2.0, 30.0, 2);

	public EyeColorSettings(){
		super("Eye Color");
		params = new Parameter[]{ eye, led, duration};

	}

	private static View relativeLayout;

	public static void register(HardwareMap hardwareMap) {
		int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
		relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);
	}

	float hsvValues[] = {0F,0F,0F};
	final float[] values = hsvValues;

	@Override
	public void execute(AutoBot bot) {

		double timeoutS = this.duration.getCur();

		ColorSensor eye = this.eye.getCur() == "left" ? bot.leftEye : bot.rightEye;

		boolean ledOn = led.getCur()=="on";
		eye.enableLed( ledOn );

		ElapsedTime runtime = new ElapsedTime();
		while (bot.OpMode.opModeIsActive() && runtime.seconds() < timeoutS ) {

			// convert the RGB values to HSV values.
			Color.RGBToHSV(eye.red() * 8, eye.green() * 8, eye.blue() * 8, hsvValues);
			bot.OpMode.telemetry.addData("Eye", this.eye.getCur() );
			bot.OpMode.telemetry.addData("LED", ledOn ? "On" : "Off");
			bot.OpMode.telemetry.addData("Red Green Blue Alpha", "R:%d  G:%d  B:%d  A:%d", eye.red(),eye.green(),eye.blue(),eye.alpha());
			bot.OpMode.telemetry.addData("Hue Saturation Value", "H:%.2f  S:%.2f  V:%.2f", hsvValues[0],hsvValues[1],hsvValues[2]);
			bot.OpMode.telemetry.addData("elapsed time", "%.0f", runtime.seconds());

			relativeLayout.post(new Runnable() {
				public void run() { relativeLayout.setBackgroundColor(Color.HSVToColor(0xff, values)); }
			});

			bot.OpMode.telemetry.update();
		}

		eye.enableLed(false);

		// Set the panel back to the default color
		relativeLayout.post(new Runnable() {
			public void run() {
				relativeLayout.setBackgroundColor(Color.WHITE);
			}
		});


	}

}
