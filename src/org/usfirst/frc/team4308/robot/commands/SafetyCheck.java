package org.usfirst.frc.team4308.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SafetyCheck extends CommandGroup {

	public SafetyCheck() {
		addSequential(new PowerCheck());
	}

}
