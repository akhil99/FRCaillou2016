package org.usfirst.frc.team115.lib;

public abstract class Loopable implements Runnable{
	
	public abstract void update();
	
	public void run() {
		update();
	}

}
