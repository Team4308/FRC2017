package org.usfirst.frc.team4308.auto;

import org.usfirst.frc.team4308.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.hal.AllianceStationID;
import edu.wpi.first.wpilibj.hal.HAL;

public class BlindAuto extends CommandGroup {

	boolean center;

	public BlindAuto() {
		super();
		requires(Robot.drive);
	}

	@Override
	protected void initialize() {
		super.initialize();
		AllianceStationID station = HAL.getAllianceStation();
		if (station.name().contains("2")) {
			center = true;
		} else {
			center = false;
		}
	}

	@Override
	protected void execute() {
		super.execute();
		if (center) {
			addSequential(new CloseClaw());
			// TODO?addSequential(new WaitForPressure());
			addSequential(new EasyAutonomous(2, 1, 1));
			addSequential(new WaitCommand(0.3));
			addSequential(new OpenClaw());
			addSequential(new EasyAutonomous(2, -1, -1));
			addSequential(new CloseClaw());
		} else {
			addSequential(new EasyAutonomous(4, 1, 1));
			// TODO? addParallel(new WaitForPressure());
		}
	}

	@Override
	protected void end() {
		super.end();
		if (Robot.drive != null)
			Robot.drive.stopMotor();
	}

	@Override
	protected void interrupted() {
		super.interrupted();
		end();
	}

}
