package org.usfirst.frc.team115.auto.actions;

import org.usfirst.frc.team115.lib.DriveState;

public class WaitForDriveDistanceAction extends TimedAction {

	double distance;
	boolean positive;
	
	public WaitForDriveDistanceAction(double distance, boolean positive, double timeout) {
		super(timeout);
		this.distance = distance;
		this.positive = positive;
	}
	
	public boolean isFinished() {
		DriveState state = kDrive.getCurrentState();
		double avg = (state.getLeftDistance() + state.getRightDistance()) / 2;
		return (positive ? avg >= distance : avg <= distance) || super.isFinished();
	}

}
