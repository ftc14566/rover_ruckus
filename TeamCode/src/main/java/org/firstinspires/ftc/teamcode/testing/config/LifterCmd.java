package org.firstinspires.ftc.teamcode.testing.config;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.testing.ExecutionContext;
import org.firstinspires.ftc.teamcode.testing.HardwareTractor2;

public class LifterCmd  implements Cmd {

	double power;
	String label;
	double timeoutS;

	public LifterCmd(double power, String label, double timeoutS){
		this.power = power;
		this.label = label;
		this.timeoutS = timeoutS;
	}

	@Override
	public void exec(ExecutionContext ctx) {

		HardwareTractor2 bot = ctx.robot;
		ElapsedTime runtime = new ElapsedTime();
		bot.lifter.setPower( power );
		while (ctx.opMode.opModeIsActive() && runtime.seconds() < timeoutS ) {
			ctx.opMode.telemetry.addData("Step", label );
			ctx.opMode.telemetry.addData("Power", "%.0f", power );
			ctx.opMode.telemetry.addData("elapsed time","%.1f", runtime.seconds());
			ctx.opMode.telemetry.update();
		}

	}

	@Override
	public String toString() {
		return String.format("%s: %.0f(%%) %.1f(s)", label, power*100, timeoutS);
	}
}
