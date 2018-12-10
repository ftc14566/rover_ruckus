package org.firstinspires.ftc.teamcode.testing.config;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.testing.AutoBot;
import org.firstinspires.ftc.teamcode.testing.ParamDouble;
import org.firstinspires.ftc.teamcode.testing.Param;
import org.firstinspires.ftc.teamcode.testing.Settings;

public class ServoSettings extends Settings {

	private ParamDouble position = new ParamDouble("position","°",20.0, 0.0, 180.0, 5);
	private ParamDouble duration = new ParamDouble("duration","s",20.0, 2.0, 60.0, 2);

	public ServoSettings(){
		super("Servo");
		params = new Param[]{
				position,
				duration
		};
	}

	@Override
	public void execute(AutoBot bot) {
		double timeoutS = this.duration.getCur();
		bot.markerServo.servo.setPosition( position.getCur() );
		ElapsedTime runtime = new ElapsedTime();
		while (bot.OpMode.opModeIsActive() && runtime.seconds() < timeoutS ) {

			bot.OpMode.telemetry.addData("Token", "%.1f", bot.markerServo.servo.getPosition() );
			bot.OpMode.telemetry.addData("Latch", "%.1f", bot.pinServo.servo.getPosition() );
			bot.OpMode.telemetry.update();
		}
	}
}
