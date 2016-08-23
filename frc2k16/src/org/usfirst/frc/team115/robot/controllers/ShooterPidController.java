package org.usfirst.frc.team115.robot.controllers;

import edu.wpi.first.wpilibj.CANTalon;

public class ShooterPidController extends PidController {
	
	private CANTalon talon;
	private double tolerance;
	
	public ShooterPidController(double p, double i, double d, double tolerance, CANTalon talon) {
		super(p, i, d);
		this.talon = talon;
		talon.setPID(0.02, 0.001, 0, 0, 0, 0, 1);
		talon.setPID(p, i, d, 0, 0, 0, 0);
		talon.configNominalOutputVoltage(+0f, -0f);
    talon.configPeakOutputVoltage(+12f, -12f);
    talon.setAllowableClosedLoopErr(0);
	}

	@Override
	public void update() {
		if(Math.abs(talon.getPosition() - setpoint) <= tolerance) {
			talon.setProfile(1);
		}
			talon.set(setpoint);
	}
	
	public boolean onTarget() {
		return Math.abs(talon.getPosition() - setpoint) <= tolerance;
	}

}
