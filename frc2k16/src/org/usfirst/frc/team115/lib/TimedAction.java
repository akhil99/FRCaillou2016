package org.usfirst.frc.team115.lib;

import org.usfirst.frc.team115.robot.HardwareAdaptor;
import org.usfirst.frc.team115.robot.subsystems.Punch;

import edu.wpi.first.wpilibj.Timer;

public abstract class TimedAction {
	
	private double timeout = 0.5; //seconds
	private double start = 0;
	
	public Punch punch = HardwareAdaptor.kPunch;
	
	
	public TimedAction(double timeout) {
		this.timeout = timeout;
	}
	
	
	public void start() {
		start = Timer.getFPGATimestamp();
	}
	
	public boolean isFinished() {
		return Timer.getFPGATimestamp() >= start + timeout;
	}
	
	public abstract void update();
	public abstract void end();

}
