
package org.usfirst.frc.team4308.robot;

import java.util.HashMap;

import org.usfirst.frc.team4308.robot.commands.DriveAngular;
import org.usfirst.frc.team4308.robot.commands.DriveLinear;
import org.usfirst.frc.team4308.robot.io.OI;
import org.usfirst.frc.team4308.robot.subsystems.Arm;
import org.usfirst.frc.team4308.robot.subsystems.Climber;
import org.usfirst.frc.team4308.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4308.robot.subsystems.Gyroscope;
import org.usfirst.frc.team4308.robot.subsystems.Pneumatics;
import org.usfirst.frc.team4308.robot.subsystems.PowerMonitor;
import org.usfirst.frc.team4308.util.Loggable;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot implements Loggable {

	public static PowerMonitor powermonitor;
	public static Pneumatics pneumatics;
	public static DriveTrain drive;
	public static Climber climber;
	public static Gyroscope gyro;
	public static Arm arm;
	public static OI oi;
	public static HashMap<String, Command> commands;

	private SendableChooser<Command> autoChooser;
	private Command autonomousCommand;

	@Override
	public void robotInit() {
		commands = new HashMap<String, Command>();
		powermonitor = new PowerMonitor();
		pneumatics = new Pneumatics();
		drive = new DriveTrain();
		climber = new Climber();
		gyro = new Gyroscope();
		arm = new Arm();
		oi = new OI();

		autoChooser = new SendableChooser<Command>();
		autoChooser.addDefault("Drive Forward", new DriveLinear());
		autoChooser.addObject("Drive Backward", new DriveLinear(-10));
		autoChooser.addObject("Orient to 0", new DriveAngular());
		autoChooser.addObject("Orient to 90", new DriveAngular(90.0));
		autoChooser.addObject("Orient to 180", new DriveAngular(180.0));
		autoChooser.addObject("Orient to 270", new DriveAngular(270.0));
		SmartDashboard.putData("Autonomous mode chooser", autoChooser);
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
		autonomousCommand = autoChooser.getSelected();
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
		gyro.log();
	}
}
