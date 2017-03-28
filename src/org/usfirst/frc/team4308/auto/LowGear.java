package org.usfirst.frc.team4308.auto;

import org.usfirst.frc.team4308.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class LowGear extends InstantCommand {

	public LowGear() {
		super();
		requires(Robot.drive);
	}

	@Override
	protected void execute() {
		super.execute();
		if (Robot.drive != null)
			Robot.drive.lowGear();
	}

}
