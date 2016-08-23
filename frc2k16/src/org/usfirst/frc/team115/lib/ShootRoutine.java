package org.usfirst.frc.team115.lib;

import edu.wpi.first.wpilibj.Timer;

public class ShootRoutine extends Routine {

	public enum States {
		START, MOVING_TO_SHOOT, LINEUP, SHOOT, DONE
	}

	//TODO define setpoints for shooter arm (angle to move to)

	private States state = States.START;
	private boolean isNewState = true;
	private boolean finished = false;
	Timer routineTimer = new Timer();


	@Override
	public void reset() {
		state = States.START;
		isNewState = true;
		routineTimer.start();
		routineTimer.reset();
	}

	@Override
	public RobotSetpoints update(Commands commands, RobotSetpoints setpoints) {
		States newState = state;
		switch(state) {
		case START:
			newState = States.MOVING_TO_SHOOT;
			break;
		case MOVING_TO_SHOOT: 
			if(isNewState) {
				setpoints.armSetpoint = RobotSetpoints.ArmPosition.OUTERWORKS;
				//arm.setPidSetpoint(Constants.kIntakePosition); //TODO find intake position and make it a constant
			}
			if(!isNewState && arm.isOnTarget()) {
				newState = States.LINEUP;
			}
			break;
		case LINEUP:
			//TODO call turn based on vision
			if(!isNewState) { //TODO add onTarget
				newState = States.SHOOT;
			}
			break;
		case SHOOT:
			setpoints.intakeAction = RobotSetpoints.IntakeAction.NONE;
			setpoints.flywheelAction = RobotSetpoints.FlywheelAction.SHOOT;
			/*
			 * if we use a breakbeam 
			if(intake.breakbeamTriggered()) { //TODO add breakbeam trigger
				newState = States.DONE;
			}*/
			if(routineTimer.get() > 1) { //TODO edit time
				newState = States.DONE;
			}

			break;
		case DONE:
			setpoints.intakeAction = RobotSetpoints.IntakeAction.NONE;
			setpoints.flywheelAction = RobotSetpoints.FlywheelAction.NONE;
			finished = true;
			break;
		}

		if(commands.intakeRequest == Commands.IntakeRequest.INTAKE) {
			setpoints.intakeAction = RobotSetpoints.IntakeAction.NONE;
		} else if(commands.intakeRequest == Commands.IntakeRequest.EXHAUST) {
			setpoints.intakeAction = RobotSetpoints.IntakeAction.EXHAUST;
		}

		if(commands.flywheelRequest == Commands.FlywheelRequest.INTAKE) {
			setpoints.flywheelAction = RobotSetpoints.FlywheelAction.NONE;
		}

		setpoints.punchAction = RobotSetpoints.PunchAction.INTAKE;

		isNewState = false;
		if(newState != state) {
			state = newState;
			routineTimer.reset();
			isNewState = true;
		}

		return setpoints;
	}

	@Override
	public void cancel() {

	}

	@Override
	public boolean isFinished() {
		return finished;
	}

}
