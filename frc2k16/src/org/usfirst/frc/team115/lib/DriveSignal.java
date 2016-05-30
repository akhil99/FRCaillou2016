package org.usfirst.frc.team115.lib;

public class DriveSignal {
	
    public double left;
    public double right;

    public DriveSignal(double left, double right) {
        this.left = left;
        this.right = right;
    }

    public static DriveSignal NEUTRAL = new DriveSignal(0, 0);
}
