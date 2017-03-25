package org.usfirst.frc.team4308.robot.subsystems;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.VideoCamera;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Abstract class allowing for multiple types of video sources and outputs used in vision processing and use
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

	public abstract boolean initialize();

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
