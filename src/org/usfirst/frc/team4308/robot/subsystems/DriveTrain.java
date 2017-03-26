package org.usfirst.frc.team4308.robot.subsystems;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.RobotMap;
import org.usfirst.frc.team4308.robot.commands.ArcadeDrive;
import org.usfirst.frc.team4308.robot.commands.TankDrive;
import org.usfirst.frc.team4308.util.Loggable;
import org.usfirst.frc.team4308.util.MultiSpeedController;
import org.usfirst.frc.team4308.util.Powered;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * Controller for the drive train and its motors
 *
 */
public class DriveTrain extends Subsystem implements Loggable, Powered {

	private final CANTalon leftFront;
	private final CANTalon leftMiddle;
	private final CANTalon leftBack;
	private final CANTalon rightFront;
	private final CANTalon rightMiddle;
	private final CANTalon rightBack;
	private Encoder encoder;
	private double maxPower;

	private DoubleSolenoid leftShifter;
	private DoubleSolenoid rightShifter;

	public RobotDrive robotDrive;

	public DriveTrain() {
		super();
		leftFront = new CANTalon(RobotMap.Drive.leftFront);
		leftMiddle = new CANTalon(RobotMap.Drive.leftMiddle);
		leftBack = new CANTalon(RobotMap.Drive.leftBack);
		rightFront = new CANTalon(RobotMap.Drive.rightFront);
		rightMiddle = new CANTalon(RobotMap.Drive.rightMiddle);
		rightBack = new CANTalon(RobotMap.Drive.rightBack);

		MultiSpeedController left = new MultiSpeedController(leftFront, leftMiddle, leftBack);
		MultiSpeedController right = new MultiSpeedController(rightFront, rightMiddle, rightBack);

		robotDrive = new RobotDrive(left, right);

		leftShifter = new DoubleSolenoid(RobotMap.PCM, RobotMap.Drive.leftShifterA, RobotMap.Drive.leftShifterB);
		rightShifter = new DoubleSolenoid(RobotMap.PCM, RobotMap.Drive.rightShifterA, RobotMap.Drive.rightShifterB);

		// encoder = new Encoder(RobotMap.Drive.ChannelA,
		// RobotMap.Drive.ChannelB);
		// encoder.setDistancePerPulse(RobotMap.Drive.encoderPulseDistance);

		LiveWindow.addActuator("Drive Train", "leftFront", leftFront);
		LiveWindow.addActuator("Drive Train", "leftMiddle", leftMiddle);
		LiveWindow.addActuator("Drive Train", "leftBack", leftBack);
		LiveWindow.addActuator("Drive Train", "rightFront", rightFront);
		LiveWindow.addActuator("Drive Train", "rightMiddle", rightMiddle);
		LiveWindow.addActuator("Drive Train", "rightBack", rightBack);
		// LiveWindow.addSensor("Drive Train", "Encoder", encoder);
	}

	@Override
	protected void initDefaultCommand() {
//		switch (Robot.io.getJoystickType()) {
//		case FLIGHT:
//			setDefaultCommand(new ArcadeDrive(Robot.io.getLeftAxis(), Robot.io.getRightAxis()));
//			break;
//		case STANDARD:
			setDefaultCommand(new TankDrive());
			// setDefaultCommand(new ArcadeDrive(Robot.io.getTurnAxis(),
			// Robot.io.getRightAxis()));
			// setDefaultCommand(new SamsonDrive());
//			break;
//		default:
//			setDefaultCommand(new TankDrive());
//			break;
//		}
	}

	public void arcadeDrive(double moveValue, double rotateValue) {
		moveValue = limit(moveValue);
		rotateValue = limit(rotateValue);

		double leftMotorSpeed;
		double rightMotorSpeed;
		if (moveValue > 0.0D) {
			if (rotateValue > 0.0D) {
				leftMotorSpeed = moveValue - rotateValue;
				rightMotorSpeed = Math.max(moveValue, rotateValue);
			} else {
				leftMotorSpeed = Math.max(moveValue, -rotateValue);
				rightMotorSpeed = moveValue + rotateValue;
			}
		} else if (rotateValue > 0.0D) {
			leftMotorSpeed = -Math.max(-moveValue, rotateValue);
			rightMotorSpeed = moveValue + rotateValue;
		} else {
			leftMotorSpeed = moveValue - rotateValue;
			rightMotorSpeed = -Math.max(-moveValue, -rotateValue);
		}

		setLeftRightMotorOutputs(leftMotorSpeed, rightMotorSpeed);
	}

	public void tankDrive(double leftOutput, double rightOutput) {
		leftOutput = limit(leftOutput);
		rightOutput = limit(rightOutput);
		setLeftRightMotorOutputs(leftOutput, rightOutput);
	}

	public void setLeftRightMotorOutputs(double leftOutput, double rightOutput) {
		// if (!PneumaticsToggle.isEnabled) {
		leftFront.set(-limit(leftOutput) * maxPower);
		leftMiddle.set(-limit(leftOutput) * maxPower);
		leftBack.set(-limit(leftOutput) * maxPower);
		rightFront.set(limit(rightOutput) * maxPower);
		rightMiddle.set(limit(rightOutput) * maxPower);
		rightBack.set(limit(rightOutput) * maxPower);
		// }

	}

	@Override
	public void log() {
		// SmartDashboard.putNumber("Distance", encoder.getDistance());
		// SmartDashboard.putNumber("Speed", encoder.getRate());
	}

	protected static double limit(double num) {
		return num > 1.0D ? 1.0D : (num < -1.0D ? -1.0D : num);
	}

	public void setMaxOutput(double power) {
		maxPower = power;
	}

	public void resetEncoder() {
		encoder.reset();
	}

	public double getDistance() {
		return encoder.getDistance();
	}

	public final Encoder getEncoder() {
		return encoder;
	}

	public void highGear() {
		leftShifter.set(Value.kForward);
		rightShifter.set(Value.kForward);
	}

	public void lowGear() {
		leftShifter.set(Value.kReverse);
		rightShifter.set(Value.kReverse);
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

	@SuppressWarnings("deprecation")
	public void stopMotor() {
		leftBack.stopMotor();
		leftMiddle.stopMotor();
		leftBack.stopMotor();
		rightBack.stopMotor();
		rightMiddle.stopMotor();
		rightBack.stopMotor();
	}
}
