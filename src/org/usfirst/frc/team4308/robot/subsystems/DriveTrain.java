package org.usfirst.frc.team4308.robot.subsystems;

import org.usfirst.frc.team4308.robot.RobotMap;
import org.usfirst.frc.team4308.util.Loggable;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Controller for the drive train and its motors
 * 
 * @author mike_
 *
 */
@Deprecated
public class DriveTrain extends Subsystem implements Loggable {

	private static final double pulseDistance = 0.042;

	private final SpeedController leftAlpha;
	private final SpeedController leftBeta;
	private final SpeedController rightAlpha;
	private final SpeedController rightBeta;
	private final RobotDrive drive;
	private Encoder leftEncoder;
	private Encoder rightEncoder;

	// TODO: Constructors for inputs of name, speed controllers, and integer
	// channels
	// TODO: instantiation of correct encoders
	public DriveTrain() {
		super();
		leftAlpha = new CANTalon(RobotMap.DRIVE.frontLeft);
		leftBeta = new CANTalon(RobotMap.DRIVE.backLeft);
		rightAlpha = new CANTalon(RobotMap.DRIVE.frontRight);
		rightBeta = new CANTalon(RobotMap.DRIVE.backRight);

		drive = new RobotDrive(leftAlpha, leftBeta, rightAlpha, rightBeta);
		drive.setSafetyEnabled(true);
		drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
		drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
		drive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
		drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);

		// leftEncoder = new Encoder(RobotMap.DRIVE.leftChannelA,
		// RobotMap.DRIVE.leftChannelB);
		// rightEncoder = new Encoder(RobotMap.DRIVE.rightChannelA,
		// RobotMap.DRIVE.rightChannelB);
		// leftEncoder.setDistancePerPulse(pulseDistance);
		// rightEncoder.setDistancePerPulse(pulseDistance);

		// LiveWindow.addSensor("Drive Train", "Left Encoder", leftEncoder);
		// LiveWindow.addSensor("Drive Train", "Right Encoder", rightEncoder);
	}

	@Override
	protected void initDefaultCommand() { // TODO: properly grab control scheme
											// from RobotMap
		// TODO setDefaultCommand(Robot.oi.controlScheme);
	}

	public void arcadeDrive(Joystick stick) {
		drive.arcadeDrive(stick);
	}

	public void arcadeDrive(double moveValue, double rotateValue) {
		drive.arcadeDrive(moveValue, rotateValue);
	}

	public void tankDrive(Joystick stick) {
		drive.tankDrive(stick.getRawAxis(1), stick.getRawAxis(5));
	}

	public void tankDrive(double leftValue, double rightValue) {
		drive.tankDrive(leftValue, rightValue);
	}

	public void drive(double magnitude, double curve) {
		drive.drive(magnitude, curve);
	}

	public void stop() {
		drive.stopMotor();
	}

	@Override
	public void log() {
		SmartDashboard.putNumber("Left Distance", leftEncoder.getDistance());
		SmartDashboard.putNumber("Right Distance", rightEncoder.getDistance());
		SmartDashboard.putNumber("Left Speed", leftEncoder.getRate());
		SmartDashboard.putNumber("Right Speed", rightEncoder.getRate());
	}

	public void reset() {
		leftEncoder.reset();
		rightEncoder.reset();
	}

	public double getDistance() {
		return (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2;
	}

	public Encoder getLeftEncoder() {
		return leftEncoder;
	}

	public Encoder getRightEncoder() {
		return rightEncoder;
	}

	public void setLimit(double limit) {
		drive.setMaxOutput(limit);
	}
}
