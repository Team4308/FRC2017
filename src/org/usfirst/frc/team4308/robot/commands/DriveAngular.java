//package org.usfirst.frc.team4308.robot.commands;
//
//import org.usfirst.frc.team4308.robot.Robot;
//import org.usfirst.frc.team4308.robot.RobotMap;
//
//public class DriveAngular extends Control {
//
//	private static final int defaultTimeout = 2;
//	private static final double tolerance = 2.0;
//
//	private final double angle;
//	private final double maxSpeed;
//	private double error;
//
//	public DriveAngular(double angle) {
//		this(angle, RobotMap.AUTONOMOUS.maxRotateSpeed);
//		requires(Robot.navx);
//	}
//
//	public DriveAngular(double angle, double maxSpeed) {
//		this(angle, maxSpeed, defaultTimeout);
//	}
//
//	public DriveAngular(double angle, double maxSpeed, double timeout) {
//		super(timeout);
//		this.angle = angle;
//		this.maxSpeed = maxSpeed;
//	}
//
//	@Override
//	protected void execute() {
//		error = angle - Robot.navx.yaw();
//		if (error > angle + tolerance && error > angle - tolerance) {
//			Robot.drivetrain.arcadeDrive(0, maxSpeed);
//		} else if (error < angle + tolerance && error < angle - tolerance) {
//			Robot.drivetrain.arcadeDrive(0, -maxSpeed);
//		} else {
//			end();
//		}
//	}
//
//	@Override
//	protected boolean isFinished() {
//		return false;
//	}
//
//}
