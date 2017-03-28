package org.usfirst.frc.team4308.auto;

import org.usfirst.frc.team4308.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class EasyAutonomous extends Command {

	private double leftMotor;
	private double rightMotor;

	public EasyAutonomous(double timeout, double leftMotor, double rightMotor) {
		super(timeout);
		requires(Robot.drive);

		this.leftMotor = leftMotor;
		this.rightMotor = rightMotor;
	}

	@Override
	protected void execute() {
		super.execute();

		if (Robot.drive != null)
			Robot.drive.setLeftRightMotorOutputs(-leftMotor, -rightMotor);
	}

	@Override
	protected void end() {
		super.end();
		if (Robot.drive != null)
			Robot.drive.stopMotor();
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

}
