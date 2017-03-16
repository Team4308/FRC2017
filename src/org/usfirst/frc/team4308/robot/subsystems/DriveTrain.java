package org.usfirst.frc.team4308.robot.subsystems;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.RobotMap;
import org.usfirst.frc.team4308.robot.commands.ArcadeDrive;
import org.usfirst.frc.team4308.robot.commands.SamsonDrive;
import org.usfirst.frc.team4308.robot.commands.TankDrive;
import org.usfirst.frc.team4308.util.Loggable;
import org.usfirst.frc.team4308.util.Powered;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Controller for the drive train and its motors
 *
 */
public class DriveTrain extends Subsystem implements Loggable, Powered {

	private final CANTalon leftFront;
	private final CANTalon leftBack;
	private final CANTalon rightFront;
	private final CANTalon rightBack;
	private final RobotDrive drive;
	private Encoder leftEncoder;
	private Encoder rightEncoder;

	// TODO: instantiation of correct encoders
	public DriveTrain() {
		leftFront = new CANTalon(RobotMap.Drive.frontLeft);
		leftBack = new CANTalon(RobotMap.Drive.backLeft);
		rightFront = new CANTalon(RobotMap.Drive.frontRight);
		rightBack = new CANTalon(RobotMap.Drive.backRight);

		drive = new RobotDrive(leftFront, leftBack, rightFront, rightBack);
		drive.setSafetyEnabled(true);
		drive.setSensitivity(RobotMap.Drive.curveSensitivity);

		linearInitialize();

		// TODO: find out the number system of the encoders
		leftEncoder = new Encoder(RobotMap.Drive.leftChannelA, RobotMap.Drive.leftChannelB);
		rightEncoder = new Encoder(RobotMap.Drive.rightChannelA, RobotMap.Drive.rightChannelB);
		leftEncoder.setDistancePerPulse(RobotMap.Drive.encoderPulseDistance);
		rightEncoder.setDistancePerPulse(RobotMap.Drive.encoderPulseDistance);

		LiveWindow.addActuator("Drive Train", "leftFront", leftFront);
		LiveWindow.addActuator("Drive Train", "leftBack", leftBack);
		LiveWindow.addActuator("Drive Train", "rightFront", rightFront);
		LiveWindow.addActuator("Drive Train", "rightBack", rightBack);
		LiveWindow.addSensor("Drive Train", "Left Encoder", leftEncoder);
		LiveWindow.addSensor("Drive Train", "Right Encoder", rightEncoder);
	}

	public void linearInitialize() {
		drive.setInvertedMotor(MotorType.kFrontLeft, true);
		drive.setInvertedMotor(MotorType.kRearLeft, true);
		drive.setInvertedMotor(MotorType.kFrontRight, true);
		drive.setInvertedMotor(MotorType.kRearRight, true);
	}

	public void angularInitialize() {
		drive.setInvertedMotor(MotorType.kFrontLeft, true);
		drive.setInvertedMotor(MotorType.kRearLeft, true);
		drive.setInvertedMotor(MotorType.kFrontRight, false);
		drive.setInvertedMotor(MotorType.kRearRight, false);
	}

	@Override
	protected void initDefaultCommand() {
		switch (Robot.oi.getJoystickType()) {
		case FLIGHT:
			setDefaultCommand(new ArcadeDrive());
			break;
		case STANDARD:
			setDefaultCommand(new SamsonDrive());
			break;
		default:
			setDefaultCommand(new TankDrive());
			break;
		}
	}

	public void arcadeDrive(double move, double rotate) {
		drive.arcadeDrive(move, rotate);
	}

	public void arcadeDrive(Joystick control, int moveAxis, int rotateAxis) {
		drive.arcadeDrive(control, moveAxis, control, rotateAxis);
	}

	public void drive(double magnitude, double curve) {
		drive.drive(magnitude, curve);
	}

	public void setLeftRightMotorOutputs(double leftOutput, double rightOutput) {
		drive.setLeftRightMotorOutputs(leftOutput, rightOutput);
	}

	public void tankDrive(double leftValue, double rightValue) {
		drive.tankDrive(leftValue, rightValue);
	}

	public void tankDrive(Joystick control, int leftAxis, int rightAxis) {
		drive.tankDrive(control, leftAxis, control, rightAxis);
	}

	@Override
	public void log() {
		SmartDashboard.putNumber("Left Distance", leftEncoder.getDistance());
		SmartDashboard.putNumber("Right Distance", rightEncoder.getDistance());
		SmartDashboard.putNumber("Left Speed", leftEncoder.getRate());
		SmartDashboard.putNumber("Right Speed", rightEncoder.getRate());
	}

	public void setMaxOutput(double power) {
		drive.setMaxOutput(power);
	}

	public void resetEncoders() {
		leftEncoder.reset();
		rightEncoder.reset();
	}

	public double getDistance() {
		return (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2.0;
	}

	public double getAbsoluteDistance() {
		return (Math.abs(leftEncoder.getDistance()) + Math.abs(rightEncoder.getDistance())) / 2.0;
	}

	public Encoder getLeftEncoder() {
		return leftEncoder;
	}

	public Encoder getRightEncoder() {
		return rightEncoder;
	}

	@Override
	public double voltage() {
		return (leftFront.getOutputVoltage() + leftBack.getOutputVoltage() + rightFront.getOutputVoltage()
				+ rightBack.getOutputVoltage()) / 4.0;
	}

	@Override
	public double current() {
		return (leftFront.getOutputCurrent() + leftBack.getOutputCurrent() + rightFront.getOutputCurrent()
				+ rightFront.getOutputCurrent()) / 4.0;
	}

	@Override
	public double temperature() {
		return (leftFront.getTemperature() + leftBack.getTemperature() + rightFront.getTemperature()
				+ rightBack.getTemperature()) / 4.0;
	}

	public void stopMotor() {
		drive.stopMotor();
	}
}
