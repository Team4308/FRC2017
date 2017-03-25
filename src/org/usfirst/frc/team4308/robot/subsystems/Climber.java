package org.usfirst.frc.team4308.robot.subsystems;

import org.usfirst.frc.team4308.robot.RobotMap;
import org.usfirst.frc.team4308.robot.commands.ClimberControl;
import org.usfirst.frc.team4308.util.Loggable;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Climber extends Subsystem implements SpeedController, Loggable { // Powered

	private final Talon master;
	private final Spark slave;
	private boolean isInverted = false;
	private double speed;

	public Climber() {
		this(false);
	}

	public Climber(boolean isInverted) {
		super();
		master = new Talon(RobotMap.Climb.climbB);
		slave = new Spark(RobotMap.Climb.climbA);
		this.set(RobotMap.Climb.restingSpeed);
		this.isInverted = isInverted;

		LiveWindow.addActuator("Climber", "MasterTalon", master);
		LiveWindow.addActuator("Climber", "SlaveTalon", slave);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ClimberControl());
	}

	@Override
	public void set(double speed) {
		speed = Math.max(Math.min(speed, RobotMap.Climb.maxForward), RobotMap.Climb.maxBackward);

		if (isInverted) {
			master.set(speed);
		   slave.set(-speed);
			this.speed = -speed;
		} else {
			master.set(-speed);
			slave.set(speed);
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
		set(RobotMap.Climb.restingSpeed);
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

	// @Override
	// public double voltage() {
	// return (master.getOutputVoltage() + slave.getOutputVoltage()) / 2.0;
	// }
	//
	// @Override
	// public double current() {
	// return (master.getOutputCurrent() / slave.getOutputCurrent()) / 2.0;
	// }
	//
	// @Override
	// public double temperature() {
	// return (master.getTemperature() + slave.getTemperature()) / 2.0;
	// }

}
