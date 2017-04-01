package org.usfirst.frc.team4308.robot.subsystems;

import org.usfirst.frc.team4308.robot.RobotMap;
import org.usfirst.frc.team4308.util.IAvailable;
import org.usfirst.frc.team4308.util.Loggable;
import org.usfirst.frc.team4308.util.Loop;
import org.usfirst.frc.team4308.util.Powered;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Pneumatics extends Subsystem implements Loggable, Powered, Loop, IAvailable {

	private static final int supplyVoltage = 12;
	private static boolean isRunning;

	private boolean isAvailable;
	private Compressor compressor;

	public Pneumatics() {
		super();
		compressor = new Compressor(RobotMap.PCM_ID);
		isAvailable = compressor != null;
		isRunning = isAvailable;
		if (isAvailable)
			LiveWindow.addActuator("Pneumatics", "Compressor", compressor);
	}

	@Override
	protected void initDefaultCommand() {
	}

	/**
	 * Start the compressor going. The compressor automatically starts and stops
	 * as it goes above and below maximum pressure.
	 */
	@Override
	public void start() {
		if (isAvailable) {
			isRunning = true;
			compressor.start();
			SmartDashboard.putBoolean("DB/Button 0", isRunning);
		}
	}

	/**
	 * Stops the compressor.
	 */
	@Override
	public void stop() {
		if (isAvailable) {
			isRunning = false;
			compressor.stop();
			SmartDashboard.putBoolean("DB/Button 0", isRunning);
		}
	}

	public boolean isRunning() {
		return isRunning;
	}

	public boolean isSafetyStopped() {
		return isRunning && !compressor.enabled();
	}

	/**
	 * @return Whether or not the system is fully pressurized.
	 */
	public boolean isPressurized() {
		return isAvailable ? compressor.getPressureSwitchValue() : false;
	}

	@Override
	public void log() {
		SmartDashboard.putBoolean("Pneumatics", isRunning);
		SmartDashboard.putBoolean("Pressurized", isPressurized());
		SmartDashboard.putNumber("Compressor Current", current());
		SmartDashboard.putString("DB/String 0", isPressurized() ? "Pressurized!" : "Not Pressurized");
		SmartDashboard.putNumber("DB/Slider 0", current());
	}

	@Override
	public double voltage() {
		return supplyVoltage;
	}

	@Override
	public double current() {
		return isAvailable ? compressor.getCompressorCurrent() : 0;
	}

	@Override
	public void loop() {
		if (SmartDashboard.getBoolean("DB/Button 0", true)) {
			start();
		} else {
			stop();
		}
	}

	@Override
	public boolean isAvailable() {
		return isAvailable;
	}

}
