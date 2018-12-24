package org.firstinspires.ftc.teamcode.testing.config;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.testing.ExecutionContext;

public class ServoCmd implements Cmd {

	public String getSelect(){ return select;}
	public double getPosition(){ return position; }

	private String select;
	private double position;

	public ServoCmd(String select,double position){
		this.select = select;
		this.position = position;
	}

	@Override
	public void exec(ExecutionContext ctx) {

		Servo servo = select == "lifter_latch"
			? ctx.robot.lifter_lock
			: ctx.robot.marker_servo;

		servo.setPosition( position );

		ctx.opMode.sleep(1000); // give servo time to move

	}

	@Override
	public String toString() {
		return String.format("Servo - %s = %.0f%%", select, position*100 );
	}
}
