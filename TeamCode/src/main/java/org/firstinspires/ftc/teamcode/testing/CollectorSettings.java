package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class CollectorSettings extends Settings {

	private DoubleParam power = new DoubleParam("Power","%",20.0, -100.0, 100.0, 5);
	private DoubleParam timeoutS = new DoubleParam("timeout","s",4.0, 2.0, 30.0, 2);


	public CollectorSettings(){
		super("Collector");
		params = new Parameter[]{ power, timeoutS };
	}

	@Override
	public void execute(AutoBot bot) {

		double timeoutS = this.timeoutS.getCur();
		ElapsedTime runtime = new ElapsedTime();

		bot.collector.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // should already be the default.
//		bot.collector.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

		bot.collector.setPower( power.getCur()*0.01 );

		while (bot.OpMode.opModeIsActive() && runtime.seconds() < timeoutS ) {
			bot.OpMode.telemetry.addData("elapsed time","%.1f", runtime.seconds());
			bot.OpMode.telemetry.update();
		}

		bot.collector.setPower( 0.0 );

	}


}
