package org.usfirst.frc.team115.robot.subsystems;

import org.usfirst.frc.team115.lib.Loopable;
import org.usfirst.frc.team115.robot.Constants;
import org.usfirst.frc.team115.robot.controllers.Controller;
import org.usfirst.frc.team115.robot.controllers.ShooterPidController;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;

public class ShooterArm extends Loopable {
	
	CANTalon left, right;
	Controller controller;
	
	public ShooterArm() {
		left = new CANTalon(Constants.kLeftShooter);
		right = new CANTalon(Constants.kRightShooter);
		
		right.changeControlMode(TalonControlMode.Follower);
		right.set(left.getDeviceID());
	}
	
	public void setPidSetpoint(double setpoint) {
		left.changeControlMode(TalonControlMode.Position);
		if(!(controller instanceof ShooterPidController))
			controller = new ShooterPidController(0.0, 0.0, 0.0, left); //tune pid values
		((ShooterPidController)pidController).setSetpoint(setpoint);
	}
	
	@Override
	public void update() {
		if(controller != null)
			controller.update();
	}

}
