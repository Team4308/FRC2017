
package org.usfirst.frc.team4308.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4308.robot.commands.ExampleCommand;
import org.usfirst.frc.team4308.robot.subsystems.ExampleSubsystem;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.kauailabs.navx.frc.AHRS;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot implements PIDOutput {

	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static OI oi;

	RobotDrive robot;
	Joystick stick;
	CANTalon shooter;
	AHRS gyro;
	Command autonomousCommand;
	PIDController turnController;
	SendableChooser<Command> chooser;
	double rotateToAngleRate;

	static final double kP = 0.03;
	static final double kI = 0.00;
	static final double kD = 0.00;
	static final double kF = 0.00;

	static final double kToleranceDegrees = 2.0f;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		chooser = new SendableChooser<Command>();
		robot = new RobotDrive(0, 1, 2, 3);
		stick = new Joystick(0);
		shooter = new CANTalon(0);
		try {
			gyro = new AHRS(SPI.Port.kMXP);
		} catch (RuntimeException rte) {
			DriverStation.reportError("Error instantiating navX-MXP: " + rte.getStackTrace(), true);
		}
		
		turnController = new PIDController(kP, kI, kD, kF, gyro, this);
		turnController.setInputRange(-180.0f, 180.0f);
		turnController.setOutputRange(-1.0, 1.0);
		turnController.setAbsoluteTolerance(kToleranceDegrees);
		turnController.setContinuous(true);

		LiveWindow.addActuator("Drive System", "Rotate Controller", turnController);

		chooser.addDefault("Default Auto", new ExampleCommand());

		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", chooser);
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

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		operatorControl();
		if (stick.getRawButton(8)) {
			shooter.set(-((-stick.getZ() + 1) / 2));
		} else {
			shooter.set(0);
		}
		SmartDashboard.putNumber("Speed: ", shooter.getAnalogInPosition());
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}

	public void operatorControl() {
		robot.setSafetyEnabled(true);
		turnController.setAbsoluteTolerance(10.0);
		boolean rotateToAngle = false;
		if (stick.getRawButton(1)) {
			gyro.reset();
		}
		if (stick.getRawButton(5)) {
			turnController.setSetpoint(0.0f);
			rotateToAngle = true;
		} else if (stick.getRawButton(3)) {
			turnController.setSetpoint(90.0f);
			rotateToAngle = true;
		} else if (stick.getRawButton(4)) {
			turnController.setSetpoint(179.9f);
			rotateToAngle = true;
		} else if (stick.getRawButton(2)) {
			turnController.setSetpoint(-90.0f);
			rotateToAngle = true;
		}
		double currentRotationRate;
		if (rotateToAngle) {
			turnController.enable();
			currentRotationRate = rotateToAngleRate;
		} else {
			turnController.disable();
			currentRotationRate = stick.getX();
		}
		try {
			/* Use the joystick X axis for lateral movement, */
			/* Y axis for forward movement, and the current */
			/* calculated rotation rate (or joystick Z axis), */
			/* depending upon whether "rotate to angle" is active. */
			// robot.mecanumDrive_Cartesian(stick.getX(), stick.getY(),
			// currentRotationRate, gyro.getAngle());
			robot.arcadeDrive(-stick.getY(), -currentRotationRate);
			SmartDashboard.putNumber("Angle: ", gyro.getAngle());
		} catch (RuntimeException ex) {
			DriverStation.reportError("Error communicating with drive system:  " + ex.getMessage(), true);
		}
		Timer.delay(0.005); // wait for a motor update time
	}

	@Override
	public void pidWrite(double output) {
		rotateToAngleRate = output;

	}

}
