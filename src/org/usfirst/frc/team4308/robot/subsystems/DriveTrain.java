package org.usfirst.frc.team4308.robot.subsystems;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.RobotMap;
import org.usfirst.frc.team4308.robot.commands.ArcadeDrive;
import org.usfirst.frc.team4308.robot.commands.TankDrive;
import org.usfirst.frc.team4308.util.Loggable;
import org.usfirst.frc.team4308.util.MultiSpeedController;
import org.usfirst.frc.team4308.util.Powered;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.MotorSafety;
import edu.wpi.first.wpilibj.MotorSafetyHelper;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * Controller for the drive train and its motors
 *
 */
public class DriveTrain extends Subsystem implements Loggable, Powered, MotorSafety {

	protected MotorSafetyHelper safetyHelper;

	private final CANTalon leftFront;
	private final CANTalon leftMiddle;
	private final CANTalon leftBack;
	private final CANTalon rightFront;
	private final CANTalon rightMiddle;
	private final CANTalon rightBack;
	
	private Encoder encoder;
	
	private final RobotDrive driveHandler;
	
	private final MultiSpeedController leftDrive;
	private final MultiSpeedController rightDrive;
	
	private boolean squaredDrive;
	
	public DriveTrain() {
		super();
		
		leftFront = new CANTalon(RobotMap.Drive.leftFront);
		leftMiddle = new CANTalon(RobotMap.Drive.leftMiddle);
		leftBack = new CANTalon(RobotMap.Drive.leftBack);
		rightFront = new CANTalon(RobotMap.Drive.rightFront);
		rightMiddle = new CANTalon(RobotMap.Drive.rightMiddle);
		rightBack = new CANTalon(RobotMap.Drive.rightBack);

		leftDrive = new MultiSpeedController(leftFront, leftMiddle, leftBack);
		rightDrive = new MultiSpeedController(rightFront, rightMiddle, rightBack);
		driveHandler = new RobotDrive(leftDrive, rightDrive);
		safetyHelper = new MotorSafetyHelper(this);
		safetyHelper.setExpiration(0.1D);
		safetyHelper.setSafetyEnabled(true);

		// encoder = new Encoder(RobotMap.Drive.ChannelA, RobotMap.Drive.ChannelB);
		// encoder.setDistancePerPulse(RobotMap.Drive.encoderPulseDistance);
		
		squaredDrive = true;

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
		switch (Robot.io.getJoystickType()) {
		case FLIGHT:
			setDefaultCommand(new ArcadeDrive(Robot.io.getLeftAxis(), Robot.io.getRightAxis()));
			break;
		case STANDARD:
			setDefaultCommand(new TankDrive());
			break;
		default:
			setDefaultCommand(new TankDrive());
			break;
		}
	}

	public void arcadeDrive(double moveValue, double rotateValue) {
		driveHandler.arcadeDrive(moveValue, rotateValue, squaredDrive);
	}

	public void tankDrive(double leftOutput, double rightOutput) {
		driveHandler.tankDrive(leftOutput, rightOutput, squaredDrive);
	}

	public void setLeftRightMotorOutputs(double leftOutput, double rightOutput) {
		driveHandler.setLeftRightMotorOutputs(leftOutput, rightOutput);
	}

	@Override
	public void log() {
		// SmartDashboard.putNumber("Distance", encoder.getDistance());
		// SmartDashboard.putNumber("Speed", encoder.getRate());
	}

	public void setMaxOutput(double power) {
		driveHandler.setMaxOutput(power);
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

	@Override
	public double voltage() {
		return (leftFront.getOutputVoltage() + leftBack.getOutputVoltage() + rightFront.getOutputVoltage() + rightBack.getOutputVoltage()) / 4.0;
	}

	@Override
	public double current() {
		return (leftFront.getOutputCurrent() + leftBack.getOutputCurrent() + rightFront.getOutputCurrent() + rightFront.getOutputCurrent()) / 4.0;
	}

	@Override
	public double temperature() {
		return (leftFront.getTemperature() + leftBack.getTemperature() + rightFront.getTemperature() + rightBack.getTemperature()) / 4.0;
	}

	@Override
	@SuppressWarnings("deprecation")
	public void stopMotor() {
		driveHandler.stopMotor();
	}

	@Override
	public String getDescription() {
		return this.getName();
	}

	@Override
	public double getExpiration() {
		return safetyHelper.getExpiration();
	}

	@Override
	public boolean isAlive() {
		return safetyHelper.isAlive();
	}

	@Override
	public boolean isSafetyEnabled() {
		return safetyHelper.isSafetyEnabled();
	}

	@Override
	public void setExpiration(double expirationTime) {
		safetyHelper.setExpiration(expirationTime);
	}

	@Override
	public void setSafetyEnabled(boolean enabled) {
		safetyHelper.setSafetyEnabled(enabled);
	}
}
