package org.usfirst.frc.team4308.auto;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.RobotMap;
import org.usfirst.frc.team4308.robot.io.IO;
import org.usfirst.frc.team4308.util.PIDContainer;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Autonomous command responsible for bi-directional linear movement of the
 * robot, determined by either user-specified distances or by default values
 * (specified in {@link IO}).
 * 
 * @author Michael Brown
 *
 */
public class DriveLinear extends Command {

	PIDController moveController;

	PIDContainer moveInput;
	PIDContainer moveOutput;

	// private final double angle;

	public DriveLinear(double timeout, double distance) {
		super(timeout);
		requires(Robot.drive);
		requires(Robot.gyro);

		moveInput = new PIDContainer(PIDSourceType.kDisplacement);
		moveOutput = new PIDContainer(PIDSourceType.kDisplacement);

		moveController = new PIDController(RobotMap.kProportional, RobotMap.kIntegral, RobotMap.kDifferential,
				moveInput, moveOutput);
		moveController.setAbsoluteTolerance(RobotMap.Autonomous.distancePercentTolerance);
		moveController.setSetpoint(distance);
	}

	@Override
	protected void initialize() {
		super.initialize();
		Robot.gyro.reset();
		moveController.enable();
	}
	
	@Override
	protected void execute() {
		super.execute();
		double displacement = Math.sqrt(Robot.gyro.xDistance()*Robot.gyro.xDistance()) + (Robot.gyro.yDistance()*Robot.gyro.yDistance());
		moveInput.pidWrite(displacement);
		Robot.drive.driveHandler.arcadeDrive(moveOutput.pidGet(), 0, false);
	}

	@Override
	protected void end() {
		super.end();
		Robot.drive.stopMotor();
		moveController.disable();
	}

	@Override
	protected boolean isFinished() {
		return moveController.onTarget();
	}

	@Override
	protected void interrupted() {
		super.interrupted();
		end();
	}

}
