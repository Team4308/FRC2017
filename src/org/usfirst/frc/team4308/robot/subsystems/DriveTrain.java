package org.usfirst.frc.team4308.robot.subsystems;

import org.usfirst.frc.team4308.robot.RobotMap;
import org.usfirst.frc.team4308.robot.commands.TankControl;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Controller for the drive train and its motors
 * 
 * @author mike_
 *
 */
public class DriveTrain extends Subsystem {
	
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
	public DriveTrain() {
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
		
		leftEncoder.setDistancePerPulse(pulseDistance);
		rightEncoder.setDistancePerPulse(pulseDistance);

		LiveWindow.addActuator("Drive Train", "Front Left Motor", (Talon) leftAlpha);
		LiveWindow.addActuator("Drive Train", "Back Left Motor", (Talon) leftBeta);
		LiveWindow.addActuator("Drive Train", "Front Right Motor", (Talon) rightAlpha);
		LiveWindow.addActuator("Drive Train", "Back Right Motor", (Talon) rightBeta);
		LiveWindow.addSensor("Drive Train", "Left Encoder", leftEncoder);
		LiveWindow.addSensor("Drive Train", "Right Encoder", rightEncoder);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new TankControl());
	}

	public void arcadeDrive(Joystick stick) {
		drive.arcadeDrive(stick);
	}

	public void arcadeDrive(double moveValue, double rotateValue) {
		drive.arcadeDrive(moveValue, rotateValue);
	}

	public void tankDrive(Joystick stick) {
		drive.tankDrive(stick.getRawAxis(1), stick.getRawAxis(3));
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

}
