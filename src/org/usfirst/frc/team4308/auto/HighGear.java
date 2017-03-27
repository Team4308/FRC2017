package org.usfirst.frc.team4308.auto;

import org.usfirst.frc.team4308.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class HighGear extends InstantCommand {

	public HighGear() {
		super();
		requires(Robot.drive);
	}
	
	@Override
	protected void execute() {
		super.execute();
		Robot.drive.highGear();
	}


}
