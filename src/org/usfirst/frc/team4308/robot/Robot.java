
package org.usfirst.frc.team4308.robot;

import org.usfirst.frc.team4308.robot.subsystems.Climber;
import org.usfirst.frc.team4308.robot.subsystems.DriveSamson;
import org.usfirst.frc.team4308.robot.subsystems.NavxMXP;
import org.usfirst.frc.team4308.robot.subsystems.Pneumatics;
import org.usfirst.frc.team4308.util.Loggable;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
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
public class Robot extends IterativeRobot implements Loggable { // TODO:
																// unbreak?
	// public static PowerMonitor powermonitor; // TODO fix this before adding
	// it back
	public static Pneumatics pneumatics;
	public static DriveSamson drivetrain;
	public static Climber climber;
	public static NavxMXP navx;
	// TODO public static OI oi;
	public static Joystick joystick;

	Command autonomousCommand;
	SendableChooser<Command> chooser;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {

		// powermonitor = new PowerMonitor();
		pneumatics = new Pneumatics();
		// drivetrain = new DriveTrain();
		drivetrain = new DriveSamson();
		joystick = new Joystick(RobotMap.CONTROL.driveStick);

		climber = new Climber();
		navx = new NavxMXP();
		// TODO oi = new OI();

		// TODO autonomousCommand = new DriveLinear();

		SmartDashboard.putData(drivetrain);
		SmartDashboard.putData(navx);

		chooser = new SendableChooser<Command>();
		// TODO remove whatever this garbag is chooser.addDefault("Move
		// Forward", new TankControl());
		SmartDashboard.putData("Auto Mode", chooser);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
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

	private static final int CONTROLLER_LEFT_STICK_X = 0;
	private static final int CONTROLLER_LEFT_STICK_Y = 1;
	private static final int CONTROLLER_RIGHT_STICK_X = 4;
	private static final int CONTROLLER_RIGHT_STICK_Y = 5;

	@Override
	public void teleopPeriodic() {
		// Passes the joystick input to the bot's drive control
		drivetrain.execute(joystick.getRawAxis(CONTROLLER_LEFT_STICK_X), joystick.getRawAxis(CONTROLLER_LEFT_STICK_Y), joystick.getRawAxis(CONTROLLER_RIGHT_STICK_X), joystick.getRawAxis(CONTROLLER_RIGHT_STICK_Y));

		Scheduler.getInstance().run();
		log();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
		log();
	}

	@Override
	public void log() {
		// pneumatics.log();
		// drivetrain.log();
		climber.log();
		navx.log();
	}

}
