package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class PneumaticsToggle extends InstantCommand {

	public PneumaticsToggle() {
		super();
		requires(Robot.pneumatics);
	}

	@Override
	protected void execute() {
		super.execute();

		if (Robot.pneumatics != null) {
			if (Robot.pneumatics.isRunning()) {
				Robot.pneumatics.stop();
			} else {
				Robot.pneumatics.start();
			}
		}
	}
}
