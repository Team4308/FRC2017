package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class OperatorDrive extends Command {

	public OperatorDrive() {
		super(RobotMap.kTeleoperatedTime);
		requires(Robot.drive);
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
