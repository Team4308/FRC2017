package org.usfirst.frc.team4308.util;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;

public class NavxMXP {

	private AHRS gyro;

	public NavxMXP() {
		initialize();
	}
	
	public double pitch() {
		return gyro.getPitch();
	}
	
	public double yaw() {
		return gyro.getYaw();
	}
	
	public double roll() {
		return gyro.getRoll();
	}

	public Vector3 orientation() {
		return new Vector3(gyro.getPitch(), gyro.getYaw(), gyro.getRoll());
	}

	public Vector3 displacement() {
		return new Vector3(gyro.getDisplacementX(), gyro.getDisplacementY(), gyro.getDisplacementZ());
	}

	public void initialize() {
		try {
			gyro = new AHRS(SPI.Port.kMXP);
		} catch (RuntimeException rte) {
			DriverStation.reportError("Error instantiating navX-MXP: " + rte.getStackTrace(), true);
			return;
		}
	}

	public void reset() {
		gyro.resetDisplacement();
		gyro.reset();
	}

	public boolean disturbance() {
		return gyro.isMagneticDisturbance() && gyro.isAltitudeValid();
	}

}
