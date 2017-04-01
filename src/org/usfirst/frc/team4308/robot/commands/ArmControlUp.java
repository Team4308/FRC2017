package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.RobotMap;
import org.usfirst.frc.team4308.robot.io.IO;
import org.usfirst.frc.team4308.robot.subsystems.Arm;

import edu.wpi.first.wpilibj.command.Command;

public class ArmControlUp extends Command {

	public ArmControlUp() {
		super();
		requires(Robot.arm);
	}

	@Override
	protected boolean isFinished() {
		return Robot.arm.isArmUp();
	}

	@Override
	protected void execute() {
		super.execute();
		Robot.arm.set(RobotMap.GearArm.speedUp);
	}

	@Override
	protected void end() {
		Robot.arm.stopMotor();
	}
}
