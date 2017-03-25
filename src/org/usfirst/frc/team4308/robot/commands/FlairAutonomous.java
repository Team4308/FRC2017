package org.usfirst.frc.team4308.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class FlairAutonomous extends CommandGroup {

	public FlairAutonomous() {
		super();
		addSequential(new DriveAngular());
		addSequential(new DriveLinear());
		addSequential(new DriveAngular(-0.0));
		addSequential(new DriveLinear(-10));
	}

}
