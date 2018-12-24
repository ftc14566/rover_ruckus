package org.firstinspires.ftc.teamcode.testing.config;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.testing.*;

public class ServoSettings extends Settings {

	private ParamSelect select = new ParamSelect("Servo", new String[]{"lifter_latch","marker_servo"});
	private ParamDouble position = new ParamDouble("Position","%", 50, 0, 100,1, 0.01);

	public ServoSettings(){
		super("Servo");
		params = new Param[] { select, position };
	}

	@Override
	public void execute(ExecutionContext ctx) {

		double pos = 0;
		double delta = 0.01;

		Servo servo = select.getCur() == "latch"
				? ctx.robot.lifter_lock
				: ctx.robot.marker_servo;

		servo.setPosition(pos);

		ElapsedTime runtime = new ElapsedTime();
		while (ctx.opMode.opModeIsActive() && !ctx.opMode.gamepad1.x) {

			if (ctx.opMode.gamepad1.a) {
				pos = (ctx.opMode.gamepad1.left_stick_y + 1) / 2;
			} else {
				if (ctx.opMode.gamepad1.left_stick_y > 0.01) pos -= delta;
				if (ctx.opMode.gamepad1.left_stick_y < -0.01) pos += delta;
			}
			pos = Range.clip(pos, 0, 1);

			servo.setPosition(pos);

			ctx.opMode.telemetry.addData("Servo", select.getCur());
			ctx.opMode.telemetry.addData("Pos", "%.0f", pos * 100);
			ctx.opMode.telemetry.addData("To Exit", "press x");
			ctx.opMode.telemetry.update();
		}
	}

	public void init(ServoCmd cmd){
		select.setCur(cmd.getSelect());
		position.setCur(cmd.getPosition());
	}

	@Override
	public Cmd buildCommand() {
		return new ServoCmd(select.getCur(),position.getCur());
	}


}
