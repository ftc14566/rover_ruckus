package org.firstinspires.ftc.teamcode.testing.config;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.testing.*;

public class DriverSettings extends Settings {

	private ParamSelect config = new ParamSelect("Configuation", new String[]{"Single","Dual","Tank","Squared"});
	private ParamSelect runMode = new ParamSelect("Run Mode", new String[]{"WITHOUT Encoder","USING Encoder"});
	private ParamDouble scale = new ParamDouble("Scale", "%", 40, 0, 100, 5, 0.01);

	public DriverSettings() {
		super("Driver");
		params = new Param[]{config, runMode,scale};
	}

	@Override
	public void execute(ExecutionContext ctx) {

		HardwareTractor2 bot = ctx.robot;
		Gamepad pad = ctx.opMode.gamepad1;

		String strMode = this.config.getCur();
		DcMotor.RunMode runMode = this.runMode.getCur() == "WITHOUT Encoder"
				? DcMotor.RunMode.RUN_WITHOUT_ENCODER
				: DcMotor.RunMode.RUN_USING_ENCODER;

		DriveMode driveMode = buildDriver(strMode, runMode);

		driveMode.init(bot.leftWheel,bot.rightWheel);

		while( !ctx.opMode.gamepad1.x && ctx.opMode.opModeIsActive() ){

			ctx.opMode.telemetry.addData("Configuration", "%s %s", strMode, this.config.getCur() );
			ctx.opMode.telemetry.addData("Left Stick","(%.2f %.2f)", pad.left_stick_x, pad.left_stick_y );

			driveMode.drive(pad,bot.leftWheel,bot.rightWheel);

			ctx.opMode.telemetry.addData("To exit","press 'X'");
			ctx.opMode.telemetry.update();

		}

	}

	@Override
	public Cmd buildCommand() {
		return null;
	}

	DriveMode buildDriver(String mode, DcMotor.RunMode runMode){
		double scale = this.scale.getCur();
		switch( mode ){
			case "Single": return new DriveModeSingleJoy(runMode,scale);
			case "Dual":  return new DriveModeHelicopter(runMode,scale);
			case "Tank":  return new DriveModeTank(runMode,scale);
			case "Squared": return new DriveModeSquared(runMode,scale);
		}
		return null; // this should never happen...
	}

	void tankMode(Gamepad gamepad1, Wheel leftWheel, Wheel rightWheel){
	}

	void squaredMode(Gamepad gamepad1, Wheel leftWheel, Wheel rightWheel){
		leftWheel.setPower( square(-gamepad1.left_stick_y) );
		rightWheel.setPower( square(-gamepad1.right_stick_y) );
	}

	double square(double d){
		return d*(d<0?-d:d);
	}
}
