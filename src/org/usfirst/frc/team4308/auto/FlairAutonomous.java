package org.usfirst.frc.team4308.auto;

import org.usfirst.frc.team4308.robot.commands.ClawSwitch;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class FlairAutonomous extends CommandGroup {

	public FlairAutonomous() {
		super();

		// Closes the claw (just in case the double solenoid is left open)
		addSequential(new ClawSwitch(false));

		// Wait for pressure
		addSequential(new EasyAutonomous(4, 0, 0));

		double left = 0.37;
		double right = 0.22;

		// Move forward slowly for 2 seconds
		addSequential(new EasyAutonomous(4, left, right));

		// Wait for 2 seconds
		addSequential(new EasyAutonomous(2, 0, 0));

		// Release claw
		addSequential(new ClawSwitch());

		// Wait for 2 seconds
		addSequential(new EasyAutonomous(2, 0, 0));

		// Move backwards for 1 second
		addSequential(new EasyAutonomous(1, -left, -right));
		
		// Closes claw
		addSequential(new ClawSwitch());

		// addSequential(new DriveAngular());
		// addSequential(new DriveLinear());
		// addSequential(new DriveAngular(-0.0));
		// addSequential(new DriveLinear(-10));
	}

}
