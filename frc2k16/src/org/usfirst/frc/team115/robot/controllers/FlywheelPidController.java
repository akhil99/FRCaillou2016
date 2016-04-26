package org.usfirst.frc.team115.robot.controllers;

import edu.wpi.first.wpilibj.CANTalon;

public class FlywheelPidController extends PidController {

	private CANTalon talon;
	
	public FlywheelPidController(CANTalon talon, double p, double i, double d, double f) {
		super(p, i, d);
		this.talon = talon;

		talon.configNominalOutputVoltage(+0f, -0f);
    talon.configPeakOutputVoltage(+12f, 0.0f);
		
		talon.setProfile(0);
		talon.setP(p);
		talon.setI(i);
		talon.setD(d);
		talon.setF(f);
	}

	@Override
	public void update() {
		talon.set(setpoint);
	}

}
