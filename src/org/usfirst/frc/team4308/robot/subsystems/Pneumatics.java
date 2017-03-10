package org.usfirst.frc.team4308.robot.subsystems;

import org.usfirst.frc.team4308.robot.commands.WaitForPressure;
import org.usfirst.frc.team4308.util.Loggable;
import org.usfirst.frc.team4308.util.Powered;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Pneumatics extends Subsystem implements Loggable, Powered {
	
	private static final int supplyVoltage = 12;

	private Compressor compressor;

	public Pneumatics() {
		super();
		compressor = new Compressor();
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new WaitForPressure());
	}

	/**
	 * Start the compressor going. The compressor automatically starts and stops
	 * as it goes above and below maximum pressure.
	 */
	public void start() {
		compressor.start();
	}

	/**
	 * @return Whether or not the system is fully pressurized.
	 */
	public boolean isPressurized() {
		return !compressor.getPressureSwitchValue();
	}

	@Override
	public void log() {
		SmartDashboard.putBoolean("Pressurized", isPressurized());
	}

	@Override
	public double voltage() {
		return supplyVoltage;
	}

	@Override
	public double current() {
		return compressor.getCompressorCurrent();
	}

	@Override
	public double temperature() {
		return 0;
	}

}
