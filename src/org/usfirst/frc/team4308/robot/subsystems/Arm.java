package org.usfirst.frc.team4308.robot.subsystems;

import org.usfirst.frc.team4308.robot.RobotMap;
import org.usfirst.frc.team4308.util.Loggable;
import org.usfirst.frc.team4308.util.Powered;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.MotorSafety;
import edu.wpi.first.wpilibj.MotorSafetyHelper;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Arm extends Subsystem implements Loggable, Powered, MotorSafety, SpeedController {

	// private static final double MAX_OUTPUT = 0.65;
	// private static final double DROP_TIME = 1;
	// private static final double CLAW_TIME = 0.5;

	protected MotorSafetyHelper safetyHelper;

	private DoubleSolenoid claw;
	private CANTalon arm;
	private DigitalInput limitSwitchUp;
	// private AnalogInput ultrasonic;

	private boolean grab;
	private boolean down;

	public Arm() {
		super();
		claw = new DoubleSolenoid(RobotMap.PCM, RobotMap.GearArm.solenoidA, RobotMap.GearArm.solenoidB);
		arm = new CANTalon(RobotMap.GearArm.armChannel);
		arm.setInverted(true);
		limitSwitchUp = new DigitalInput(2);
		// ultrasonic = new AnalogInput(RobotMap.GearArm.sensorChannel);
		safetyHelper = new MotorSafetyHelper(this);
		safetyHelper.setExpiration(0.5D);
		safetyHelper.setSafetyEnabled(false);

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
		claw(!grab);
	}

	public void claw(boolean state) {
		if (!state) {
			claw.set(Value.kReverse);
		} else {
			claw.set(Value.kForward);
		}

	}

	public void openClaw() {
		claw(true);
	}

	public void closeClaw() {
		claw(false);
	}

	@Override
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
		SmartDashboard.putString("DB/String 2", grab ? "Claw Open" : "Claw Closed");
		SmartDashboard.putString("DB/String 3", down ? "Arm Down" : "Arm Up");
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

	@SuppressWarnings("deprecation")
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
	
	public boolean isArmUp(){
		return limitSwitchUp.get();
	}

}
