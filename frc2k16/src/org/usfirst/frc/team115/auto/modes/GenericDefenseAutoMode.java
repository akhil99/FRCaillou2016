package org.usfirst.frc.team115.auto.modes;

public class GenericDefenseAutoMode extends AutoMode {
	
	public GenericDefenseAutoMode() {
		this.m_update_rate = 1.0 / 200.0;
	}

	@Override
	protected void routine() throws AutoModeEndedException {
		waitTime(0.7);
		drive.setDistanceSetpoint(10, 5); //set to actual values later
		waitForDriveDistance(10, true, 5);
	}

	@Override
	public void prestart() {
		
	}
	
}
