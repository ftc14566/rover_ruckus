package org.firstinspires.ftc.teamcode.testing.config;

import org.firstinspires.ftc.teamcode.testing.ExecutionContext;

import java.util.ArrayList;


public class CmdList implements Cmd {

	public ArrayList<Cmd> _cmds = new ArrayList<Cmd>();

	@Override
	public void exec(ExecutionContext ctx) {
		for(int i=0;i<_cmds.size();++i)
			_cmds.get(i).exec(ctx);
	}

}
