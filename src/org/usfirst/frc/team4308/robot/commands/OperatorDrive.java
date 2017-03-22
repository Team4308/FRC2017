package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class OperatorDrive extends Command {

	public OperatorDrive() {
		super();
		requires(Robot.drive);
		if (!Robot.operatorControl) {
			end();
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void end() {
		Robot.drive.stopMotor();
	}

}
