package org.usfirst.frc.team4308.robot.subsystems;

import org.usfirst.frc.team4308.robot.RobotMap;
import org.usfirst.frc.team4308.robot.commands.ArmControl;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

public class Arm extends Subsystem {

	private static final double restingAngle = 45.0;

	private Solenoid claw;
	private CANTalon arm;
	private Potentiometer armAngle;
	private PIDController armController;

	private boolean grab;

	public Arm() {
		claw = new Solenoid(RobotMap.ARM.pistonChannel);
		arm = new CANTalon(RobotMap.ARM.armChannel);

		armController = new PIDController(RobotMap.CONSTANT.proportional, RobotMap.CONSTANT.integral,
				RobotMap.CONSTANT.differential, armAngle, arm);
		armController.enable();
		armController.setPercentTolerance(RobotMap.ARM.tolerancePercent);
		armController.setContinuous(false);
		armController.setSetpoint(restingAngle);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ArmControl());
	}

	public void claw() {
		claw.set(grab);
		grab = !grab;
	}

	public void setAngle(double angle) { // TODO: convert value from -1 to 1 into the 0V to 5V the potentiometer uses
		armController.setSetpoint(angle);
	}

	public void reset() {
		armController.setSetpoint(restingAngle);
	}

}
