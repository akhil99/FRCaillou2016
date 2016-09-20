package org.usfirst.frc.team115.auto.modes;

import org.usfirst.frc.team115.auto.actions.TimedAction;
import org.usfirst.frc.team115.auto.actions.WaitForAnglerAction;
import org.usfirst.frc.team115.auto.actions.WaitForDriveDistanceAction;
import org.usfirst.frc.team115.lib.DriveBase;
import org.usfirst.frc.team115.robot.HardwareAdaptor;
import org.usfirst.frc.team115.robot.subsystems.Flywheel;
import org.usfirst.frc.team115.robot.subsystems.Intake;
import org.usfirst.frc.team115.robot.subsystems.ShooterArm;

public abstract class AutoMode extends AutoModeBase {

		protected DriveBase drive = HardwareAdaptor.kDrive;
		protected Intake intake = HardwareAdaptor.kIntake;
		protected Flywheel rightFlywheel = HardwareAdaptor.kRightFlywheel;
		protected Flywheel leftFlywheel = HardwareAdaptor.kLeftFlywheel;
		protected ShooterArm angler = HardwareAdaptor.kAngler;
		
		public void waitTime(double seconds) throws AutoModeEndedException {
			runAction(new TimedAction(seconds));
		}

		public void waitForDriveDistance(double distance, boolean positive, double timeout) throws AutoModeEndedException {
			runAction(new WaitForDriveDistanceAction(distance, positive, timeout));
		}
		
		public void waitForAngler(double timeout) throws AutoModeEndedException {
			runAction(new WaitForAnglerAction(timeout));
		}

	}
