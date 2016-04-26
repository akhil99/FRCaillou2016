package org.usfirst.frc.team115.robot.controllers;

public abstract class Controller {
	
	double setpoint;
	
	public void setSetpoint(double setpoint) {
		this.setpoint = setpoint;
	}
	
	public double getSetpoint() {
		return setpoint;
	}
	
	public abstract void update();
	
}
