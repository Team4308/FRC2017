package org.usfirst.frc.team4308.robot.subsystems;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.RobotMap;
import org.usfirst.frc.team4308.robot.commands.ArcadeDrive;
import org.usfirst.frc.team4308.robot.commands.DriveControl;
import org.usfirst.frc.team4308.robot.commands.SamsonDrive;
import org.usfirst.frc.team4308.robot.commands.TankDrive;
import org.usfirst.frc.team4308.util.Loggable;
import org.usfirst.frc.team4308.util.Powered;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.MotorSafety;
import edu.wpi.first.wpilibj.MotorSafetyHelper;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Controller for the drive train and its motors
 * 
 * @author Samson Close
 *
 */
public class DriveTrain extends PIDSubsystem implements Loggable, Powered {

	private DriveControlType type;
	private final CANTalon leftFront;
	private final CANTalon leftBack;
	private final CANTalon rightFront;
	private final CANTalon rightBack;
	private final RobotDrive drive;
	private Encoder leftEncoder;
	private Encoder rightEncoder;

	// TODO: instantiation of correct encoders
	public DriveTrain() {
		super(RobotMap.CONSTANT.proportional, RobotMap.CONSTANT.integral, RobotMap.CONSTANT.differential);

		type = DriveControlType.SAMSON;

		leftFront = new CANTalon(RobotMap.DRIVE.frontLeft);
		leftBack = new CANTalon(RobotMap.DRIVE.backLeft);
		rightFront = new CANTalon(RobotMap.DRIVE.frontRight);
		rightBack = new CANTalon(RobotMap.DRIVE.backRight);

		drive = new RobotDrive(leftFront, leftBack, rightFront, rightBack);
		drive.setSafetyEnabled(true);
		drive.setSensitivity(RobotMap.DRIVE.curveSensitivity);

		drive.setInvertedMotor(MotorType.kFrontLeft, true);
		drive.setInvertedMotor(MotorType.kRearLeft, true);
		drive.setInvertedMotor(MotorType.kFrontRight, true);
		drive.setInvertedMotor(MotorType.kRearRight, true);

		// TODO encode shit
		leftEncoder = new Encoder(RobotMap.DRIVE.leftChannelA, RobotMap.DRIVE.leftChannelB);
		rightEncoder = new Encoder(RobotMap.DRIVE.rightChannelA, RobotMap.DRIVE.rightChannelB);
		leftEncoder.setDistancePerPulse(RobotMap.DRIVE.encoderPulseDistance);
		rightEncoder.setDistancePerPulse(RobotMap.DRIVE.encoderPulseDistance);

		LiveWindow.addSensor("Drive Train", "Left Encoder", leftEncoder);
		LiveWindow.addSensor("Drive Train", "Right Encoder", rightEncoder);
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
			setDefaultCommand(new DriveControl());
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
		return (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2;
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

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub

	}

	public void stopMotor() {
		drive.stopMotor();
	}
}
