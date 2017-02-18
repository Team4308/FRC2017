package org.usfirst.frc.team4308.robot.subsystems;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.RobotMap;
import org.usfirst.frc.team4308.util.Loggable;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.MotorSafety;
import edu.wpi.first.wpilibj.MotorSafetyHelper;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Controller for the drive train and its motors
 * 
 * @author mike_
 *
 */
public class DriveSamson extends Subsystem implements Loggable, MotorSafety {

	protected MotorSafetyHelper motorSafetyHelper;
	public static final double kDefaultExpirationTime = 0.1;
	public static final double kDefaultMaxOutput = 1.0;

	private static final double pulseDistance = 0.042;

	private DriveSamsonType type;
	private double maxOutput;
	private final SpeedController leftFront;
	private final SpeedController leftBack;
	private final SpeedController rightFront;
	private final SpeedController rightBack;
	private Encoder leftEncoder;
	private Encoder rightEncoder;

	// TODO: Constructors for inputs of name, speed controllers, and integer
	// channels
	// TODO: instantiation of correct encoders
	public DriveSamson() {
		super();

		motorSafetyHelper = new MotorSafetyHelper(this);
		motorSafetyHelper.setExpiration(kDefaultExpirationTime);
		motorSafetyHelper.setSafetyEnabled(true);

		maxOutput = kDefaultMaxOutput;

		leftFront = new CANTalon(RobotMap.DRIVE.frontLeft);
		leftBack = new CANTalon(RobotMap.DRIVE.backLeft);
		rightFront = new CANTalon(RobotMap.DRIVE.frontRight);
		rightBack = new CANTalon(RobotMap.DRIVE.backRight);

		setSafetyEnabled(true);

		// TODO encode shit
		leftEncoder = new Encoder(RobotMap.DRIVE.leftChannelA, RobotMap.DRIVE.leftChannelB);
		rightEncoder = new Encoder(RobotMap.DRIVE.rightChannelA, RobotMap.DRIVE.rightChannelB);
		leftEncoder.setDistancePerPulse(pulseDistance);
		rightEncoder.setDistancePerPulse(pulseDistance);

		LiveWindow.addSensor("Drive Train", "Left Encoder", leftEncoder);
		LiveWindow.addSensor("Drive Train", "Right Encoder", rightEncoder);
	}

	public void setDriveType(DriveSamsonType type) {
		this.type = type;
	}

	public void execute() {
		Joystick joystick = Robot.oi.getJoystick();

		switch (Robot.oi.getJoystickType()) {
		case STANDARD:
			// double leftX = limit(joystick.getRawAxis(RobotMap.CONTROL.LEFT_STICK_X));
			double leftY = limit(joystick.getRawAxis(RobotMap.CONTROL.LEFT_STICK_Y));
			double rightX = limit(joystick.getRawAxis(RobotMap.CONTROL.RIGHT_STICK_X));
			double rightY = limit(joystick.getRawAxis(RobotMap.CONTROL.RIGHT_STICK_Y));

			switch (type) {
			default:
			case SAMSON:

				double leftMotor = leftY + rightX;
				double rightMotor = leftY - rightX;

				setMotorOutputs(leftMotor, rightMotor);
				break;
			case TANK:
				setMotorOutputs(leftY, rightY);
				break;
			}
			break;
		case FLIGHT:
			// TODO controls for a flight styled control stick
			break;
		}
	}

	@Override
	protected void initDefaultCommand() { // TODO: properly grab control scheme from RobotMap
		// setDefaultCommand(Robot.oi.controlScheme);
		type = DriveSamsonType.SAMSON;
	}

	@Override
	public void log() {
		SmartDashboard.putNumber("Left Distance", leftEncoder.getDistance());
		SmartDashboard.putNumber("Right Distance", rightEncoder.getDistance());
		SmartDashboard.putNumber("Left Speed", leftEncoder.getRate());
		SmartDashboard.putNumber("Right Speed", rightEncoder.getRate());
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

	/**
	 * @return the maximum output for the motors
	 */
	public double getMaxOutput() {
		return maxOutput;
	}

	/**
	 * Sets the outputs of the motors. The general range for these values is -1.0 to 1.0. (Refer to getMaxOutput() to see what the range is).
	 * 
	 * @param leftOutput
	 *            the output to the left motors
	 * @param rightOutput
	 *            the output to the left motors
	 */
	public void setMotorOutputs(double leftOutput, double rightOutput) {
		if (leftFront != null) {
			leftFront.set(limit(leftOutput) * maxOutput);
		}

		if (leftBack != null) {
			leftBack.set(limit(leftOutput) * maxOutput);
		}

		if (rightFront != null) {
			rightFront.set(-limit(rightOutput) * maxOutput);
		}

		if (rightBack != null) {
			rightBack.set(-limit(rightOutput) * maxOutput);
		}

		if (motorSafetyHelper != null) {
			motorSafetyHelper.feed();
		}
	}

	private double limit(double num) {
		if (num > 1.0) {
			return 1.0;
		}
		if (num < -1.0) {
			return -1.0;
		}
		return num;
	}

	public void setMaxOutput(double limit) {
		this.maxOutput = Math.abs(limit);
	}

	@Override
	public void setExpiration(double timeout) {
		motorSafetyHelper.setExpiration(timeout);
	}

	@Override
	public double getExpiration() {
		return motorSafetyHelper.getExpiration();
	}

	@Override
	public boolean isAlive() {
		return motorSafetyHelper.isAlive();
	}

	@Override
	public boolean isSafetyEnabled() {
		return motorSafetyHelper.isSafetyEnabled();
	}

	@Override
	public void setSafetyEnabled(boolean enabled) {
		motorSafetyHelper.setSafetyEnabled(enabled);
	}

	@Override
	public String getDescription() {
		return "Samson's Robot Drive";
	}

	@Override
	public void stopMotor() {
		if (leftFront != null) {
			leftFront.stopMotor();
		}
		if (rightFront != null) {
			rightFront.stopMotor();
		}
		if (leftBack != null) {
			leftBack.stopMotor();
		}
		if (rightBack != null) {
			rightBack.stopMotor();
		}
		if (motorSafetyHelper != null) {
			motorSafetyHelper.feed();
		}
	}
}