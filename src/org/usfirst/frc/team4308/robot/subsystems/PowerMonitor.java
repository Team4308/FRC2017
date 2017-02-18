package org.usfirst.frc.team4308.robot.subsystems;

import java.util.HashMap;

import org.usfirst.frc.team4308.robot.RobotMap;
import org.usfirst.frc.team4308.robot.RobotMap.POWER;
import org.usfirst.frc.team4308.robot.commands.PowerCheck;
import org.usfirst.frc.team4308.util.Loggable;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PowerMonitor extends Subsystem implements Loggable {

	private static final double cautionThreshold = 0.9;
	private static final double warningThreshold = 0.8;

	private HashMap<String, CANTalon> motors;
	private final PowerDistributionPanel pdp;

	private POWER.BatteryLevel batteryLevel;
	private boolean currentWarning;
	private boolean temperatureWarning;

	public PowerMonitor() {
		super();
		motors = new HashMap<String, CANTalon>();
		pdp = new PowerDistributionPanel();
		batteryLevel = POWER.BatteryLevel.level(pdp.getVoltage());
		currentWarning = false;
		temperatureWarning = false;

		add("frontLeft", new CANTalon(RobotMap.DRIVE.frontLeft));
		add("frontRight", new CANTalon(RobotMap.DRIVE.frontRight));
		add("backLeft", new CANTalon(RobotMap.DRIVE.backLeft));
		add("backRight", new CANTalon(RobotMap.DRIVE.backRight));

		add("climbMaster", new CANTalon(RobotMap.CLIMBER.masterChannel));
		add("climbSlave", new CANTalon(RobotMap.CLIMBER.slaveChannel));
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new PowerCheck());
	}

	public void add(String name, CANTalon motor) {
		motors.put(name, motor);
	}

	public boolean check() {
		double voltage = pdp.getVoltage();
		double current = pdp.getTotalCurrent();
		double temperature = pdp.getTemperature();
		batteryLevel = POWER.BatteryLevel.level(voltage);

		if (current > POWER.breakerAmpLimit * cautionThreshold) {
			// TODO: auto initiate reduced-draw
			return false;
		} else if (current > POWER.breakerAmpLimit * warningThreshold) {
			// TODO: warn of the high current draws
			currentWarning = true;
		}

		if (batteryLevel.ordinal() < POWER.BatteryLevel.LOW.ordinal()) {
			// TODO: warn of low battery charge
			return false;
		}

		if (temperature > POWER.dangerTemp) {
			// TODO: auto initiate reduced draw
			// TODO: warn of instable temperature levels
			return false;
		} else if (temperature > POWER.warningTemp) {
			// TODO: warn of high temperature
			temperatureWarning = true;
		}

		return true;
	}

	public boolean currentWarning() {
		return currentWarning;
	}

	public boolean temperatureWarning() {
		return temperatureWarning;
	}

	@Override
	public void log() {
		SmartDashboard.putBoolean("Temperature Warning", temperatureWarning);
		SmartDashboard.putBoolean("Current Warning", currentWarning);
	}

}
