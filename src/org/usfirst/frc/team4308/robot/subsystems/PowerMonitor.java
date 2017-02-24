package org.usfirst.frc.team4308.robot.subsystems;

import java.util.ArrayList;
import java.util.HashMap;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.RobotMap.Power;
import org.usfirst.frc.team4308.robot.commands.PowerCheck;
import org.usfirst.frc.team4308.util.Loggable;
import org.usfirst.frc.team4308.util.Powered;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PowerMonitor extends Subsystem implements Loggable {

	private final PowerDistributionPanel pdp;

	private Power.BatteryLevel batteryLevel;
	private ArrayList<Powered> monitors;

	private boolean currentWarning;
	private boolean temperatureWarning;

	public PowerMonitor() {
		super();
		pdp = new PowerDistributionPanel();
		batteryLevel = Power.BatteryLevel.level(pdp.getVoltage());
		currentWarning = false;
		temperatureWarning = false;
		monitors = new ArrayList<Powered>();

		monitors.add(Robot.arm);
		monitors.add(Robot.climber);
		monitors.add(Robot.drive);
		monitors.add(Robot.pneumatics);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new PowerCheck());
	}

	/**
	 * Polls the main power delivery systems for any non-optimal scenarios.
	 * 
	 * @return Whether the system is pulling potentially harmful levels of power
	 *         or not
	 */
	public boolean check() {
		double voltage = pdp.getVoltage();
		double current = pdp.getTotalCurrent();
		double temperature = pdp.getTemperature();
		batteryLevel = Power.BatteryLevel.level(voltage);

		if (current > Power.breakerAmpLimit * Power.cautionThreshold) {
			// TODO: auto initiate reduced-draw
			return false;
		} else if (current > Power.breakerAmpLimit * Power.warningThreshold) {
			// TODO: warn of the high current draws
			currentWarning = true;
		}

		if (batteryLevel.ordinal() < Power.BatteryLevel.LOW.ordinal()) {
			// TODO: warn of low battery charge
			return false;
		}

		if (temperature > Power.dangerTemp) {
			// TODO: auto initiate reduced draw
			// TODO: warn of instable temperature levels
			return false;
		} else if (temperature > Power.warningTemp) {
			// TODO: warn of high temperature
			temperatureWarning = true;
		}

		return true;
	}

	// TODO: current-reactive motor speed limiting for arm, climber and drive
	// TODO: voltage-reactive warning for arm, climber and drive
	// TODO: temperature-reactive motor speed limiting for drive
	// TODO: temperature-reactive warning for arm and climber
	/**
	 * Polls each subsystems power status, determining whether their power using
	 * components are taking too much power or producing too much heat
	 * 
	 * @return Whether any of the systems are
	 */
	public boolean[] systemCheck() {
		boolean armState = true;
		boolean climbState = true;
		boolean driveState = true;
		boolean pneumaticsState = true;

		if (Robot.arm.current() > Power.secondaryAmpLimit * Power.cautionThreshold) {
			armState = false;
		} else if (Robot.arm.current() > Power.secondaryAmpLimit * Power.warningThreshold) {

		}

		if (Robot.arm.temperature() > Power.dangerTemp) {
			armState = false;
		} else if (Robot.arm.temperature() > Power.warningTemp) {

		}

		if (Robot.climber.current() > Power.secondaryAmpLimit * Power.cautionThreshold) {
			climbState = false;
		} else if (Robot.climber.current() > Power.secondaryAmpLimit * Power.warningThreshold) {

		}

		if (Robot.climber.temperature() > Power.dangerTemp) {
			climbState = false;
		} else if (Robot.climber.temperature() > Power.warningTemp) {

		}

		if (Robot.drive.current() > Power.primaryAmpLimit * Power.cautionThreshold) {
			driveState = false;
		} else if (Robot.drive.current() > Power.primaryAmpLimit * Power.warningThreshold) {

		}

		if (Robot.drive.temperature() > Power.dangerTemp) {
			driveState = false;
		} else if (Robot.drive.temperature() > Power.warningTemp) {

		}

		if (Robot.pneumatics.current() > Power.pneumaticsAmpLimit * Power.cautionThreshold) {
			pneumaticsState = false;
		} else if (Robot.pneumatics.current() > Power.pneumaticsAmpLimit * Power.warningThreshold) {

		}

		if (Robot.pneumatics.temperature() > Power.dangerTemp + 10.0) {
			pneumaticsState = false;
		} else if (Robot.pneumatics.temperature() > Power.warningTemp) {

		}

		return new boolean[] {armState, climbState, driveState, pneumaticsState};
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
