package org.usfirst.frc.team115.auto.actions;

import org.usfirst.frc.team115.lib.DriveBase;
import org.usfirst.frc.team115.robot.HardwareAdaptor;
import org.usfirst.frc.team115.robot.subsystems.Flywheel;
import org.usfirst.frc.team115.robot.subsystems.ShooterArm;

public abstract class Action {
	
	protected DriveBase kDrive = HardwareAdaptor.kDrive;
	protected Flywheel rightFlywheel = HardwareAdaptor.kRightFlywheel;
	protected Flywheel leftFlywheel = HardwareAdaptor.kLeftFlywheel;
	protected ShooterArm angler = HardwareAdaptor.kAngler;
	
	public abstract boolean isFinished();

    public abstract void update();

    public abstract void done();

    public abstract void start();

}
