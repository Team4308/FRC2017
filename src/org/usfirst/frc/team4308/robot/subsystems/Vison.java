package org.usfirst.frc.team4308.robot.subsystems;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.VideoCamera;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Abstract class allowing for multiple types of video sources and outputs used
 * in vision processing and use
 * 
 * @author Michael
 *
 */
public abstract class Vison extends Subsystem {

	// TODO: more access to components of camera

	protected Thread visionThread;
	protected VideoCamera camera;
	protected CvSink cvsink;
	protected CvSource outputStream;

	public Vison() {
		super();
	}

	/**
	 * Used for grabbing camera feed and throwing video processing into a
	 * different thread.
	 * 
	 * @return
	 */
	public abstract boolean initialize();

	/**
	 * Unpauses or starts the thread responsible for vision.
	 * 
	 * @return Whether the thread starts properly.
	 */
	public synchronized boolean start() {
		try {
			if (visionThread.isAlive()) {
				visionThread.notify();
			} else {
				visionThread.start();
			}
		} catch (IllegalThreadStateException itse) {
			return false;
		}
		return true;
	}

	/**
	 * Pauses the thread responsible for vision, does not destroy thread.
	 * 
	 * @return Whether the thread stopped properly.
	 */
	public synchronized boolean stop() {
		try {
			visionThread.wait();
		} catch (IllegalThreadStateException | InterruptedException e) {
			DriverStation.reportError(e.getMessage(), true);
			return false;
		}
		return true;
	}

}
