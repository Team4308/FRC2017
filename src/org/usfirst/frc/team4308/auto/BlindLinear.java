package org.usfirst.frc.team4308.auto;

import org.usfirst.frc.team4308.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BlindLinear extends CommandGroup {

	public BlindLinear(double distance, double angle) {
		super();
		addParallel(new DriveAngular(15, angle));
		addParallel(new DriveLinear(15,distance));
		
	}
	
	
}
