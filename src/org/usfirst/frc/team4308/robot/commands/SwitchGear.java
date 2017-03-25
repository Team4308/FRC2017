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
		Robot.drive.highGear();
	}

	@Override
	protected void toggleOff() {
		Robot.drive.lowGear();
	}

}
