package org.firstinspires.ftc.teamcode.testing.config;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.testing.AutoBot;
import org.firstinspires.ftc.teamcode.testing.ParamDouble;
import org.firstinspires.ftc.teamcode.testing.Param;
import org.firstinspires.ftc.teamcode.testing.Settings;

public class LifterSettings extends Settings {

	private ParamDouble liftPower = new ParamDouble("Lift Power","%",-30.0, -100.0, 100.0, 1);
	private ParamDouble liftDuration = new ParamDouble("Lift Duration","s",1.0, 0.0, 10.0, 0.2);
	private ParamDouble dropPower = new ParamDouble("Lower Power","%",-5.0, -100.0, 100.0, 1);
	private ParamDouble dropDuration = new ParamDouble("Lower Duration","s",1.0, 0.0, 10.0, 0.2);

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
	public void execute(AutoBot bot) {

		bot.lifter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // should already be the default.

//		DcMotor.ZeroPowerBehavior zeroPower = this.zeroBehavior.getCur()=="brake"
//				?DcMotor.ZeroPowerBehavior.BRAKE
//				:DcMotor.ZeroPowerBehavior.FLOAT;
//		bot.collector.setZeroPowerBehavior( zeroPower );
//		run(bot, this.zeroBehavior.getCur(), 0.0, this.zeroDuration.getCur());

		run(bot, "Lift", this.liftPower.getCur() * 0.01, this.liftDuration.getCur());
		run(bot, "Lower", this.dropPower.getCur() * 0.01, this.dropDuration.getCur());

		bot.lifter.setPower( 0.0 );

	}

	private void run(AutoBot bot, String label, double power, double timeoutS) {
		ElapsedTime runtime = new ElapsedTime();
		bot.lifter.setPower( power );
		while (bot.OpMode.opModeIsActive() && runtime.seconds() < timeoutS ) {
			bot.OpMode.telemetry.addData("Step", label );
			bot.OpMode.telemetry.addData("Power", "%.0f", power );
			bot.OpMode.telemetry.addData("elapsed time","%.1f", runtime.seconds());
			bot.OpMode.telemetry.update();
		}
	}


}
