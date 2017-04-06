package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PneumaticsToggle extends InstantCommand {

	public PneumaticsToggle() {
		super();
		requires(Robot.pneumatics);
	}

	@Override
	protected void execute() {
		super.execute();

		Robot.pneumatics.toggle();
	}
}
