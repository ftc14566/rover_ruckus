package org.firstinspires.ftc.teamcode.testing.config;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RobotHardware;
import org.firstinspires.ftc.teamcode.testing.*;

public class CollectorSettings extends Settings {

	private ParamDouble power = new ParamDouble("Power","%",20.0, -100.0, 100.0, 5, 0.01);
	private ParamDouble timeoutS = new ParamDouble("timeout","s",4.0, 2.0, 30.0, 2);


	public CollectorSettings(){
		super("Collector");
		params = new Param[]{ power, timeoutS };
	}

	@Override
	public void execute(ExecutionContext ctx) {

		RobotHardware bot = ctx.robot;

		double timeoutS = this.timeoutS.getCur();
		ElapsedTime runtime = new ElapsedTime();

		bot.collector.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

		bot.collector.setPower( power.getCur() );

		while (ctx.opMode.opModeIsActive() && runtime.seconds() < timeoutS ) {
			ctx.opMode.telemetry.addData("elapsed time","%.1f", runtime.seconds());
			ctx.opMode.telemetry.update();
		}

		bot.collector.setPower( 0.0 );

	}


}
