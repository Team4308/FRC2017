package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class WaitForPressure extends Command {

	public WaitForPressure() {
		super();
		requires(Robot.pneumatics);
	}

	@Override
	protected void initialize() {
		super.initialize();
		Robot.pneumatics.start();
	}

	@Override
	protected boolean isFinished() {
		return Robot.pneumatics.isPressurized();
	}

}
