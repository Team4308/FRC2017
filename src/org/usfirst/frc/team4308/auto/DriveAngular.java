package org.usfirst.frc.team4308.auto;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.RobotMap;
import org.usfirst.frc.team4308.robot.io.IO;
import org.usfirst.frc.team4308.util.PIDContainer;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Autonomous command responsible for bi-directional linear movement of the robot, determined by either user-specified distances or by default values (specified in {@link IO}).
 * 
 * @author Michael Brown
 *
 */
public class DriveAngular extends Command {

	PIDController moveController;
	PIDController turnController;

	PIDContainer moveInput;
	PIDContainer moveOutput;
	PIDContainer turnInput;
	PIDContainer turnOutput;

	private final double angle;
	public static double desiredAngle;

	public DriveAngular() {
		this(RobotMap.Autonomous.defaultDistance, 0);
	}

	public DriveAngular(double timeout, double desiredAngle) {
		super(timeout);
		requires(Robot.drive);
		// requires(Robot.gyro);

		moveInput = new PIDContainer(PIDSourceType.kDisplacement);
		moveOutput = new PIDContainer(PIDSourceType.kDisplacement);
		turnInput = new PIDContainer(PIDSourceType.kDisplacement);
		turnOutput = new PIDContainer(PIDSourceType.kDisplacement);

		moveController = new PIDController(RobotMap.kProportional, RobotMap.kIntegral, RobotMap.kDifferential, moveInput, moveOutput);
		turnController = new PIDController(RobotMap.kProportional, RobotMap.kIntegral, RobotMap.kDifferential, turnInput, turnOutput);

		angle = Robot.gyro.heading();
		turnController.setSetpoint(desiredAngle);
	}

	@Override
	protected void execute() {
		super.execute();
	}

	@Override
	protected void end() {
		if (Robot.drive != null)
			Robot.drive.resetEncoder();
		super.end();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void interrupted() {
		super.interrupted();
		end();
	}

}
