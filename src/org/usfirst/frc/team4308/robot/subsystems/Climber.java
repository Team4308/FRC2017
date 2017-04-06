package org.usfirst.frc.team4308.robot.subsystems;

import org.usfirst.frc.team4308.robot.RobotMap;
import org.usfirst.frc.team4308.util.IAvailable;
import org.usfirst.frc.team4308.util.Loggable;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A subsystem responsible for controlling a ratcheted rope climbing system.
 * 
 * @author Michael Brown
 *
 */
public class Climber extends Subsystem implements SpeedController, Loggable, IAvailable { // Powered

	private final Talon master;
	private final Talon slave;
	private boolean isInverted;
	private double speed = 0;
	private boolean isAvailable;

	public Climber() {
		this(false);
	}

	public Climber(boolean isInverted) {
		super();
		master = new Talon(RobotMap.Climb.climbB);
		slave = new Talon(RobotMap.Climb.climbA);

		isAvailable = !(master == null && slave == null);

		this.set(RobotMap.Climb.restingSpeed);
		this.isInverted = isInverted;

		LiveWindow.addActuator("Climber", "MasterTalon", master);
		LiveWindow.addActuator("Climber", "SlaveTalon", slave);
	}

	@Override
	protected void initDefaultCommand() {

	}

	@Override
	public void set(double speed) {
		if (isAvailable) {
			speed = Math.max(Math.min(speed, RobotMap.Climb.maxForward), RobotMap.Climb.maxBackward);

			if (isInverted) {
				if (master != null)
					master.set(speed);
				if (slave != null)
					slave.set(-speed);
				this.speed = -speed;
			} else {
				if (master != null)
					master.set(-speed);
				if (slave != null)
					slave.set(speed);
				this.speed = speed;
			}
		}
	}

	@Override
	public double get() {
		return this.speed;
	}

	@Override
	public void stopMotor() {
		if (isAvailable) {
			if (master != null)
				this.master.stopMotor();
			if (slave != null)
				this.slave.stopMotor();
			set(RobotMap.Climb.restingSpeed);
		}
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
		if (master != null)
			master.disable();
		if (slave != null)
			slave.disable();
	}

	@Override
	public void log() {
		SmartDashboard.putBoolean("Climb Invert", isInverted);
		SmartDashboard.putNumber("Climb Speed", speed);
	}

	@Override
	public boolean isAvailable() {
		return isAvailable;
	}

}
