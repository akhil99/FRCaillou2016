package org.usfirst.frc.team115.auto.modes;

import org.usfirst.frc.team115.robot.Constants;

public class LowBarAutoMode extends AutoMode {
	
	public LowBarAutoMode() {
		m_update_rate = 1.0 / 200.0;
	}
	
	@Override
	protected void routine() throws AutoModeEndedException {
		waitTime(0.7);
		angler.setPidSetpoint(Constants.kIntakePosition);
		waitForAngler(4);
		drive.setDistanceSetpoint(-10, -5); //set to actual values later
		waitForDriveDistance(-10, false, -5);
		angler.setPidSetpoint(Constants.kBatterPosition);
		waitForAngler(4);
	}

	@Override
	public void prestart() {
		
	}

}
