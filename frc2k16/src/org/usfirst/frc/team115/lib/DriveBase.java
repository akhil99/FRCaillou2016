package org.usfirst.frc.team115.lib;


import org.usfirst.frc.team115.robot.Constants;
import org.usfirst.frc.team115.robot.HardwareAdaptor;
import org.usfirst.frc.team115.robot.controllers.Controller;
import org.usfirst.frc.team115.robot.controllers.DriveStraightController;

import edu.wpi.first.wpilibj.CANTalon;

public class DriveBase extends Loopable{

	private CANTalon leftDriveBaseFront;
	private CANTalon leftDriveBaseRear;

	private CANTalon rightDriveBaseFront;
	private CANTalon rightDriveBaseRear;

	private DriveController controller;

	private DriveState cachedState = new DriveState(0, 0, 0, 0, 0, 0);

	public static abstract class DriveController extends Controller {

		@Override
		public void reset() {
			// TODO Auto-generated method stub

		}

		public abstract DriveSignal update(DriveState state); 

	}

	public DriveBase() {

		leftDriveBaseFront = new CANTalon(Constants.kLeftDriveFront);
		leftDriveBaseRear = new CANTalon(Constants.kLeftDriveRear);
		rightDriveBaseFront = new CANTalon(Constants.kRightDriveFront);
		rightDriveBaseRear = new CANTalon(Constants.kRightDriveRear);
		
	}

	public void setOpenLoop(DriveSignal signal) {
		controller = null;
		setDriveOutputs(signal);
	}

	public void setDistanceSetpoint(double distance, double velocity) {
		double vel = Math.min(18, Math.max(velocity, 0)); // TODO add kDriveMaxSpeedPerInch
		controller = new DriveStraightController(getCurrentState(), distance, vel);
	}

	public void setDriveOutputs(DriveSignal signal) {
		leftDriveBaseFront.set(signal.left);
		leftDriveBaseRear.set(signal.left);
		rightDriveBaseFront.set(-signal.right);
		rightDriveBaseRear.set(-signal.right);
	}

	@Override
	public void update() {
		if(controller != null) {
			setDriveOutputs(controller.update(getStateToContinueFrom()));
		}
	}
	
  private DriveState getStateToContinueFrom() {
    if (controller == null) {
      return getCurrentState();
    } else if (controller instanceof DriveStraightController && ((DriveStraightController)controller).isOnTarget() ) {
      return ((DriveStraightController)controller).getCurrentState();
    } else {
      return getCurrentState();
    }
  }
	
	/*private DriveState getPoseToContinueFrom(boolean for_turn_controller) {
    if (!for_turn_controller && controller instanceof TurnInPlaceController) {
        DriveState pose_to_use = getCurrentState();
        pose_to_use.m_heading = ((TurnInPlaceController) controller).getHeadingGoal();
        pose_to_use.m_heading_velocity = 0;
        return pose_to_use;
    } else if (controller == null || (controller instanceof DriveStraightController && for_turn_controller)) {
        return getCurrentState();
    }
}*/ //TODO finish this shit

	public DriveState getCurrentState() {
		cachedState.reset(HardwareAdaptor.kLeftDriveEncoder.get(),
				HardwareAdaptor.kRightDriveEncoder.get(), 
				HardwareAdaptor.kLeftDriveEncoder.getRate(), 
				HardwareAdaptor.kRightDriveEncoder.getRate(), 0, 0); //TODO update with values from sensor readings
		return cachedState;
	}


	public CANTalon getLeftFront() {
		return leftDriveBaseFront;
	}

	public CANTalon getLeftRear() {
		return leftDriveBaseRear;
	}

	public CANTalon getRightFront() {
		return rightDriveBaseFront;
	}

	public CANTalon getRightRear() {
		return rightDriveBaseRear;
	}

}
