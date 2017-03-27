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
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Controller for the drive train and its motors
 *
 */
public class DriveTrain extends Subsystem implements Loggable, Powered {

	private Encoder encoder;

	private DoubleSolenoid leftShifter;
	private DoubleSolenoid rightShifter;

	public RobotDrive driveHandler;

	private boolean gear;
	private boolean slow;

	private MultiSpeedController left;
	private MultiSpeedController right;

	public DriveTrain() {
		super();
		CANTalon leftFront = new CANTalon(RobotMap.Drive.leftFront);
		CANTalon leftMiddle = new CANTalon(RobotMap.Drive.leftMiddle);
		CANTalon leftBack = new CANTalon(RobotMap.Drive.leftBack);
		CANTalon rightFront = new CANTalon(RobotMap.Drive.rightFront);
		CANTalon rightMiddle = new CANTalon(RobotMap.Drive.rightMiddle);
		CANTalon rightBack = new CANTalon(RobotMap.Drive.rightBack);

		left = new MultiSpeedController(leftFront, leftMiddle, leftBack);
		right = new MultiSpeedController(rightFront, rightMiddle, rightBack);

		driveHandler = new RobotDrive(left, right);

		leftShifter = new DoubleSolenoid(RobotMap.PCM, RobotMap.Drive.leftShifterA, RobotMap.Drive.leftShifterB);
		rightShifter = new DoubleSolenoid(RobotMap.PCM, RobotMap.Drive.rightShifterA, RobotMap.Drive.rightShifterB);

		gear = false;
		slow = false;

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
		if (Robot.io == null || Robot.io.getJoystickType() == null) {
			DriverStation.reportWarning("Shit man, Robot.io is null", true);
			DriverStation.reportWarning("Control system is not present!", true);
		} else {
			switch (Robot.io.getJoystickType()) {
			case FLIGHT:
				Robot.control = new ArcadeDrive(Robot.io.getLeftAxis(), Robot.io.getRightAxis());
				break;
			case STANDARD:
				Robot.control = new TankDrive();
				break;
			default:
				Robot.control = null;
				DriverStation.reportError("Cannot assign control scheme to joystick!", false);
				break;
			}
		}
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
		driveHandler.setLeftRightMotorOutputs(leftOutput, rightOutput);
	}

	@Override
	public void log() {
		// SmartDashboard.putNumber("Distance", encoder.getDistance());
		// SmartDashboard.putNumber("Speed", encoder.getRate());
		SmartDashboard.putBoolean("Gear", gear);
		SmartDashboard.putString("DB/String 1", gear ? "High Gear" : "Low Gear");
	}

	protected static double limit(double num) {
		return num > 1.0D ? 1.0D : (num < -1.0D ? -1.0D : num);
	}
	
	public void slow() {
		slow = !slow;
		if (slow) {
			driveHandler.setMaxOutput(RobotMap.Drive.Slow.slow);
		} else {
			driveHandler.setMaxOutput(RobotMap.Drive.Slow.normal);
		}
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
		gear = true;
	}

	public void lowGear() {
		leftShifter.set(Value.kReverse);
		rightShifter.set(Value.kReverse);
		gear = false;
	}

	public void stopMotor() {
		driveHandler.stopMotor();
	}
}
