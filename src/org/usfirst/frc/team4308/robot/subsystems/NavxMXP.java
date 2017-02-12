package org.usfirst.frc.team4308.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Handler class responsible for the many functions the Navx-MXP gives
 * @author mike_
 *
 */
public class NavxMXP extends Subsystem {

	private AHRS gyro;
	// TODO: initialize gyro
	// TODO: reset gyro
	// TODO: get raw readings from gyro
	// TODO: get averaged readings from gyro
	// TODO: access accelerometer
	// TODO: add error handling

	public NavxMXP() {
		super();
		try {
			gyro = new AHRS(SPI.Port.kMXP);
		} catch (RuntimeException rte) {
			DriverStation.reportError("Error instantiating navX-MXP: " + rte.getStackTrace(), true);
		}

	}

	public NavxMXP(String name) {
		super(name);
		try {
			gyro = new AHRS(SPI.Port.kMXP);
		} catch (RuntimeException rte) {
			DriverStation.reportError("Error instantiating navX-MXP: " + rte.getStackTrace(), true);
		}
	}

	@Override
	protected void initDefaultCommand() {
		

	}

}
