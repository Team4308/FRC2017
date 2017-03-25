package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;

import edu.wpi.first.wpilibj.RobotDrive;

/**
 * Continuous command that emulates the {@link TankDrive} control scheme, but
 * delegates the turning control to a cubic power curve, rather than the square curve of the {@link RobotDrive}.
 * 
 * @author Samson Close
 *
 */
public class SamsonDrive extends OperatorDrive {

	public SamsonDrive() {
		super();
		requires(Robot.drive);
	}

	@Override
	protected void execute() {
		double input = Robot.io.getJoystick().getRawAxis(Robot.io.getTurnAxis());
		double curvedInput = input * input * input;
		double leftValue = Robot.io.getLeftValue() + curvedInput;
		double rightValue = Robot.io.getRightValue() - curvedInput;
		Robot.drive.setLeftRightMotorOutputs(leftValue, rightValue);
	}
	
}
