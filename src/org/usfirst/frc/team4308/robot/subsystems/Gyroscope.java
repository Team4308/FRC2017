package org.usfirst.frc.team4308.robot.subsystems;

import org.usfirst.frc.team4308.AbsoluteDashboard;
import org.usfirst.frc.team4308.util.IAvailable;
import org.usfirst.frc.team4308.util.Loggable;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * A subsystem that controls and logs the output of an attached Gyroscope, in
 * this case, Kauai Labs's navX-MXP.
 * 
 * @author Michael Brown
 *
 */
public class Gyroscope extends Subsystem implements Loggable, IAvailable {

	// TODO: make gyro wait when calibrating gyro.isCalibrating
	// TODO: if we ever use an ultrasonic, we can reconfig its speed of sound
	// based on barometric from the gyro.
	private boolean isAvailable;
	private AHRS gyro;

	public Gyroscope() {
		initialize();
		LiveWindow.addSensor("Gyroscope", "navX-MXP", gyro);
	}

	@Override
	protected void initDefaultCommand() {
	}

	public double pitch() {
		return gyro.getPitch();
	}

	public double roll() {
		return gyro.getRoll();
	}

	public double yaw() {
		return gyro.getYaw();
	}

	public double angle() {
		return gyro.getAngle();
	}

	public double xDistance() {
		return gyro.getDisplacementX();
	}

	public double yDistance() {
		return gyro.getDisplacementY();
	}

	public double zDistance() {
		return gyro.getDisplacementZ();
	}

	public double heading() {
		return gyro.getFusedHeading();
	}

	public void initialize() {
		try {
			gyro = new AHRS(SPI.Port.kMXP);
			isAvailable = true;
		} catch (RuntimeException rte) {
			DriverStation.reportError("Error instantiating navX-MXP: " + rte.getStackTrace(), true);
			isAvailable = false;
		}
	}

	public void reset() {
		if (isAvailable) {
			gyro.resetDisplacement();
			gyro.reset();
		}
	}

	public boolean disturbance() {
		return isAvailable && gyro.isMagneticDisturbance() && gyro.isAltitudeValid();
	}

	public void start() {
		initialize();
	}

	public void stop() {
		reset();
	}

