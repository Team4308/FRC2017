package org.usfirst.frc.team4308.robot.subsystems;

import org.usfirst.frc.team4308.robot.RobotMap;
import org.usfirst.frc.team4308.util.Loggable;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Climber extends Subsystem implements SpeedController, Loggable {

	public static final double restingSpeed = 0.0;
	public static final double maxForward = 1.0;
	public static final double maxBackward = -1.0;

	private final SpeedController master;
	private final SpeedController slave;
	private boolean isInverted = false;
	private double speed;

	public Climber() {
		this(false);
	}

	public Climber(boolean isInverted) {
		super();
		master = new CANTalon(RobotMap.CLIMBER.masterChannel);
		slave = new CANTalon(RobotMap.CLIMBER.slaveChannel);
		this.set(restingSpeed);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO setDefaultCommand(new ClimberControl());
	}

	@Override
	public void set(double speed) {
		if (isInverted) {
			master.set(-speed);
			slave.set(speed);
			this.speed = -speed;
		} else {
			master.set(speed);
			slave.set(-speed);
			this.speed = speed;
		}
	}

	@Override
	public double get() {
		return this.speed;
	}

	@Override
	public void stopMotor() {
		this.master.stopMotor();
		this.slave.stopMotor();
		this.speed = restingSpeed;
	}

	@Override
	public void pidWrite(double output) {
		this.set(output);
	}

	@Override
	public void setInverted(boolean isInverted) {
		this.isInverted = isInverted;

	}

	@Override
	public boolean getInverted() {
		return this.isInverted;
	}

	@Override
	public void disable() {
		master.disable();
		slave.disable();
	}

	@Override
	public void log() {
		SmartDashboard.putBoolean("Climb Invert", isInverted);
		SmartDashboard.putNumber("Climb Speed", speed);
	}

}
