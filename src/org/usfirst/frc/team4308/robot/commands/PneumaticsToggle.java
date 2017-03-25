package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PneumaticsToggle extends InstantCommand {

	public static boolean isEnabled = false;

	public PneumaticsToggle() {
		requires(Robot.pneumatics);
	}

	@Override
	protected void execute() {
		super.execute();

		isEnabled = !isEnabled;

		if (isEnabled) {
			Robot.pneumatics.start();
		} else {
			Robot.pneumatics.stop();
		}
		
		SmartDashboard.putBoolean("DB/LED 0", isEnabled);
	}
}
