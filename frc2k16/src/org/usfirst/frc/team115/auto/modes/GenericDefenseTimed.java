package org.usfirst.frc.team115.auto.modes;

import org.usfirst.frc.team115.lib.DriveSignal;

public class GenericDefenseTimed extends AutoMode {

	@Override
	protected void routine() throws AutoModeEndedException {
		waitTime(0.7);
		drive.setOpenLoop(new DriveSignal(0.75, 0.75));
		waitTime(4);
	}

	@Override
	public void prestart() {
		// TODO Auto-generated method stub
		
	}

}
