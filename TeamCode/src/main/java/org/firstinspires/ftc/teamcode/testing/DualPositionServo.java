package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.hardware.Servo;

public class DualPositionServo {

	public Servo servo;
	public double Position1;
	public double Position2;

	public DualPositionServo(Servo servo, double pos1, double pos2){
		this.servo = servo;
		Position1 = pos1;
		Position2 = pos2;
	}

	public void goToPosition1(){
		servo.setPosition( Position1 );
	}

	public void goToPosition2(){
		servo.setPosition (Position2 );
	}


}
