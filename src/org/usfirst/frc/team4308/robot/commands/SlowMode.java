package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class SlowMode extends InstantCommand {

	private static boolean slow = false;
	private static final double SLOW_SPEED = 0.42;
	private static final double FAST_SPEED = 1.0;

	private DriveTrain drive;

	public SlowMode(DriveTrain drive) {
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
