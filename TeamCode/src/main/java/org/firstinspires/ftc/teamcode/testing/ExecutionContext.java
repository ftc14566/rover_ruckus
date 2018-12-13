package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.RobotHardware;

public class ExecutionContext{

	public ExecutionContext(LinearOpMode opMode,RobotHardware robot){
		this.robot = robot;
		this.opMode = opMode;
	}

	public RobotHardware robot;
	public LinearOpMode opMode;
}
