package org.usfirst.frc.team4308.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Abstract class that gives a framework to specify multiple drivetrain control types.
 * @author mike_
 *
 */
public abstract class Drive extends Subsystem {

	protected RobotDrive drive;

	public Drive(SpeedController... controllers) {
		switch (controllers.length) {
		case 2:
			drive = new RobotDrive(controllers[0], controllers[1]);
			break;
		case 4:
			drive = new RobotDrive(controllers[0], controllers[1], controllers[2], controllers[3]);
			break;
		default:
			DriverStation.reportError("Invalid number of motor channels given for drive control", false);
		}
	}

	public Drive(String name, SpeedController... controllers) {
		super(name);
		switch (controllers.length) {
		case 2:
			drive = new RobotDrive(controllers[0], controllers[1]);
			break;
		case 4:
			drive = new RobotDrive(controllers[0], controllers[1], controllers[2], controllers[3]);
			break;
		default:
			DriverStation.reportError("Invalid number of motor channels given for drive control", false);
		}
	}

	@Override
	protected void initDefaultCommand() {
		

	}

}
