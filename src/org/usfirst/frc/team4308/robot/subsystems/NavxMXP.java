package org.usfirst.frc.team4308.robot.subsystems;

import org.usfirst.frc.team4308.util.OverflowingArrayList;
import org.usfirst.frc.team4308.util.Vector3;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * Handler class responsible for the many functions the Navx-MXP gives
 * 
 * @author Michael
 *
 */
public class NavxMXP extends Subsystem {

	private static final int READING_DEPTH = 25;

	private AHRS gyro;

	private OverflowingArrayList<Vector3> gyroReadings;
	private OverflowingArrayList<Vector3> displacements;

	// TODO: initialize gyro
	// TODO: reset gyro
	// TODO: get raw readings from gyro
	// TODO: get averaged readings from gyro
	// TODO: access accelerometer
	// TODO: add error handling

	public NavxMXP() {
		super();
		reset();
	}

	@Override
	protected void initDefaultCommand() {
	}

	public Vector3 orientation() {
		Vector3 reading = new Vector3(gyro.getPitch(), gyro.getYaw(), gyro.getRoll());
		gyroReadings.add(reading);
		return reading;
	}

	public Vector3 avgOrientation() {
		double xSum = 0.0;
		double ySum = 0.0;
		double zSum = 0.0;

		for (Vector3 vect : gyroReadings) {
			xSum += vect.x;
			ySum += vect.y;
			zSum += vect.z;
		}

		return new Vector3(xSum / gyroReadings.size(), ySum / gyroReadings.size(), zSum / gyroReadings.size());
	}

	public Vector3 displacement() {
		Vector3 reading = new Vector3(gyro.getDisplacementX(), gyro.getDisplacementY(), gyro.getDisplacementZ());
		displacements.add(reading);
		return reading;
	}

	public Vector3 avgDisplacement() {
		double xSum = 0.0;
		double ySum = 0.0;
		double zSum = 0.0;

		for (Vector3 vect : displacements) {
			xSum += vect.x;
			ySum += vect.y;
			zSum += vect.z;
		}

		return new Vector3(xSum / displacements.size(), ySum / displacements.size(), zSum / displacements.size());
	}

	public double yaw() {
		return gyro.getYaw();
	}

	public double roll() {
		return gyro.getRoll();
	}

	public double pitch() {
		return gyro.getPitch();
	}

	public boolean reset() {
		boolean status = true;
		try {
			gyro = new AHRS(SPI.Port.kMXP);
		} catch (RuntimeException rte) {
			DriverStation.reportError("Error instantiating navX-MXP: " + rte.getStackTrace(), true);
			status = false;
		}

		gyroReadings = new OverflowingArrayList<Vector3>(READING_DEPTH);
		displacements = new OverflowingArrayList<Vector3>(READING_DEPTH);

		LiveWindow.addSensor("NavX-MXP", "Gyroscope", gyro);
		
		return status;
	}

	public void recalibrate() {
		gyro.resetDisplacement();
		gyro.reset();
	}

	public boolean disturbance() {
		return gyro.isMagneticDisturbance() && gyro.isAltitudeValid();
	}

}
