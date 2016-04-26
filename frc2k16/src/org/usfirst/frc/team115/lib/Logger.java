package org.usfirst.frc.team115.lib;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;

import org.usfirst.frc.team115.robot.HardwareAdaptor;
import org.usfirst.frc.team115.robot.subsystems.Flywheel;
import org.usfirst.frc.team115.robot.subsystems.Intake;
import org.usfirst.frc.team115.robot.subsystems.Punch;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;

public class Logger extends Loopable {

	PrintWriter writer;
	
	private double old;
	
	Flywheel rightFlywheel = HardwareAdaptor.kRightFlywheel;
	Flywheel leftFlywheel = HardwareAdaptor.kLeftFlywheel;
	Punch punch = HardwareAdaptor.kPunch;
	Intake intake = HardwareAdaptor.kIntake;
	
	public Logger(String fileName) {
		try {
			writer = new PrintWriter(new File(fileName));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void update() {
		double time = Timer.getFPGATimestamp();
		writer.println("[" + new Date().getTime() + "], " + "FPGA, Time, " + time);
		writer.println("[" + new Date().getTime() + "], " + "Delta Time, " + (time - old));
		writer.println("[" + new Date().getTime() + "], " + "General, Battery Voltage, " + DriverStation.getInstance().getBatteryVoltage());
		writer.println("[" + new Date().getTime() + "], " + "General, Match Time, " + DriverStation.getInstance().getMatchTime());
		writer.println("[" + new Date().getTime() + "], " + "Left Flywheel, Log, " + leftFlywheel.getLog());
		writer.println("[" + new Date().getTime() + "], " + "Right Flywheel, Log, " + rightFlywheel.getLog());
		writer.println("[" + new Date().getTime() + "], " + "Punch, Log, " + punch.getLog());
		writer.println("[" + new Date().getTime() + "], " + "Intake, Log, " + intake.getLog());
		old = time;
	}
	
	public void close() {
		writer.flush();
		writer.close();
	}

}
