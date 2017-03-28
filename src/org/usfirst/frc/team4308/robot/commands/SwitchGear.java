package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;

public class SwitchGear extends ToggleCommand {

	public SwitchGear() {
		super(false);
	}

	public SwitchGear(boolean startState) {
		super(startState);
		requires(Robot.drive);
	}

	@Override
	protected void toggleOn() {
		if (Robot.drive != null)
			Robot.drive.highGear();
	}

	@Override
	protected void toggleOff() {
		if (Robot.drive != null)
			Robot.drive.lowGear();
	}

}
