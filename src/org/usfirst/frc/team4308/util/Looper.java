package org.usfirst.frc.team4308.util;

import java.util.ArrayList;
import java.util.List;

public interface Looper {

	public List<Loop> loops = new ArrayList<Loop>();

	public default void start() {
		for (Loop loop : loops) {
			loop.start();
		}
	}

	public default void stop() {
		for (Loop loop : loops) {
			loop.stop();
		}
	}

	public default void loop() {
		for (Loop loop : loops) {
			loop.loop();
		}
	}

}
