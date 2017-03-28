package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.RobotMap;
import org.usfirst.frc.team4308.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4308.util.ValueChangeRegulator;

/**
 * Continuous command that takes the common Y axis of two analog sticks on a two joystick controller, in order to allow individual control of the left and right side motors of the {@link DriveTrain}.
 * 
 * @author Michael Brown
 *
 */
public class TankDrive extends OperatorDrive {

	private static final boolean TEST_REGULATOR = false;
	private ValueChangeRegulator leftRegulator;
	private ValueChangeRegulator rightRegulator;

	public TankDrive() {
		super();

		leftRegulator = new ValueChangeRegulator(0.05);
		rightRegulator = new ValueChangeRegulator(0.05);
	}

	@Override
	protected void execute() {
		if (Robot.io != null && Robot.io.isAvailable()) {
			double left = Robot.io.getJoystick().getRawAxis(RobotMap.Control.Standard.leftY);
			double right = Robot.io.getJoystick().getRawAxis(RobotMap.Control.Standard.rightY);

			if (TEST_REGULATOR) {
				left = leftRegulator.filter(left);
				right = rightRegulator.filter(right);
			}

			if (Robot.drive != null && Robot.drive.driveHandler != null)
				Robot.drive.driveHandler.tankDrive(left, right);
		}
	}

}
