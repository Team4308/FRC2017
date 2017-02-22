
package org.usfirst.frc.team4308.robot;

import org.usfirst.frc.team4308.robot.commands.DriveLinear;
import org.usfirst.frc.team4308.robot.io.OI;
import org.usfirst.frc.team4308.robot.subsystems.Climber;
import org.usfirst.frc.team4308.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4308.robot.subsystems.NavxMXP;
import org.usfirst.frc.team4308.robot.subsystems.Pneumatics;
import org.usfirst.frc.team4308.util.Loggable;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to each mode, as described in the IterativeRobot documentation. If you change the name of this class or the package after creating this project, you must also update the manifest file in the resource directory.
 */
public class Robot extends IterativeRobot implements Loggable { // TODO: unbreak?

	// public static PowerMonitor powermonitor; // TODO fix this (creates errors. Check github page)
	public static Pneumatics pneumatics;
	public static DriveTrain drive;
	public static Climber climber;
	public static NavxMXP navx;
	public static OI oi;

	Command autonomousCommand;
	// SendableChooser<Command> chooser;

	@Override
	public void robotInit() {
		// powermonitor = new PowerMonitor();
		pneumatics = new Pneumatics();
		drive = new DriveTrain();
		climber = new Climber();
		navx = new NavxMXP();
		oi = new OI();

		autonomousCommand = new DriveLinear();
		// chooser = new SendableChooser<Command>();

		SmartDashboard.putData(drive);
		SmartDashboard.putData(navx);
		// SmartDashboard.putData("Auto Mode", chooser);
	}

	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		// autonomousCommand = chooser.getSelected();

		// String autoSelected = SmartDashboard.getString("Auto Selector",
		// "Default");
		// switch (autoSelected) {
		// case "My Auto":
		// autonomousCommand = new MyAutoCommand();
		// break;
		// case "Default Auto":
		// default:
		// autonomousCommand = new ExampleCommand();
		// break;
		// }

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		log();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		log();
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
		log();
	}

	@Override
	public void log() {
		// pneumatics.log();
		// drive.log();
		climber.log();
		navx.log();
	}
}
