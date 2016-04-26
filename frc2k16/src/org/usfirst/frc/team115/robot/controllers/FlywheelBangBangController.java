package org.usfirst.frc.team115.robot.controllers;

import edu.wpi.first.wpilibj.CANTalon;

public class FlywheelBangBangController extends BangBangController {

	CANTalon talon;

	public FlywheelBangBangController(CANTalon talon, double tolerance, double max, double min) {
		super(tolerance, max, min);
		this.talon = talon;
	}

	@Override
	public void update() {
		if(setpoint > 0) {
			if(talon.getEncVelocity() < setpoint) {
				System.out.println("setting: " + max);
				talon.set(max);
			} else {
				System.out.println("setting: " + 0);
				talon.set(0);
			}
		} else {
			if(talon.getEncVelocity() > setpoint) {
				talon.set(min);
			} else {
				talon.set(0);
			}
		}
	}

}
