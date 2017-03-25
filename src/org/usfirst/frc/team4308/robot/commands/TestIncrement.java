package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.RobotMap;

import com.ctre.CANTalon;

public class TestIncrement extends ToggleCommand {

	public TestIncrement() {
		super(false);
	}

	@Override
	protected void toggleOn() {
		new CANTalon(RobotMap.Drive.leftFront).set(-0.65);
		new CANTalon(RobotMap.Drive.leftMiddle).set(-0.65);
		new CANTalon(RobotMap.Drive.leftBack).set(-0.65);
		new CANTalon(RobotMap.Drive.rightFront).set(0);
		new CANTalon(RobotMap.Drive.rightMiddle).set(0);
		new CANTalon(RobotMap.Drive.rightBack).set(0);
	}

	@Override
	protected void toggleOff() {
		new CANTalon(RobotMap.Drive.leftFront).set(0);
		new CANTalon(RobotMap.Drive.leftMiddle).set(0);
		new CANTalon(RobotMap.Drive.leftBack).set(0);
		new CANTalon(RobotMap.Drive.rightFront).set(0.65);
		new CANTalon(RobotMap.Drive.rightMiddle).set(0.65);
		new CANTalon(RobotMap.Drive.rightBack).set(0.65);
	}

}
