package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PneumaticsToggle extends ToggleCommand {

	public PneumaticsToggle() {
		super(true);
		requires(Robot.pneumatics);
	}

	@Override
	protected void toggleOn() {
		Robot.pneumatics.start();

		SmartDashboard.putBoolean("DB/LED 0", isToggled());
	}

	@Override
	protected void toggleOff() {
		Robot.pneumatics.stop();

		SmartDashboard.putBoolean("DB/LED 0", isToggled());
	}
}
