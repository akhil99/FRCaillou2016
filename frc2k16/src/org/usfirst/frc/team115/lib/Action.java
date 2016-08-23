package org.usfirst.frc.team115.lib;

import org.usfirst.frc.team115.robot.HardwareAdaptor;
import org.usfirst.frc.team115.robot.subsystems.Flywheel;
import org.usfirst.frc.team115.robot.subsystems.Intake;
import org.usfirst.frc.team115.robot.subsystems.Punch;
import org.usfirst.frc.team115.robot.subsystems.ShooterArm;

public abstract class Action {

	protected DriveBase drive = HardwareAdaptor.kDrive;
	protected Intake intake = HardwareAdaptor.kIntake;
	protected ShooterArm arm = HardwareAdaptor.kAngler;
	protected Flywheel left = HardwareAdaptor.kLeftFlywheel;
	protected Flywheel right = HardwareAdaptor.kRightFlywheel;
	protected Punch punch = HardwareAdaptor.kPunch;


	public abstract boolean isFinished();

	public abstract void update();

	public abstract void done();

	public abstract void start();

}
