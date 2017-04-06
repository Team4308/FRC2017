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
public class DriveAngular extends Command {

	PIDController turnController;
	PIDContainer turnInput;
	PIDContainer turnOutput;

	public DriveAngular(double timeout, double desiredAngle) {
		super(timeout);
		requires(Robot.drive);
		requires(Robot.gyro);

		turnInput = new PIDContainer(PIDSourceType.kDisplacement);
		turnOutput = new PIDContainer(PIDSourceType.kDisplacement);

		turnController = new PIDController(RobotMap.kProportional, RobotMap.kIntegral, RobotMap.kDifferential,
				turnInput, turnOutput);
		turnController.setAbsoluteTolerance(RobotMap.Autonomous.angularToleranceDegrees);
		turnController.setOutputRange(-0.27, 0.27);
		turnController.setContinuous(true);

		turnController.setSetpoint(desiredAngle);
	}

	private double startAngle;

	@Override
	protected void initialize() {
		super.initialize();
		turnController.reset();
		Robot.gyro.reset();
		startAngle = Robot.gyro.angle();
		turnController.enable();
		
		Robot.println("Initializing with start value of : " + startAngle);
	}
	
	@Override
	protected void execute() {
		super.execute();
		
		double currentAngle = Robot.gyro.angle() - startAngle;
		turnInput.pidWrite(currentAngle);
		double drive = turnOutput.pidGet();
		Robot.drive.driveHandler.arcadeDrive(0, drive, false);
		
		Robot.println("Agnel: " + Robot.gyro.angle());
		Robot.println("GyroValue: " + currentAngle);
		Robot.println("rotating: " + drive);
		
	}

	@Override
	protected void end() {
		super.end();
		Robot.drive.stopMotor();
		turnController.disable();
	}

	@Override
	protected boolean isFinished() {
		return turnController.onTarget();
	}

	@Override
	protected void interrupted() {
		super.interrupted();
		end();
	}

}
