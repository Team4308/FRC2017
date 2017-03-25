package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PneumaticsToggle extends InstantCommand {

	public static boolean isEnabled = true;

	public PneumaticsToggle() {
		super();
		requires(Robot.pneumatics);
		realExec();
	}

	@Override
	protected void execute() {
		super.execute();
		realExec();
	}

	private void realExec() {
		isEnabled = !isEnabled;

		DriverStation.reportWarning("Enabling Pneumatics: " + isEnabled, false);
		
		if (isEnabled) {
			Robot.pneumatics.start();
		} else {
			Robot.pneumatics.stop();
		}

		SmartDashboard.putBoolean("DB/LED 0", isEnabled);
	}
}
