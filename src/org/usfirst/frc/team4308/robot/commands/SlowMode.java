package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.subsystems.DriveSamson;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class SlowMode extends InstantCommand {

	private static boolean slow = false;
	private static final double SLOW_SPEED = 0.3;
	private static final double FAST_SPEED = 1.0;

	private DriveSamson drive;

	public SlowMode(DriveSamson drive) {
		super();
		this.drive = drive;
		requires(drive);
	}

	@Override
	protected void execute() {
		drive.setMaxOutput(slow ? SLOW_SPEED : FAST_SPEED);
		slow = !slow;
		end();
	}

}
