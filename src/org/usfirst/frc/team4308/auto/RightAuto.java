package org.usfirst.frc.team4308.auto;

import org.usfirst.frc.team4308.robot.commands.ClawSwitch;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RightAuto extends CommandGroup {

	public RightAuto() {
		super();

		// Rotate to face right of airship
		addSequential(new DriveAngular(15, 45));

		// Travel to Avoid Airship
		addParallel(new DriveAngular(15, 45));
		addParallel(new DriveLinear(15, 1));

		// Rotate to Face Forward
		addSequential(new DriveAngular(15, 0));

		// Pass Baseline
		addParallel(new DriveAngular(15, 0));
		addParallel(new DriveLinear(15, 1));
	}

}
