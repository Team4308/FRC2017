package org.usfirst.frc.team4308.robot.subsystems;

import org.usfirst.frc.team4308.robot.RobotMap;
import org.usfirst.frc.team4308.robot.commands.ArmControl;
import org.usfirst.frc.team4308.util.Loggable;
import org.usfirst.frc.team4308.util.Powered;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Arm extends PIDSubsystem implements Loggable, Powered {

	private Solenoid claw;
	private CANTalon arm;
	private AnalogPotentiometer armAngle;

	private boolean grab;

	public Arm() {
		super(RobotMap.Constant.proportional, RobotMap.Constant.integral, RobotMap.Constant.differential);
		armAngle = new AnalogPotentiometer(RobotMap.GearArm.potentiometerChannel, RobotMap.GearArm.potentiometerRange);
		grab = false;
		claw = new Solenoid(RobotMap.GearArm.pistonChannel);
		arm = new CANTalon(RobotMap.GearArm.armChannel);

		setPercentTolerance(RobotMap.GearArm.tolerancePercent);
		getPIDController().setContinuous(false);
		reset();
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ArmControl());
	}

	public void claw() {
		claw.set(grab);
		grab = !grab;
	}

	// TODO: test
	public void setAngle(double angle) {
		setSetpoint(angle);
	}

	// TODO: test
	public void reset() {
		setSetpoint(RobotMap.GearArm.restingAngle);
	}

	public double angle() {
		return armAngle.get();
	}

	@Override
	protected double returnPIDInput() {
		return armAngle.pidGet();
	}

	@Override
	protected void usePIDOutput(double output) {
		arm.set(output);
	}

	@Override
	public double voltage() {
		return arm.getOutputVoltage();
	}

	@Override
	public double current() {
		return arm.getOutputCurrent();
	}

	@Override
	public double temperature() {
		return arm.getTemperature();
	}

	@Override
	public void log() {
		SmartDashboard.putNumber("Arm Angle", angle());
		SmartDashboard.putBoolean("Claw State", grab);
	}

}
