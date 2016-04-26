package org.usfirst.frc.team115.robot.controllers;

public abstract class PidController extends Controller {
	
	protected double p, i, d;
	
	public PidController(double p, double i, double d) {
		this.p = p;
		this.i = i;
		this.d = d;
	}

	public abstract void update();

}
