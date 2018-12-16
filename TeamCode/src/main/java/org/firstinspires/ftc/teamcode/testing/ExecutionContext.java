package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class ExecutionContext{

	public ExecutionContext(LinearOpMode opMode,HardwareTractor2 robot){
		this.robot = robot;
		this.opMode = opMode;
	}

	public HardwareTractor2 robot;
	public LinearOpMode opMode;
}
