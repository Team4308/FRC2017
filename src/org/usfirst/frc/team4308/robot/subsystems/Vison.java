package org.usfirst.frc.team4308.robot.subsystems;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.VideoCamera;
import edu.wpi.first.wpilibj.command.Subsystem;

public abstract class Vison extends Subsystem {

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
