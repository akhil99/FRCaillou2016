package org.usfirst.frc.team115.lib;


import org.usfirst.frc.team115.robot.Constants;
import org.usfirst.frc.team115.robot.controllers.Controller;

import edu.wpi.first.wpilibj.CANTalon;

public class DriveBase extends Loopable{

  private CANTalon leftDriveBaseFront;
  private CANTalon leftDriveBaseRear;

  private CANTalon rightDriveBaseFront;
  private CANTalon rightDriveBaseRear;

  private Controller controller;
  
  public DriveBase() {

    leftDriveBaseFront = new CANTalon(Constants.kLeftDriveFront);
    leftDriveBaseRear = new CANTalon(Constants.kLeftDriveRear);
    rightDriveBaseFront = new CANTalon(Constants.kRightDriveFront);
    rightDriveBaseRear = new CANTalon(Constants.kRightDriveRear);
  }
  
  public void setOpenLoop(double left, double right) {
  	controller = null;
  	setDriveOutputs(left, right);
  }

  public void setDriveOutputs(double left, double right) {
    leftDriveBaseFront.set(left);
    leftDriveBaseRear.set(left);
    rightDriveBaseFront.set(-right);
    rightDriveBaseRear.set(-right);
  }

	@Override
	public void update() {
		if(controller != null) {
			controller.update();
		}
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
