package org.usfirst.frc.team115.robot.controllers;

import org.usfirst.frc.team115.lib.TimedAction;
import org.usfirst.frc.team115.robot.subsystems.Punch;

public class PunchAction extends TimedAction {

	public PunchAction(double timeout) {
		super(timeout);
	}

	@Override
	public void update() {
		if(isFinished()) {
			end();
		} else {
			punch.setState(Punch.State.PUNCH);
		}
	}

	@Override
	public void end() {
		punch.setState(Punch.State.RETRACT);
	}
	
	

}
