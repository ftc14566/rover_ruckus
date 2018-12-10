package org.firstinspires.ftc.teamcode.testing.config;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.testing.AutoBot;
import org.firstinspires.ftc.teamcode.testing.ParamDouble;
import org.firstinspires.ftc.teamcode.testing.Param;
import org.firstinspires.ftc.teamcode.testing.Settings;

public class CollectorSettings extends Settings {

	private ParamDouble power = new ParamDouble("Power","%",20.0, -100.0, 100.0, 5);
	private ParamDouble timeoutS = new ParamDouble("timeout","s",4.0, 2.0, 30.0, 2);


	public CollectorSettings(){
		super("Collector");
		params = new Param[]{ power, timeoutS };
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
