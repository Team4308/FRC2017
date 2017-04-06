package org.usfirst.frc.team4308.robot.subsystems;

import org.usfirst.frc.team4308.robot.RobotMap;
import org.usfirst.frc.team4308.util.IAvailable;
import org.usfirst.frc.team4308.util.Loggable;
import org.usfirst.frc.team4308.util.MultiSpeedController;
import org.usfirst.frc.team4308.util.Powered;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A controlling subsystem responsible for a 6 wheel drive train and its motors,
 * as well as its pneumatic ball shifters. Encoders were to be available, but
 * were never implemented. Currently only one encoder is written in, but two
 * would be available on the robot.
 *
 * @author Michael Brown
 *
 */
public class DriveTrain extends Subsystem implements Loggable, Powered, IAvailable {

	private static final int CURRENT_LIMIT = 20; // Amps

	private boolean isAvailable;

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

		leftFront.setCurrentLimit(CURRENT_LIMIT);
		leftFront.EnableCurrentLimit(true);
		leftMiddle.setCurrentLimit(CURRENT_LIMIT);
		leftMiddle.EnableCurrentLimit(true);
		leftBack.setCurrentLimit(CURRENT_LIMIT);
		leftBack.EnableCurrentLimit(true);
		rightFront.setCurrentLimit(CURRENT_LIMIT);
		rightFront.EnableCurrentLimit(true);
		rightMiddle.setCurrentLimit(CURRENT_LIMIT);
		rightMiddle.EnableCurrentLimit(true);
		rightBack.setCurrentLimit(CURRENT_LIMIT);
		rightBack.EnableCurrentLimit(true);

		left = new MultiSpeedController(leftFront, leftMiddle, leftBack);
		right = new MultiSpeedController(rightFront, rightMiddle, rightBack);

		driveHandler = new RobotDrive(left, right);
		driveHandler.setSafetyEnabled(false);

		leftShifter = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.Drive.leftShifterA, RobotMap.Drive.leftShifterB);
		rightShifter = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.Drive.rightShifterA, RobotMap.Drive.rightShifterB);

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

		isAvailable = true;
	}

	@Override
	protected void initDefaultCommand() {

	}

	public void setLeftRightMotorOutputs(double leftOutput, double rightOutput) {
		leftOutput = limit(leftOutput);
		rightOutput = limit(rightOutput);
		// DriverStation.reportWarning("Motor Output: " + leftOutput + ", " +
		// rightOutput, false);
		driveHandler.setLeftRightMotorOutputs(leftOutput, rightOutput);
		leftMotorFeedback = leftOutput;
		rightMotorFeedback = rightOutput;
	}

	private double leftMotorFeedback = 0.0;
	private double rightMotorFeedback = 0.0;

	@Override
	public void log() {
		// SmartDashboard.putNumber("Distance", encoder.getDistance());
		// SmartDashboard.putNumber("Speed", encoder.getRate());
		SmartDashboard.putString("Transmission", gear ? "High Gear" : "Low Gear");
		SmartDashboard.putString("DB/String 1", gear ? "High Gear" : "Low Gear");
		SmartDashboard.putNumber("Left Motor", leftMotorFeedback);
		SmartDashboard.putNumber("Right Motor", rightMotorFeedback);
	}

	protected static double limit(double num) {
		return num > 1.0D ? 1.0D : (num < -1.0D ? -1.0D : num);
	}

	/**
	 * Toggle between the motors running at near-half speed or full speed.
	 */
	public void slow() {
		slow = !slow;
		if (slow) {
			driveHandler.setMaxOutput(RobotMap.Drive.Slow.slow);
		} else {
			driveHandler.setMaxOutput(RobotMap.Drive.Slow.normal);
		}
	}

	/**
	 * Resets the encoder attached to the drive train.
	 */
	public void resetEncoder() {
		encoder.reset();
	}

	/**
	 * @return How far have the encoders rotated thus far.
	 */
	public double getDistance() {
		return encoder.getDistance();
	}

	/**
	 * Returns the encoder that the drive train uses, allowing closer access and
	 * configuration.
	 * 
	 * @return The encoder attached to this drive train.
	 */
	public final Encoder getEncoder() {
		return encoder;
	}

	/**
	 * Switches the robot's ball shifter into high speed mode.
	 */
	public void highGear() {
		leftShifter.set(Value.kForward);
		rightShifter.set(Value.kForward);
		gear = true;
	}

	/**
	 * Switches the robot's ball shifter into high torque mode.
	 */
	public void lowGear() {
		leftShifter.set(Value.kReverse);
		rightShifter.set(Value.kReverse);
		gear = false;
	}

	public void stopMotor() {
		driveHandler.stopMotor();
	}

	@Override
	public boolean isAvailable() {
		return isAvailable;
	}
}
