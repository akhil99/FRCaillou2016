package org.usfirst.frc.team115.robot.controllers;

public abstract class PidController extends Controller {
	
	protected double p, i, d;
	
	public PidController(double p, double i, double d) {
		this.p = p;
		this.i = i;
		this.d = d;
		setpoint = 0;
	}

	public abstract void update();

	public void reset() {
		p = 0;
		i = 0;
		d = 0;
		setpoint = 0;
	}
}
