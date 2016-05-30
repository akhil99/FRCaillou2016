package org.usfirst.frc.team115.lib;


import org.usfirst.frc.team115.robot.Constants;
import org.usfirst.frc.team115.robot.controllers.Controller;

import edu.wpi.first.wpilibj.CANTalon;

public class DriveBase extends Loopable{

  private CANTalon leftDriveBaseFront;
  private CANTalon leftDriveBaseRear;

  private CANTalon rightDriveBaseFront;
  private CANTalon rightDriveBaseRear;

  private DriveController controller;
  
  private DriveState cachedState = new DriveState(0, 0, 0, 0, 0, 0);
  
  public abstract class DriveController extends Controller {

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

  public void setDriveOutputs(DriveSignal signal) {
    leftDriveBaseFront.set(signal.left);
    leftDriveBaseRear.set(signal.left);
    rightDriveBaseFront.set(-signal.right);
    rightDriveBaseRear.set(-signal.right);
  }

	@Override
	public void update() {
		if(controller != null) {
			setDriveOutputs(controller.update(getCurrentState()));
		}
	}
	
	public DriveState getCurrentState() {
    cachedState.reset(0, 0, 0, 0, 0, 0); //TODO update with values from sensor readings
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
