package org.usfirst.frc.team115.lib;

import org.usfirst.frc.team115.robot.Constants;
import org.usfirst.frc.team115.robot.HardwareAdaptor;
import org.usfirst.frc.team115.robot.subsystems.Flywheel;
import org.usfirst.frc.team115.robot.subsystems.Intake;
import org.usfirst.frc.team115.robot.subsystems.Punch;
import org.usfirst.frc.team115.robot.subsystems.ShooterArm;

import edu.wpi.first.wpilibj.Servo;

public class BehaviorManager {

	public Flywheel leftFlywheel = HardwareAdaptor.kLeftFlywheel;
	public Flywheel rightFlywheel = HardwareAdaptor.kRightFlywheel;
	public Intake intake = HardwareAdaptor.kIntake;
	public ShooterArm angler = HardwareAdaptor.kAngler;
	public Punch punch = HardwareAdaptor.kPunch;
	public Servo camServo = HardwareAdaptor.kCameraServo;

	private Routine currentRoutine = null;
	private RobotSetpoints setpoints;
	private ManualRoutine manualRoutine = new ManualRoutine(); //TODO make manual routine

	private void setNewRoutine(Routine newRoutine) {
		boolean needsCancel = newRoutine != currentRoutine && currentRoutine != null;

		boolean needsReset = newRoutine != currentRoutine && newRoutine != null;
		if(needsCancel) {
			currentRoutine.cancel();
		}
		currentRoutine = newRoutine;
		if(needsReset) {
			currentRoutine.reset();
		}
	}

	public void reset() {
		setNewRoutine(null);
	}

	public BehaviorManager() {
		setpoints = new RobotSetpoints();
		//setpoints.armSetpoint = RobotSetpoints.ArmPosition.NONE;
		setpoints.reset();
	}

	public void update(Commands commands) {
		setpoints.reset();

		if(currentRoutine != null && currentRoutine.isFinished()) {
			setNewRoutine(null);
		}

		/*if(commands.cancelRoutine) {
			setNewRoutine(null);
		} else if(commands.intakeRequest == Commands.IntakeRequest.INTAKE && !(currentRoutine instanceof IntakeRoutine)) {
			setNewRoutine(new IntakeRoutine());
		} else if(commands.flywheelRequest == Commands.FlywheelRequest.SHOOT && !(currentRoutine instanceof ShootRoutine)) {
			setNewRoutine(new ShootRoutine()); //TODO make shoot routine
		}*/

		if(currentRoutine != null) {
			setpoints = currentRoutine.update(commands, setpoints);
		}

		setpoints = manualRoutine.update(commands, setpoints);
		
		if(setpoints.camSetpoint == RobotSetpoints.CamPosition.LOWBAR) {
			camServo.setAngle(180);
		} else if(setpoints.camSetpoint == RobotSetpoints.CamPosition.STD) {
			camServo.setAngle(0);
		} else if(setpoints.camSetpoint == RobotSetpoints.CamPosition.INCREMENT) {
			camServo.setAngle(camServo.getAngle()+10);
		} else if(setpoints.camSetpoint == RobotSetpoints.CamPosition.DECREMENT) {
			camServo.setAngle(camServo.getAngle()-10);
		}

		if(setpoints.armSetpoint == RobotSetpoints.ArmPosition.NONE) {
			angler.setPidSetpoint(angler.getPosition());
		} else if(setpoints.armSetpoint == RobotSetpoints.ArmPosition.INTAKE) {
			angler.setPidSetpoint(Constants.kIntakePosition);
		} else if(setpoints.armSetpoint == RobotSetpoints.ArmPosition.OUTERWORKS) {
			angler.setPidSetpoint(Constants.kOuterworksPosition); //TODO Add outworks position
		} else if(setpoints.armSetpoint == RobotSetpoints.ArmPosition.BATTER) {
			angler.setPidSetpoint(Constants.kBatterPosition);
		} else if(setpoints.armSetpoint == RobotSetpoints.ArmPosition.MANUAL) {
			double angle = HardwareAdaptor.kXboxController.getRawAxis(4) * Constants.kIntakePosition;
			if(angle != 0) {				
				angler.setPidSetpoint(angle);
			} else {
				angler.setPidSetpoint(angler.getPosition());
			}
		}

		if(setpoints.intakeAction == RobotSetpoints.IntakeAction.INTAKE) {
			intake.setState(Intake.State.INTAKE);
		} else if(setpoints.intakeAction == RobotSetpoints.IntakeAction.EXHAUST) {
			intake.setState(Intake.State.EXHAUST);
		} else if(setpoints.intakeAction == RobotSetpoints.IntakeAction.NONE) {
			intake.setState(Intake.State.NEUTRAL);
		}

		if(setpoints.punchAction == RobotSetpoints.PunchAction.INTAKE) {
			punch.setState(Punch.State.INTAKE);
		} else if(setpoints.punchAction == RobotSetpoints.PunchAction.PUNCH) {
			punch.setState(Punch.State.PUNCH);
		} else if (setpoints.punchAction == RobotSetpoints.PunchAction.NONE){
			punch.setState(Punch.State.NONE);
		}


		if(setpoints.flywheelAction == RobotSetpoints.FlywheelAction.INTAKE) {
			rightFlywheel.setTBHSetpoint(Constants.kFlywheelIntake);
			leftFlywheel.setTBHSetpoint(Constants.kFlywheelIntake);
			//leftFlywheel.setSpeedPidSetpoint(Constants.kFlywheelIntake);
//			leftFlywheel.setSpeed(rightFlywheel.getVoltage()); // use values from other for now
		} else if(setpoints.flywheelAction == RobotSetpoints.FlywheelAction.SHOOT) {
			rightFlywheel.setTBHSetpoint(Constants.kFlywheelMax);
			leftFlywheel.setTBHSetpoint(Constants.kFlywheelMax);
		//	leftFlywheel.setSpeedPidSetpoint(Constants.kFlywheelMax);
//			leftFlywheel.setSpeed(rightFlywheel.getVoltage());
		} else if(setpoints.flywheelAction == RobotSetpoints.FlywheelAction.BATTER_SHOOT) {
			rightFlywheel.setTBHSetpoint(Constants.kFlywheelBatter);
			leftFlywheel.setTBHSetpoint(Constants.kFlywheelBatter);
			//leftFlywheel.setSpeedPidSetpoint(Constants.kFlywheelBatter);
//			leftFlywheel.setSpeed(rightFlywheel.getVoltage());
		} else if(setpoints.flywheelAction == RobotSetpoints.FlywheelAction.NONE) {
			rightFlywheel.setOpenLoop(0);
			leftFlywheel.setOpenLoop(0);
//			leftFlywheel.setSpeedPidSetpoint(0);
//			leftFlywheel.setSpeed(rightFlywheel.getVoltage());
		}

	}

}
