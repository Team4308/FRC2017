package org.usfirst.frc.team4308.robot.subsystems;

import org.usfirst.frc.team4308.robot.RobotMap;
import org.usfirst.frc.team4308.robot.commands.JoystickControl;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {

	private final SpeedController leftAlpha;
	private final SpeedController leftBeta;
	private final SpeedController rightAlpha;
	private final SpeedController rightBeta;
	private final RobotDrive drive;
	
	 // TODO: Constructors for inputs of name, speed controllers, and integer channels
	public DriveTrain() { 
		leftAlpha = new CANTalon(RobotMap.MOTORS.frontLeft);
		leftBeta = new CANTalon(RobotMap.MOTORS.backLeft);
		rightAlpha = new CANTalon(RobotMap.MOTORS.frontRight);
		rightBeta = new CANTalon(RobotMap.MOTORS.backRight);
		drive = new RobotDrive(leftAlpha, leftBeta, rightAlpha, rightBeta);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new JoystickControl());
	}

	public void arcadeDrive(Joystick stick) {
		drive.arcadeDrive(stick);
	}

	public void arcadeDrive(double moveValue, double rotateValue) {
		drive.arcadeDrive(moveValue, rotateValue);
	}

	public void tankDrive(Joystick stick) {
		drive.tankDrive(-stick.getRawAxis(1), -stick.getRawAxis(3));
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

}
