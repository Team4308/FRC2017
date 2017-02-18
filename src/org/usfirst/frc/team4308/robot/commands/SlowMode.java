package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.subsystems.DriveSamson;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class SlowMode extends InstantCommand {

	private static boolean slow = false;

	private DriveSamson drive;

	public SlowMode(DriveSamson drive) {
		super();
		this.drive = drive;
		requires(drive);
	}

	@Override
	protected void execute() {
		drive.setMaxOutput(slow ? 0.5 : 1.0);
		slow = !slow;
		end();
	}

}
