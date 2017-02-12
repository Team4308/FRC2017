package org.usfirst.frc.team4308.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Vison extends Subsystem {
	
	Thread visionThread;

	public Vison() {
		// TODO: Initializing vision thread
		// TODO: controlling camera connection errors
		// TODO: throwing errors to the driver
	}

	public Vison(String name) {
		super(name);
		
	}

	@Override
	protected void initDefaultCommand() {
		

	}

}
