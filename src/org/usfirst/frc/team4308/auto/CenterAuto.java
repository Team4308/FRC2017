package org.usfirst.frc.team4308.auto;

import org.usfirst.frc.team4308.robot.commands.ClawSwitch;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterAuto extends CommandGroup {

	public CenterAuto() {
		super();

		// Closes the claw (just in case the double solenoid is left open)
		addSequential(new ClawSwitch(false));

		// Wait for pressure
		addSequential(new EasyAutonomous(4, 0, 0));

		addParallel(new DriveAngular(15,0));
		addParallel(new DriveLinear(15, 1.8));
		addSequential(new OpenClaw());
		addParallel(new DriveAngular(15,0));
		addParallel(new DriveLinear(15, -1.8));
	}

}
