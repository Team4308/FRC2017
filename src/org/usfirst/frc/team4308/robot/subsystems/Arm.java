package org.usfirst.frc.team4308.robot.subsystems;

import org.usfirst.frc.team4308.robot.RobotMap;
import org.usfirst.frc.team4308.robot.commands.ArmControl;
import org.usfirst.frc.team4308.util.Loggable;
import org.usfirst.frc.team4308.util.Powered;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Arm extends PIDSubsystem implements Loggable, Powered {

	private DoubleSolenoid claw;
	private CANTalon arm;
	private AnalogPotentiometer armAngle;

	private boolean grab;

	public Arm() {
		super(Arm.class.getName(), RobotMap.Constant.proportional, RobotMap.Constant.integral,
				RobotMap.Constant.differential, RobotMap.GearArm.feedForward);

		armAngle = new AnalogPotentiometer(RobotMap.GearArm.potentiometerChannel, RobotMap.GearArm.potentiometerRange);
		claw = new DoubleSolenoid(RobotMap.GearArm.forwardChannel, RobotMap.GearArm.backwardChannel);
		arm = new CANTalon(RobotMap.GearArm.armChannel);

		grab = false;

		getPIDController().setInputRange(-180.0f, 180.0f);
		setPercentTolerance(RobotMap.GearArm.tolerancePercent);
		getPIDController().setContinuous(false);
		reset();
		
		LiveWindow.addSensor("Arm", "Potentiometer", armAngle);
		LiveWindow.addActuator("Arm", "Motor", arm);
		LiveWindow.addActuator("Arm", "Piston", claw);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ArmControl());
	}

	public void claw() {
		if (grab) {
			claw.set(DoubleSolenoid.Value.kForward);
		} else {
			claw.set(DoubleSolenoid.Value.kReverse);
		}
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
