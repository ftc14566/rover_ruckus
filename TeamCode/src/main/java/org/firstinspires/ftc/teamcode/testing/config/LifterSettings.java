package org.firstinspires.ftc.teamcode.testing.config;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RobotHardware;
import org.firstinspires.ftc.teamcode.testing.*;

public class LifterSettings extends Settings {

	private ParamDouble liftPower = new ParamDouble("Lift Power","%",30.0, -100.0, 100.0, 1, 0.01);
	private ParamDouble liftDuration = new ParamDouble("Lift Duration","s",1.0, 0.0, 10.0, 0.2);
	private ParamDouble dropPower = new ParamDouble("Lower Power","%",5.0, -100.0, 100.0, 1, 0.01);
	private ParamDouble dropDuration = new ParamDouble("Lower Duration","s",5.0, 0.0, 10.0, 0.2);

//	private ParamSelect zeroBehavior = new ParamSelect("0-Power Behavior",new String[]{"brake","float"});
//	private ParamDouble zeroDuration = new ParamDouble("0-Duration","s",3.0, 0.0, 10.0, 0.5);

	public LifterSettings(){
		super("Lifter");
		params = new Param[]{liftPower, liftDuration,
				dropPower, dropDuration,
				//zeroBehavior, zeroDuration
		};
	}

	@Override
	public void execute(ExecutionContext ctx) {
		RobotHardware bot = ctx.robot;
		bot.lifter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // should already be the default.

//		DcMotor.ZeroPowerBehavior zeroPower = this.zeroBehavior.getCur()=="brake"
//				?DcMotor.ZeroPowerBehavior.BRAKE
//				:DcMotor.ZeroPowerBehavior.FLOAT;
//		bot.collector.setZeroPowerBehavior( zeroPower );
//		run(bot, this.zeroBehavior.getCur(), 0.0, this.zeroDuration.getCur());

		run(ctx, "Lift", this.liftPower.getCur(), this.liftDuration.getCur());
		run(ctx, "Lower", this.dropPower.getCur(), this.dropDuration.getCur());

		bot.lifter.setPower( 0.0 );

	}

	private void run( ExecutionContext ctx, String label, double power, double timeoutS ) {
		RobotHardware bot = ctx.robot;
		ElapsedTime runtime = new ElapsedTime();
		bot.lifter.setPower( power );
		while (ctx.opMode.opModeIsActive() && runtime.seconds() < timeoutS ) {
			ctx.opMode.telemetry.addData("Step", label );
			ctx.opMode.telemetry.addData("Power", "%.0f", power );
			ctx.opMode.telemetry.addData("elapsed time","%.1f", runtime.seconds());
			ctx.opMode.telemetry.update();
		}
	}


}
