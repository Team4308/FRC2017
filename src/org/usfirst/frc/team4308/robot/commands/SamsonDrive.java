package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 * Continuous command that emulates the {@link TankDrive} control scheme, but delegates the turning control to a cubic power curve, rather than the square curve of the {@link RobotDrive}.
 * 
 * @author Samson Close
 *
 */
public class SamsonDrive extends OperatorDrive {

	public SamsonDrive() {
		super();
	}

	@Override
	protected void execute() {
		if (Robot.io != null && Robot.io.isAvailable()) {
			Joystick joy = Robot.io.getJoystick();
			double input = joy.getRawAxis(RobotMap.Control.Standard.leftY);
			//double curvedInput = input * input * input;

			double rightX = joy.getRawAxis(RobotMap.Control.Standard.rightX);
			double leftValue = input - rightX;
			double rightValue = input + rightX;
			Robot.drive.setLeftRightMotorOutputs(leftValue, rightValue);
		}
	}

}
