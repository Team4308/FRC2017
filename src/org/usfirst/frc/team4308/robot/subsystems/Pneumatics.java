package org.usfirst.frc.team4308.robot.subsystems;

import org.usfirst.frc.team4308.robot.RobotMap;
import org.usfirst.frc.team4308.robot.commands.WaitForPressure;
import org.usfirst.frc.team4308.util.Loggable;
import org.usfirst.frc.team4308.util.Loop;
import org.usfirst.frc.team4308.util.Powered;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Pneumatics extends Subsystem implements Loggable, Powered, Loop {

	private static final int supplyVoltage = 12;
	private static boolean isRunning;

	private Compressor compressor;

	public Pneumatics() {
		super();
		compressor = new Compressor(RobotMap.PCM);
		isRunning = true;
		LiveWindow.addActuator("Pneumatics", "Compressor", compressor);
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
		isRunning = true;
		compressor.start();
		SmartDashboard.putBoolean("DB/Button 0", isRunning);
	}

	/**
	 * Stops the compressor.
	 */
	public void stop() {
		isRunning = false;
		compressor.stop();
		SmartDashboard.putBoolean("DB/Button 0", isRunning);
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
		return compressor.getPressureSwitchValue();
	}

	@Override
	public void log() {
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
		return compressor.getCompressorCurrent();
	}

	@Override
	public void loop() {
		if (SmartDashboard.getBoolean("DB/Button 0", true)) {
			start();
		} else {
			stop();
		}
	}

}
