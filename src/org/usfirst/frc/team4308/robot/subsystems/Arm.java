package org.usfirst.frc.team4308.robot.subsystems;

import org.usfirst.frc.team4308.robot.RobotMap;
import org.usfirst.frc.team4308.util.Loggable;
import org.usfirst.frc.team4308.util.Powered;

import com.ctre.MotorControl.CANTalon;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.MotorSafety;
import edu.wpi.first.wpilibj.MotorSafetyHelper;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A subsystem used to control an arm that can pickup and dropoff gears.
 * 
 * @author Michael Brown
 *
 */
public class Arm extends Subsystem implements Loggable, Powered, MotorSafety, SpeedController {

	// private static final double MAX_OUTPUT = 0.65;
	// private static final double DROP_TIME = 1;
	// private static final double CLAW_TIME = 0.5;

	protected MotorSafetyHelper safetyHelper;

	private DoubleSolenoid claw;
	private CANTalon arm;
	private DigitalInput limitSwitchUp, limitSwitchDown;
	// private AnalogInput ultrasonic;

	private boolean grab;
	private boolean down;

	public Arm() {
		super();
		claw = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.GearArm.solenoidA, RobotMap.GearArm.solenoidB);
		arm = new CANTalon(RobotMap.GearArm.armChannel);
		arm.setInverted(true);
		try {
			limitSwitchUp = new DigitalInput(1);
			limitSwitchDown = new DigitalInput(0);
		} catch (Exception e) {
			DriverStation.reportWarning("Plugg in the switcch you asshat", true);
		}

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
		closeClaw();
	}

	public void claw() {
		claw(!grab);
	}

	public void claw(boolean state) {
		grab = state;

		if (state) {
			claw.set(Value.kForward);
		} else {
			claw.set(Value.kReverse);
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
		if (!grab)
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

	public boolean isArmUp() {
		try {
			return !limitSwitchUp.get();
		} catch (Exception e) {
			DriverStation.reportWarning("Plug in the switcch you asshat", false);
		}
		return false;
	}

	public boolean isArmDown() {
		try {
			return limitSwitchDown.get();
		} catch (Exception e) {
			DriverStation.reportWarning("Plug in the switcch you asshat", false);
		}
		return false;
	}
}
