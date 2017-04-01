package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.io.IO;
import org.usfirst.frc.team4308.robot.subsystems.Arm;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Continuous command to send the throttle axis (specified in {@link IO}) to the
 * {@link Arm} subsystem, in order to control the pitch of the arm.
 * 
 * @author Michael Brown
 *
 */
public class ArmControl extends ToggleCommand {

	private boolean direction = false;

	public ArmControl() {
		super();
		requires(Robot.arm);

		SmartDashboard.putString("Arm State", "Off");

	}

	@Override
	protected boolean isFinished() {
		SmartDashboard.putString("Arm State", "Off");

		DriverStation.reportWarning("direction: " + direction, false);
		DriverStation.reportWarning("isArmUp: " + Robot.arm.isArmUp(), false);
		DriverStation.reportWarning("isArmDown: " + Robot.arm.isArmDown(), false);

		return (direction && Robot.arm.isArmUp()) || (!direction && Robot.arm.isArmDown());
	}

	@Override
	protected void end() {
		Robot.arm.stopMotor();
	}

	@Override
	protected void toggleOn() {
		Robot.arm.set(-0.75);
		SmartDashboard.putString("Arm State", "Moving Up");
		direction = true;
	}

	@Override
	protected void toggleOff() {
		Robot.arm.set(0.75);
		SmartDashboard.putString("Arm State", "Moving Down");
		direction = false;
	}

}
