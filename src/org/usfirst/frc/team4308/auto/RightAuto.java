package org.usfirst.frc.team4308.auto;

import org.usfirst.frc.team4308.robot.commands.ClawSwitch;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RightAuto extends CommandGroup {

	public RightAuto() {
		super();

		// Closes the claw (just in case the double solenoid is left open)
		addSequential(new ClawSwitch(false));

		double left = 0.37;
		double right = 0.22;

		// Move forward slowly for 4 seconds
		addSequential(new EasyAutonomous(4, left, right));

		// Wait 1 second
		addSequential(new EasyAutonomous(1, 0, 0));

		// Rotate to Face Right
		addSequential(new EasyAutonomous(2, left * 4, 0));

		// Wait 1 second
		addSequential(new EasyAutonomous(1, 0, 0));

		// Move forward slowly for 4 seconds
		addSequential(new EasyAutonomous(4, left, right));

		// Wait 1 second
		addSequential(new EasyAutonomous(1, 0, 0));

		// Rotate back straight for 2 seconds
		addSequential(new EasyAutonomous(2, 0, right * 4));

		// Pass Baseline
		addSequential(new EasyAutonomous(4, left, right));

		// addSequential(new DriveAngular());
		// addSequential(new DriveLinear());
		// addSequential(new DriveAngular(-0.0));
		// addSequential(new DriveLinear(-10));
	}

}
