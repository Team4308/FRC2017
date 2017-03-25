package org.usfirst.frc.team4308.robot.subsystems;

import org.usfirst.frc.team4308.robot.RobotMap;
import org.usfirst.frc.team4308.robot.commands.WaitForPressure;
import org.usfirst.frc.team4308.util.Loggable;
import org.usfirst.frc.team4308.util.Powered;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Pneumatics extends Subsystem implements Loggable, Powered {

	private static final int supplyVoltage = 12;

	private Compressor compressor;

	public Pneumatics() {
		super();
		compressor = new Compressor(RobotMap.PCM);

		LiveWindow.addActuator("Pneumatics", "Compressor", compressor);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new WaitForPressure());
	}

	/**
	 * Start the compressor going. The compressor automatically starts and stops as it goes above and below maximum pressure.
	 */
	public void start() {
		compressor.start();
	}

	/**
	 * Stops the compressor.
	 */
	public void stop() {
		compressor.stop();
	}

	/**
	 * @return Whether or not the system is fully pressurized.
	 */
	public boolean isPressurized() {
		return compressor.getPressureSwitchValue();
	}

	@Override
	public void log() {
		SmartDashboard.putBoolean("Pressurized", isPressurized());
		SmartDashboard.putNumber("Compressor Current", current());
	}

	@Override
	public double voltage() {
		return supplyVoltage;
	}

	@Override
	public double current() {
		return compressor.getCompressorCurrent();
	}

}
