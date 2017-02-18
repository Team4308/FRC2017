package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class FlairAutonomous extends CommandGroup {

	public FlairAutonomous() {
		addSequential(new DriveAngular(Robot.drive, 0.0));
		addSequential(new DriveLinear(Robot.drive));
		addSequential(new DriveAngular(Robot.drive, -0.0));
		addSequential(new DriveLinear(Robot.drive, -10));
	}

}
