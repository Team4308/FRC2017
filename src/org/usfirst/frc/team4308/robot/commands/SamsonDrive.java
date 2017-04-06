package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.RobotMap;
import org.usfirst.frc.team4308.util.ValueChangeRegulator;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 * Continuous command that emulates the {@link TankDrive} control scheme, but
 * delegates the turning control to a cubic power curve, rather than the square
 * curve of the {@link RobotDrive}.
 * 
 * @author Samson Close
 *
 */
public class SamsonDrive extends OperatorDrive {

	private ValueChangeRegulator leftRegulator;
	private ValueChangeRegulator rightRegulator;

	public SamsonDrive() {
		super();

		// leftRegulator = new ValueChangeRegulator(0.05);
		// rightRegulator = new ValueChangeRegulator(0.05);
	}

	@Override
	protected void execute() {
		if (Robot.io != null && Robot.io.isAvailable()) {
			Joystick joy = Robot.io.getJoystick();
			double input = joy.getRawAxis(RobotMap.Control.Standard.leftY);
			// double curvedInput = input * input * input;

			double rightX = joy.getRawAxis(RobotMap.Control.Standard.rightX);

			// input = leftRegulator.filter(input);
			// rightX = rightRegulator.filter(rightX);

			double leftValue = input - rightX;
			double rightValue = input + rightX;
			Robot.drive.setLeftRightMotorOutputs(leftValue, rightValue);
		}
	}

}
