package org.usfirst.frc.team115.lib;

import edu.wpi.first.wpilibj.Timer;

public class TimedAction extends Action {
	
	private double timeout = 0; //seconds
	private double start = 0;
	
	public TimedAction(double timeout) {
		this.timeout = timeout;
	}
	
	
	public void start() {
		start = Timer.getFPGATimestamp();
	}
	
	public boolean isFinished() {
		return Timer.getFPGATimestamp() >= start + timeout;
	}
	
	public void update(){
		
	}
	
	public void done() {
		
	}

}
