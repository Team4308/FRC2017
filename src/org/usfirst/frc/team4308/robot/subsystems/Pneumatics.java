package org.usfirst.frc.team4308.robot.subsystems;

import org.usfirst.frc.team4308.robot.RobotMap;
import org.usfirst.frc.team4308.robot.commands.WaitForPressure;
import org.usfirst.frc.team4308.util.Loggable;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Pneumatics extends Subsystem implements Loggable { // TODO: adapt to actual pneumatics application

	private static final double maxPressure = 2.55;

	private AnalogInput pressureSensor;
	private Compressor compressor;

	public Pneumatics() {
		super();
		compressor = new Compressor();
		pressureSensor = new AnalogInput(RobotMap.pressureSensorChannel);

		LiveWindow.addSensor("Pneumatics", "Pressure Sensor", pressureSensor);
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
		return maxPressure <= pressureSensor.getVoltage();
	}

	@Override
	public void log() {
		SmartDashboard.putNumber("Pressure", pressureSensor.getVoltage());
	}

}
