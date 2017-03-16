package org.usfirst.frc.team4308.robot.subsystems;

import org.usfirst.frc.team4308.util.Loggable;
import org.usfirst.frc.team4308.util.Loop;
import org.usfirst.frc.team4308.util.Vector2;
import org.usfirst.frc.team4308.util.Vector3;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Gyroscope extends Subsystem implements Loggable, Loop {

	private AHRS gyro;

	private Vector2 fieldLocation;
	private double direction;

	public Gyroscope() {
		LiveWindow.addSensor("navX-MXP", "Gyroscope", gyro);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

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

	public double xDistance() {
		return gyro.getDisplacementX();
	}

	public double yDistance() {
		return gyro.getDisplacementY();
	}

	public double zDistance() {
		return gyro.getDisplacementZ();
	}

	public Vector3 displacement() {
		return new Vector3(gyro.getDisplacementX(), gyro.getDisplacementY(), gyro.getDisplacementZ());
	}

	public Vector3 orientation() {
		return new Vector3(gyro.getPitch(), gyro.getYaw(), gyro.getRoll());
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

	@Override
	public void start() {
		initialize();
	}

	@Override
	public void stop() {
		reset();
	}

	@Override
	public void loop() {
		fieldLocation = new Vector2(gyro.getDisplacementX(), gyro.getDisplacementZ());
	}

	@Override
	public void log() {
		// Display 6-axis Processed Angle Data
		SmartDashboard.putBoolean("IMU_Connected", gyro.isConnected());
		SmartDashboard.putBoolean("IMU_IsCalibrating", gyro.isCalibrating());
		SmartDashboard.putNumber("IMU_Yaw", gyro.getYaw());
		SmartDashboard.putNumber("IMU_Pitch", gyro.getPitch());
		SmartDashboard.putNumber("IMU_Roll", gyro.getRoll());

		// Display tilt-corrected, Magnetometer-based heading (requires
		// magnetometer calibration to be useful)
		SmartDashboard.putNumber("IMU_CompassHeading", gyro.getCompassHeading());

		// Display 9-axis Heading (requires magnetometer calibration to be
		// useful)
		SmartDashboard.putNumber("IMU_FusedHeading", gyro.getFusedHeading());

		// These functions are compatible w/the WPI Gyro Class, providing a
		// simple path for upgrading from the Kit-of-Parts gyro to the navx-MXP
		SmartDashboard.putNumber("IMU_TotalYaw", gyro.getAngle());
		SmartDashboard.putNumber("IMU_YawRateDPS", gyro.getRate());

		// Display Processed Acceleration Data (Linear Acceleration, Motion
		// Detect)
		SmartDashboard.putNumber("IMU_Accel_X", gyro.getWorldLinearAccelX());
		SmartDashboard.putNumber("IMU_Accel_Y", gyro.getWorldLinearAccelY());
		SmartDashboard.putBoolean("IMU_IsMoving", gyro.isMoving());
		SmartDashboard.putBoolean("IMU_IsRotating", gyro.isRotating());

		// Display estimates of velocity/displacement. Note that these values
		// are not expected to be accurate enough for estimating robot position
		// on a FIRST FRC Robotics Field, due to accelerometer noise and the
		// compounding of these errors due to single (velocity) integration and
		// especially double (displacement) integration.
		SmartDashboard.putNumber("Velocity_X", gyro.getVelocityX());
		SmartDashboard.putNumber("Velocity_Y", gyro.getVelocityY());
		SmartDashboard.putNumber("Displacement_X", gyro.getDisplacementX());
		SmartDashboard.putNumber("Displacement_Y", gyro.getDisplacementY());

		// Display Raw Gyro/Accelerometer/Magnetometer Values NOTE: These values
		// are not normally necessary, but are made available for advanced
		// users. Before using this data, please consider whether the processed
		// data (see above) will suit your needs.
		SmartDashboard.putNumber("RawGyro_X", gyro.getRawGyroX());
		SmartDashboard.putNumber("RawGyro_Y", gyro.getRawGyroY());
		SmartDashboard.putNumber("RawGyro_Z", gyro.getRawGyroZ());
		SmartDashboard.putNumber("RawAccel_X", gyro.getRawAccelX());
		SmartDashboard.putNumber("RawAccel_Y", gyro.getRawAccelY());
		SmartDashboard.putNumber("RawAccel_Z", gyro.getRawAccelZ());
		SmartDashboard.putNumber("RawMag_X", gyro.getRawMagX());
		SmartDashboard.putNumber("RawMag_Y", gyro.getRawMagY());
		SmartDashboard.putNumber("RawMag_Z", gyro.getRawMagZ());
		SmartDashboard.putNumber("IMU_Temp_C", gyro.getTempC());

		// Omnimount Yaw Axis Information For more info, see
		// http://navx-mxp.kauailabs.com/installation/omnimount
		AHRS.BoardYawAxis yaw_axis = gyro.getBoardYawAxis();
		SmartDashboard.putString("YawAxisDirection", yaw_axis.up ? "Up" : "Down");
		SmartDashboard.putNumber("YawAxis", yaw_axis.board_axis.getValue());

		// Sensor Board Information
		SmartDashboard.putString("FirmwareVersion", gyro.getFirmwareVersion());

		// Quaternion Data Quaternions are fascinating, and are the most compact
		// representation of orientation data. All of the Yaw, Pitch and Roll
		// Values can be derived from the Quaternions. If interested in motion
		// processing, knowledge of Quaternions is highly recommended.

		SmartDashboard.putNumber("QuaternionW", gyro.getQuaternionW());
		SmartDashboard.putNumber("QuaternionX", gyro.getQuaternionX());
		SmartDashboard.putNumber("QuaternionY", gyro.getQuaternionY());
		SmartDashboard.putNumber("QuaternionZ", gyro.getQuaternionZ());

		// Connectivity Debugging Support
		SmartDashboard.putNumber("IMU_Byte_Count", gyro.getByteCount());
		SmartDashboard.putNumber("IMU_Update_Count", gyro.getUpdateCount());
	}

}
