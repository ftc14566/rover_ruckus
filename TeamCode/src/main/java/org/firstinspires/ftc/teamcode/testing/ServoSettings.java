package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.util.ElapsedTime;

public class ServoSettings extends Settings {

	private DoubleParam position = new DoubleParam("position","°",20.0, 0.0, 180.0, 5);
	private DoubleParam duration = new DoubleParam("duration","s",20.0, 2.0, 60.0, 2);

	public ServoSettings(){
		super("Servo");
		params = new Parameter[]{
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
