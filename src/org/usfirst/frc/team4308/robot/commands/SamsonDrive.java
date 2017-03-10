package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Continuous command that emulates the {@link TankDrive} control scheme, but
 * delegates the turning control to a power curve, rather than the linear input
 * of the joystick.
 * 
 * @author Samson Close
 *
 */
public class SamsonDrive extends Command {

	private static final int power = 3;

	public SamsonDrive() {
		super();
		requires(Robot.drive);
	}

	@Override
	protected void execute() {
		double curvedInput = Math.pow(Robot.oi.getJoystick().getRawAxis(Robot.oi.getTurnAxis()), power);
		double leftValue = Robot.oi.getLeftValue() + curvedInput;
		double rightValue = Robot.oi.getRightValue() - curvedInput;
		Robot.drive.setLeftRightMotorOutputs(leftValue, rightValue);
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
