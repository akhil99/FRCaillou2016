package org.usfirst.frc.team115.robot.controllers;

public abstract class BangBangController extends Controller {

	double max, min;
	double tolerance;
	
	public BangBangController(double tolerance, double max, double min) {
		this.tolerance = tolerance;
		this.max = max;
		this.min = min;
		setpoint = 0;
	}
	
	public abstract void update();

}
