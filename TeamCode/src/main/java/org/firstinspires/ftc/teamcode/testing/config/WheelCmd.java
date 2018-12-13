package org.firstinspires.ftc.teamcode.testing.config;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RobotHardware;
import org.firstinspires.ftc.teamcode.testing.ExecutionContext;
import org.firstinspires.ftc.teamcode.testing.Wheel;

public abstract class WheelCmd implements Cmd  {

	private double _timeoutS;

	protected WheelCmd(double timeoutS){
		_timeoutS = timeoutS;
	}

	abstract protected void init(Wheel leftWheel, Wheel rightWheel);

	public  void exec( ExecutionContext ctx) {
		LinearOpMode opMode = ctx.opMode;
		RobotHardware robot = ctx.robot;

		if (!opMode.opModeIsActive()) { return; }

		Wheel leftWheel = robot.leftWheel;
		Wheel rightWheel = robot.rightWheel;

		init(leftWheel,rightWheel);

		ElapsedTime runtime = new ElapsedTime();
		while (opMode.opModeIsActive() && (runtime.seconds() < _timeoutS)
				&& (leftWheel.isBusy() || rightWheel.isBusy())) {
			leftWheel.showTelemetry(opMode.telemetry);
			rightWheel.showTelemetry(opMode.telemetry);
			opMode.telemetry.addData("elapsed time", "%5.2f (s)", runtime.seconds() );

			opMode.telemetry.update();
		}

		leftWheel.stop();
		rightWheel.stop();
	}

}

//	public void ArcForTime(double leftPower, double rightPower, double durationS){
//		leftWheel._dcMotor.setPower(leftPower);
//		rightWheel._dcMotor.setPower(rightPower);
//		ElapsedTime runtime = new ElapsedTime();
//		while (opMode.opModeIsActive() && (runtime.seconds() < durationS)) {
//			opMode.telemetry.addData("Path", "%2.5f S Elapsed", runtime.seconds());
//			opMode.telemetry.update();
//		}
//	}
