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
	private ManualRoutine manualRoutine = new ManualRoutine();
	
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
		setpoints.reset();
	}
	
	double setpoint = 0;

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
			double output = HardwareAdaptor.kXboxController.getRawAxis(5);
			angler.setOpenLoop(output);
		} else if(setpoints.armSetpoint == RobotSetpoints.ArmPosition.RESET) {
			angler.setOpenLoop(-0.2);
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
			leftFlywheel.setTBHSetpoint(-Constants.kFlywheelIntake);
			rightFlywheel.setSpeed(-leftFlywheel.getVoltage()); // use values from other for now
		} else if(setpoints.flywheelAction == RobotSetpoints.FlywheelAction.SHOOT) {
			leftFlywheel.setTBHSetpoint(-Constants.kFlywheelMax);
			rightFlywheel.setSpeed(-leftFlywheel.getVoltage());
		} else if(setpoints.flywheelAction == RobotSetpoints.FlywheelAction.BATTER_SHOOT) {
			leftFlywheel.setTBHSetpoint(-Constants.kFlywheelBatter);
			rightFlywheel.setSpeed(-leftFlywheel.getVoltage());
		} else if(setpoints.flywheelAction == RobotSetpoints.FlywheelAction.NONE) {
			leftFlywheel.setOpenLoop(0);
			rightFlywheel.setSpeed(-leftFlywheel.getVoltage());
		}

	}

}
