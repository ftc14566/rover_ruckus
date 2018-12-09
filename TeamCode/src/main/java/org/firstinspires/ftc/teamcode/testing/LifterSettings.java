package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class LifterSettings extends Settings {

	private DoubleParam liftPower = new DoubleParam("Lift Power","%",-30.0, -100.0, 100.0, 1);
	private DoubleParam liftDuration = new DoubleParam("Lift Duration","s",1.0, 0.0, 10.0, 0.2);
	private DoubleParam dropPower = new DoubleParam("Lower Power","%",-5.0, -100.0, 100.0, 1);
	private DoubleParam dropDuration = new DoubleParam("Lower Duration","s",1.0, 0.0, 10.0, 0.2);

//	private SelectParameter zeroBehavior = new SelectParameter("0-Power Behavior",new String[]{"brake","float"});
//	private DoubleParam zeroDuration = new DoubleParam("0-Duration","s",3.0, 0.0, 10.0, 0.5);

	public LifterSettings(){
		super("Lifter");
		params = new Parameter[]{liftPower, liftDuration,
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
