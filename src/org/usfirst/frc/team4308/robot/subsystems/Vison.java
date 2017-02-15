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
	
	protected final String host;
	protected final int height;
	protected final int width;

	public Vison(String host, int width, int height) {
		super();
		this.width = width;
		this.height = height;
		this.host = host;
	}

	public Vison(String name, String host, int width, int height) {
		super(name);
		this.width = width;
		this.height = height;
		this.host = host;
	}

	public abstract boolean initialize();

	public abstract boolean start();

	public abstract boolean stop();

}
