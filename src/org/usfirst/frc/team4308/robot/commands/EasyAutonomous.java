package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class EasyAutonomous extends Command {

	private double leftMotor;
	private double rightMotor;

	public EasyAutonomous(double timeout, double leftMotor, double rightMotor) {
		super(timeout);
		
		this.leftMotor = leftMotor;
		this.rightMotor = rightMotor;
	}

	@Override
	protected void execute() {
		super.execute();

		Robot.drive.setLeftRightMotorOutputs(-leftMotor, -rightMotor);
	}

	@Override
	protected void end() {
		super.end();
		Robot.drive.stopMotor();
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

}
