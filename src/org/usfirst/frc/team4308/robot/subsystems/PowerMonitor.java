package org.usfirst.frc.team4308.robot.subsystems;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.RobotMap;
import org.usfirst.frc.team4308.robot.RobotMap.Power;
import org.usfirst.frc.team4308.util.IAvailable;
import org.usfirst.frc.team4308.util.Loggable;

import com.ctre.MotorControl.CANTalon;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Currently unimplemented power management system. Allows techniques such as
 * brownout avoidance and extraneous system shutoff.
 * 
 * @author Michael Brown
 *
 */
public class PowerMonitor extends Subsystem implements Loggable, IAvailable {

	private final PowerDistributionPanel pdp;

	private Power.BatteryLevel batteryLevel;

	private boolean isAvailable;

	private boolean currentWarning;
	private boolean temperatureWarning;

	public PowerMonitor() {
		super();
		pdp = new PowerDistributionPanel(RobotMap.PDP_ID);
		if (pdp != null) {
			batteryLevel = Power.BatteryLevel.level(pdp.getVoltage());
			isAvailable = true;
		} else {
			isAvailable = false;
		}
		currentWarning = false;
		temperatureWarning = false;
	}

	@Override
	protected void initDefaultCommand() {
	}

	public PowerDistributionPanel getPDP() {
		return pdp;
	}

	/**
	 * Polls the main power delivery systems for any non-optimal or unhealthy
	 * scenarios.
	 * 
	 * @return Whether the system is pulling potentially harmful levels of power
	 *         or not
	 */
	public boolean powerCheck() {
		if (isAvailable) {
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
		} else {
			return false;
		}
	}

	// TODO: current-reactive motor speed limiting for arm, climber and drive
	// TODO: voltage-reactive warning for arm, climber and drive
	// TODO: temperature-reactive motor speed limiting for drive
	// TODO: temperature-reactive warning for arm and climber
	/**
	 * Polls each subsystems power status, determining whether their power using
	 * components are taking too much power or producing too much heat. Data is
	 * returned in an order of; {@link Arm}, {@link Climber},
	 * {@link DriveTrain}, and {@link Pneumatics}.
	 * 
	 * @return set of booleans that represent whether any of the power-drawing
	 *         systems are within an unhealthy state
	 */
	public boolean[] systemCheck() {
		boolean armState = true;
		boolean climbState = true;
		boolean driveState = true;
		boolean pneumaticsState = true;

		if (Robot.arm != null) {
			if (Robot.arm.current() > Power.secondaryAmpLimit * Power.cautionThreshold) {
				armState = false;
			} else if (Robot.arm.current() > Power.secondaryAmpLimit * Power.warningThreshold) {

			}
		}

		if (Robot.arm != null) {
			if (Robot.arm.temperature() > Power.dangerTemp) {
				armState = false;
			} else if (Robot.arm.temperature() > Power.warningTemp) {

			}
		}

		// if (Robot.climber.current() > Power.secondaryAmpLimit *
		// Power.cautionThreshold) {
		// climbState = false;
		// } else if (Robot.climber.current() > Power.secondaryAmpLimit *
		// Power.warningThreshold) {
		//
		// }
		//
		// if (Robot.climber.temperature() > Power.dangerTemp) {
		// climbState = false;
		// } else if (Robot.climber.temperature() > Power.warningTemp) {
		//
		// }

		if (Robot.drive != null) {
			if (Robot.drive.current() > Power.primaryAmpLimit * Power.cautionThreshold) {
				driveState = false;
			} else if (Robot.drive.current() > Power.primaryAmpLimit * Power.warningThreshold) {

			}

			if (Robot.drive.temperature() > Power.dangerTemp) {
				driveState = false;
			} else if (Robot.drive.temperature() > Power.warningTemp) {

			}
		}

		if (Robot.pneumatics != null) {
			if (Robot.pneumatics.current() > Power.pneumaticsAmpLimit * Power.cautionThreshold) {
				pneumaticsState = false;
			} else if (Robot.pneumatics.current() > Power.pneumaticsAmpLimit * Power.warningThreshold) {

			}

			if (Robot.pneumatics.temperature() > Power.dangerTemp + 10.0) {
				pneumaticsState = false;
			} else if (Robot.pneumatics.temperature() > Power.warningTemp) {

			}
		}

		return new boolean[] { armState, climbState, driveState, pneumaticsState };
	}

	/**
	 * Whether or not the system's power delivery system is currently in a power
	 * draw state that is unhealthy.
	 * 
	 * @return Unhealthy or (relatively) healthy
	 */
	public boolean currentWarning() {
		return currentWarning;
	}

	public boolean temperatureWarning() {
		return temperatureWarning;
	}

	@Override
	public void log() {
		powerCheck();
		systemCheck();

		SmartDashboard.putNumber("PDP Current", pdp.getTotalCurrent());

		SmartDashboard.putBoolean("Temperature Warning", temperatureWarning);
		SmartDashboard.putBoolean("Current Warning", currentWarning);
	}

	/**
	 * Determines the ratio of voltage sent to a CANTalon (bus) and the voltage
	 * sent to the motor it is attached to (output).
	 * 
	 * @param talon
	 *            The CANTalon whose voltage is under examination.
	 * @return The ratio of voltage in and voltage out.
	 */
	public double voltageRatio(CANTalon talon) {
		return talon.getOutputVoltage() / talon.getBusVoltage();
	}

	/**
	 * Determines the ratio of current sent to a CANTalon (input) and the
	 * current sent to the motor it is controlling (output).
	 * 
	 * @param talon
	 *            The CANTalon whose current is under examination.
	 * @return The ratio of current in and current out.
	 */
	public double currentRatio(CANTalon talon) {
		return isAvailable ? talon.getOutputCurrent() / pdp.getCurrent(talon.getDeviceID()) : 0;
	}

	@Override
	public boolean isAvailable() {
		return isAvailable;
	}

}
