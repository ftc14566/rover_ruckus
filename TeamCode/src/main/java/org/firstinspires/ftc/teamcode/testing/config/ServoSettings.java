package org.firstinspires.ftc.teamcode.testing.config;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.testing.*;

public class ServoSettings extends Settings {

	private ParamSelect select = new ParamSelect("Servo", new String[]{"lifter_latch","marker_servo"});
	private ParamDouble position = new ParamDouble("Position","%", 50, 0, 100,1, 0.01);

	public ServoSettings(){
		super("Servo");
		params = new Param[] { select, position };
	}

	@Override
	public Cmd buildCommand() {
		return new ServoCmd(select.getCur(),position.getCur());
	}


}
