package org.usfirst.frc.team4308.robot.subsystems;

import org.usfirst.frc.team4308.util.Loggable;
import org.usfirst.frc.team4308.util.NavxMXP;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Gyroscope extends Subsystem implements Loggable {

	private NavxMXP gyro;

	public Gyroscope() {
		gyro = new NavxMXP();
	}

	public double azimuth() {
		return gyro.yaw();
	}

	public double altitude() {
		return gyro.pitch();
	}

	public double zenith() {
		return gyro.roll();
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

	@Override
	public void log() {
		SmartDashboard.putNumber("Azimuth", azimuth());
		SmartDashboard.putNumber("X Position", gyro.displacement().x);
		SmartDashboard.putNumber("Z Position", gyro.displacement().z);
	}

}
