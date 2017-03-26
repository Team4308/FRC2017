package org.usfirst.frc.team4308.robot.subsystems;

import org.usfirst.frc.team4308.robot.RobotMap;
import org.usfirst.frc.team4308.robot.commands.ArmControl;
import org.usfirst.frc.team4308.util.Loggable;
import org.usfirst.frc.team4308.util.Loop;
import org.usfirst.frc.team4308.util.Powered;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.MotorSafety;
import edu.wpi.first.wpilibj.MotorSafetyHelper;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Arm extends Subsystem implements Loggable, Powered, MotorSafety, SpeedController {

	private static final double MAX_OUTPUT = 0.65;
	private static final double DROP_TIME = 1;
	private static final double CLAW_TIME = 0.5;

	protected MotorSafetyHelper safetyHelper;

	private DoubleSolenoid claw;
	private CANTalon arm;
	private AnalogInput ultrasonic;

	private boolean grab;
	private boolean down;

	public Arm() {
		super();
		claw = new DoubleSolenoid(RobotMap.PCM, RobotMap.GearArm.solenoidA, RobotMap.GearArm.solenoidB);
		arm = new CANTalon(RobotMap.GearArm.armChannel);
		arm.setInverted(true);
		// ultrasonic = new AnalogInput(RobotMap.GearArm.sensorChannel);
		safetyHelper = new MotorSafetyHelper(this);
		safetyHelper.setExpiration(0.5D);
		safetyHelper.setSafetyEnabled(true);

		grab = false;
		down = false;

		LiveWindow.addActuator("Arm", "Motor", arm);
		LiveWindow.addActuator("Arm", "Piston", claw);
		// LiveWindow.addSensor("Arm", "Ultrasonic Sensor", ultrasonic);
	}

	@Override
	protected void initDefaultCommand() {
	}

	public void claw() {
		if (down) { // Open and control enabled
			if (grab) {
				claw.set(Value.kReverse);
			} else {
				claw.set(Value.kForward);
			}
			grab = !grab;
		} else { // Closed and control disabled
			claw.set(Value.kForward);
		}
	}

	public void claw(boolean state) {
		if (down) { // Open and control enabled
			if (state) {
				claw.set(Value.kReverse);
			} else {
				claw.set(Value.kForward);
			}
			grab = state;
		} else { // Closed and control disabled
			claw.set(Value.kForward);
		}
	}

	public void arm() {
		down = !down;
		if (down) {
			claw(true);
			Timer.delay(CLAW_TIME);
			arm.set(1.0);
			Timer.delay(DROP_TIME);
			claw(false);
			Timer.delay(CLAW_TIME);
		} else {
			claw(true);
			Timer.delay(CLAW_TIME);
			arm.set(0);
			Timer.delay(DROP_TIME);
			claw(true);
		}
	}

	public void set(double output) {
		arm.set(output);
	}

	protected static double limit(double num) {
		return num > 1.0D ? 1.0D : (num < -1.0D ? -1.0D : num);
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
		SmartDashboard.putString("Claw State", grab ? "Open" : "Closed");
		SmartDashboard.putString("Arm State", down ? "Down" : "Up");
	}

	@Override
	public void setExpiration(double expirationTime) {
		safetyHelper.setExpiration(expirationTime);
	}

	@Override
	public double getExpiration() {
		return safetyHelper.getExpiration();
	}

	@Override
	public boolean isAlive() {
		return safetyHelper.isAlive();
	}

	@Override
	public void stopMotor() {
		arm.stopMotor();
	}

	@Override
	public void setSafetyEnabled(boolean enabled) {
		safetyHelper.setSafetyEnabled(enabled);
	}

	@Override
	public boolean isSafetyEnabled() {
		return safetyHelper.isSafetyEnabled();
	}

	@Override
	public String getDescription() {
		return this.getClass().getSimpleName();
	}

	@Override
	public void pidWrite(double output) {
		arm.pidWrite(output);
	}

	@Override
	public double get() {
		return arm.get();
	}

	@Override
	public void setInverted(boolean isInverted) {
		arm.setInverted(isInverted);
	}

	@Override
	public boolean getInverted() {
		return arm.getInverted();
	}

	@Override
	public void disable() {
		arm.disable();
	}

}
