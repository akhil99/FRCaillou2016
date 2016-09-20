package org.usfirst.frc.team115.auto.actions;

public class WaitForAnglerAction extends TimedAction {

	public WaitForAnglerAction(double timeout) {
		super(timeout);
	}
	
	public boolean isFinished() {
		return angler.isOnTarget() || super.isFinished();
	}

}