	@Override
	public void log() {
		if (isAvailable) {
			// Display 6-axis Processed Angle Data
			AbsoluteDashboard.Gyroscope().putBoolean("isConnected", gyro.isConnected());
			AbsoluteDashboard.Gyroscope().putBoolean("IsCalibrating", gyro.isCalibrating());
			AbsoluteDashboard.Gyroscope().putNumber("Yaw", gyro.getYaw());
			AbsoluteDashboard.Gyroscope().putNumber("Pitch", gyro.getPitch());
			AbsoluteDashboard.Gyroscope().putNumber("Roll", gyro.getRoll());

			// Display tilt-corrected, Magnetometer-based heading (requires
			// magnetometer calibration to be useful)
			AbsoluteDashboard.Gyroscope().putNumber("CompassHeading", gyro.getCompassHeading());

			// Display 9-axis Heading (requires magnetometer calibration to be
			// useful)
			AbsoluteDashboard.Gyroscope().putNumber("FusedHeading", gyro.getFusedHeading());

			// These functions are compatible w/the WPI Gyro Class, providing a
			// simple path for upgrading from the Kit-of-Parts gyro to the
			// navx-MXP
			AbsoluteDashboard.Gyroscope().putNumber("TotalYaw", gyro.getAngle());
			AbsoluteDashboard.Gyroscope().putNumber("YawRateDPS", gyro.getRate());

			// Display Processed Acceleration Data (Linear Acceleration, Motion
			// Detect)
			AbsoluteDashboard.Gyroscope().putNumber("Accel_X", gyro.getWorldLinearAccelX());
			AbsoluteDashboard.Gyroscope().putNumber("Accel_Y", gyro.getWorldLinearAccelY());
			AbsoluteDashboard.Gyroscope().putBoolean("IsMoving", gyro.isMoving());
			AbsoluteDashboard.Gyroscope().putBoolean("IsRotating", gyro.isRotating());

			// Display estimates of velocity/displacement. Note that these
			// values
			// are not expected to be accurate enough for estimating robot
			// position
			// on a FIRST FRC Robotics Field, due to accelerometer noise and the
			// compounding of these errors due to single (velocity) integration
			// and
			// especially double (displacement) integration.
			AbsoluteDashboard.Gyroscope().putNumber("Velocity_X", gyro.getVelocityX());
			AbsoluteDashboard.Gyroscope().putNumber("Velocity_Y", gyro.getVelocityY());
			AbsoluteDashboard.Gyroscope().putNumber("Displacement_X", gyro.getDisplacementX());
			AbsoluteDashboard.Gyroscope().putNumber("Displacement_Y", gyro.getDisplacementY());

			// Display Raw Gyro/Accelerometer/Magnetometer Values NOTE: These
			// values
			// are not normally necessary, but are made available for advanced
			// users. Before using this data, please consider whether the
			// processed
			// data (see above) will suit your needs.
			AbsoluteDashboard.Gyroscope().putNumber("RawGyro_X", gyro.getRawGyroX());
			AbsoluteDashboard.Gyroscope().putNumber("RawGyro_Y", gyro.getRawGyroY());
			AbsoluteDashboard.Gyroscope().putNumber("RawGyro_Z", gyro.getRawGyroZ());
			AbsoluteDashboard.Gyroscope().putNumber("RawAccel_X", gyro.getRawAccelX());
			AbsoluteDashboard.Gyroscope().putNumber("RawAccel_Y", gyro.getRawAccelY());
			AbsoluteDashboard.Gyroscope().putNumber("RawAccel_Z", gyro.getRawAccelZ());
			AbsoluteDashboard.Gyroscope().putNumber("RawMag_X", gyro.getRawMagX());
			AbsoluteDashboard.Gyroscope().putNumber("RawMag_Y", gyro.getRawMagY());
			AbsoluteDashboard.Gyroscope().putNumber("RawMag_Z", gyro.getRawMagZ());
			AbsoluteDashboard.Gyroscope().putNumber("Temp_C", gyro.getTempC());

			// Omnimount Yaw Axis Information For more info, see
			// http://navx-mxp.kauailabs.com/installation/omnimount
			AHRS.BoardYawAxis yaw_axis = gyro.getBoardYawAxis();
			if (yaw_axis != null) {
				AbsoluteDashboard.Gyroscope().putString("YawAxisDirection", yaw_axis.up ? "Up" : "Down");
				AbsoluteDashboard.Gyroscope().putNumber("YawAxis", yaw_axis.board_axis.getValue());
			}

			// Sensor Board Information
			AbsoluteDashboard.Gyroscope().putString("FirmwareVersion", gyro.getFirmwareVersion());

			// Quaternion Data Quaternions are fascinating, and are the most
			// compact
			// representation of orientation data. All of the Yaw, Pitch and
			// Roll
			// Values can be derived from the Quaternions. If interested in
			// motion
			// processing, knowledge of Quaternions is highly recommended.

			AbsoluteDashboard.Gyroscope().putNumber("QuaternionW", gyro.getQuaternionW());
			AbsoluteDashboard.Gyroscope().putNumber("QuaternionX", gyro.getQuaternionX());
			AbsoluteDashboard.Gyroscope().putNumber("QuaternionY", gyro.getQuaternionY());
			AbsoluteDashboard.Gyroscope().putNumber("QuaternionZ", gyro.getQuaternionZ());

			// Connectivity Debugging Support
			AbsoluteDashboard.Gyroscope().putNumber("Byte_Count", gyro.getByteCount());
			AbsoluteDashboard.Gyroscope().putNumber("Update_Count", gyro.getUpdateCount());
		}
	}

	@Override
	public boolean isAvailable() {
		return isAvailable;
	}

}
