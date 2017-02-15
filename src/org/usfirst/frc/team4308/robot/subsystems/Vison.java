package org.usfirst.frc.team4308.robot.subsystems;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.VideoCamera;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Abstract class allowing for multiple types of video sources and outputs used in vision processing and use
 * @author Michael
 *
 */
public abstract class Vison extends Subsystem {
	
	// TODO: more access to components of camera
	// TODO: separate thread instantiation (remove block statement, replace with safer modules)

	protected Thread visionThread;
	protected VideoCamera camera;
	protected CvSink cvsink;
	protected CvSource outputStream;

	public Vison() {
		super();
	}

	public Vison(String name) {
		super(name);
	}

	public abstract boolean initialize();

	public abstract boolean start();

	public abstract boolean stop();

}
