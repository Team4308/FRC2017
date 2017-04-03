package org.usfirst.frc.team4308.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestAutonomous extends CommandGroup {

	public TestAutonomous() {
		super();

		// Rotate 90 degrees
		addSequential(new DriveAngular(5, 90));
//		
//		// Wait
//		addSequential(new EasyAutonomous(4, 0, 0));
//		
//		// Move forward 2m
//		addSequential(new DriveLinear(5, 2));
		
//		double left = 0.37;
//		double right = 0.22;
//		
//		addSequential(new EasyAutonomous(4, left, right));
//
//		// Wait 1 second
//		addSequential(new EasyAutonomous(1, 0, 0));
		
		/*
		// Closes the claw (just in case the double solenoid is left open)
		addSequential(new ClawSwitch(false));

		// Wait for pressure
		addSequential(new EasyAutonomous(4, 0, 0));

		double left = 0.37;
		double right = 0.22;

		// Move forward slowly for seconds
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
		 * */
	}

}
