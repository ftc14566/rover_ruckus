package org.firstinspires.ftc.teamcode.testing.config;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.testing.*;

public class EyeColorSettings extends Settings {

	private ParamSelect eye = new ParamSelect("Eye",new String[]{"left","right"});
	private ParamSelect led = new ParamSelect("LED",new String[]{"on","off"});
	private ParamDouble duration = new ParamDouble("duration","s",8.0, 2.0, 30.0, 2);

	public EyeColorSettings(){
		super("Eye Color");
		params = new Param[]{ eye, led, duration};

	}

	private static View relativeLayout;

	public static void register(HardwareMap hardwareMap) {
		int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
		relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);
	}

	float hsvValues[] = {0F,0F,0F};
	final float[] values = hsvValues;

	@Override
	public void execute(ExecutionContext ctx) {

		HardwareTractor2 bot = ctx.robot;

		double timeoutS = this.duration.getCur();

		ColorSensor eye = null;//this.eye.getCur() == "left" ? bot.left_eye : bot.right_eye;

		boolean ledOn = led.getCur()=="on";
		eye.enableLed( ledOn );

		ElapsedTime runtime = new ElapsedTime();
		while (ctx.opMode.opModeIsActive() && runtime.seconds() < timeoutS ) {

			// convert the RGB values to HSV values.
			Color.RGBToHSV(eye.red() * 8, eye.green() * 8, eye.blue() * 8, hsvValues);
			ctx.opMode.telemetry.addData("Eye", this.eye.getCur() );
			ctx.opMode.telemetry.addData("LED", ledOn ? "On" : "Off");
			ctx.opMode.telemetry.addData("Red Green Blue Alpha", "R:%d  G:%d  B:%d  A:%d", eye.red(),eye.green(),eye.blue(),eye.alpha());
			ctx.opMode.telemetry.addData("Hue Saturation Value", "H:%.2f  S:%.2f  V:%.2f", hsvValues[0],hsvValues[1],hsvValues[2]);
			ctx.opMode.telemetry.addData("elapsed time", "%.0f", runtime.seconds());

			relativeLayout.post(new Runnable() {
				public void run() { relativeLayout.setBackgroundColor(Color.HSVToColor(0xff, values)); }
			});

			ctx.opMode.telemetry.update();
		}

		eye.enableLed(false);

		// Set the panel back to the default color
		relativeLayout.post(new Runnable() {
			public void run() {
				relativeLayout.setBackgroundColor(Color.WHITE);
			}
		});


	}

	@Override
	public Cmd buildCommand() {
		return null;
	}

}
