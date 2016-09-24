package org.usfirst.frc.team115.robot.controllers;

import edu.wpi.first.wpilibj.CANTalon;

public class TBHController extends Controller{
	private CANTalon controller;

	private double gain = 1E-5;

	private double lastError = 0;

	private double tbh = 0;

	private double output = 0;



	public TBHController(CANTalon controller, double setpoint, double gain) {
		this.controller = controller;
		this.setpoint = setpoint;
		this.gain = gain;
	}


	public void update() {
		double error = setpoint - controller.getSpeed();

		output += gain * error;

		output = clamp(output);

		if(isPositive(lastError) != isPositive(error)) {
			output = 0.5 * (output + tbh);
			tbh = output;
			lastError = error;
		}

		controller.set(output);
	}

	private boolean isPositive(double input){
		return input > 0;
	}


	private static double clamp(double input) {
		if (input > 1) {
			return 1;
		}
		if (input < -1) {
			return -1;
		}
		return input;
	}

	public double getRPM() {
		return controller.get(); // figure out how to calculate rpm
	}

	@Override
	public void reset() {
		controller.set(0);
		setpoint = 0;
		lastError = 0;
		tbh = 0;
	}



}
