package org.usfirst.frc.team115.lib;

import java.util.Vector;

public class MultiLooper extends Loopable {

	Looper looper;
	Vector<Loopable> loopables = new Vector<Loopable>();

	public MultiLooper(String name, double period) {
		looper = new Looper(name, this, period);
	}

	@Override
	public void update() {
		int i;
    for (i = 0; i < loopables.size(); ++i) {
        Loopable c = loopables.elementAt(i);
        if (c != null) {
            c.update();
        }
    }
	}
	
	public void start() {
		looper.start();
	}
	
	public void stop() {
		looper.stop();
	}
	
	public void registerLoopable(Loopable loopable) {
		loopables.addElement(loopable);
	}
}
