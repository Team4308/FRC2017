package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class SlowMode extends InstantCommand {

	private static boolean slow = false;
	private static final double SLOW_SPEED = 0.42;
	private static final double FAST_SPEED = 1.0;

	public SlowMode() {
		super();
		requires(Robot.drive);
	}

	@Override
	protected void execute() {
		Robot.drive.setMaxOutput(slow ? SLOW_SPEED : FAST_SPEED);
		slow = !slow;
		end();
	}

}
