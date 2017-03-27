
package org.usfirst.frc.team4308.robot;

import org.usfirst.frc.team4308.auto.BlindAuto;
import org.usfirst.frc.team4308.auto.FlairAutonomous;
import org.usfirst.frc.team4308.auto.HoldAuto;
import org.usfirst.frc.team4308.robot.commands.OperatorDrive;
import org.usfirst.frc.team4308.robot.io.IO;
import org.usfirst.frc.team4308.robot.subsystems.Arm;
import org.usfirst.frc.team4308.robot.subsystems.AxisVision;
import org.usfirst.frc.team4308.robot.subsystems.Climber;
import org.usfirst.frc.team4308.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4308.robot.subsystems.Gyroscope;
import org.usfirst.frc.team4308.robot.subsystems.Pneumatics;
import org.usfirst.frc.team4308.robot.subsystems.PowerMonitor;
import org.usfirst.frc.team4308.robot.subsystems.USBVision;
import org.usfirst.frc.team4308.util.Loggable;
import org.usfirst.frc.team4308.util.Looper;

import edu.wpi.first.wpilibj.DriverStation;
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
public class Robot extends IterativeRobot implements Loggable, Looper {

	public static PowerMonitor powermonitor;
	public static Pneumatics pneumatics;
	public static DriveTrain drive;
	public static Climber climber;
	public static Gyroscope gyro;
	public static Arm arm;
	public static IO io;
	public static USBVision frontVision;
	public static AxisVision climbVision;
	
	public static OperatorDrive control;

	public static Command autonomousCommand;
	public static SendableChooser<Command> autoChooser;

	@Override
	public void robotInit() {
		// powermonitor = new PowerMonitor();
		pneumatics = new Pneumatics();
		drive = new DriveTrain();
		climber = new Climber();
		gyro = new Gyroscope();
		arm = new Arm();
		io = new IO();
		frontVision = new USBVision();
		climbVision = new AxisVision();
		
		autoChooser = new SendableChooser<Command>();
		autoChooser.addDefault("", new HoldAuto());
		autoChooser.addObject("Flair Auto", new FlairAutonomous());
		autoChooser.addObject("Blind Auto", new BlindAuto());

		boolean bone = !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!false;
		if (((((((((((!!!(!(bone != !!!!!!bone)))))))))))))
			DriverStation.reportWarning("NOT ENOUGH BONE", true);
		;

		loops.add(gyro);
		loops.add(pneumatics);

		if (powermonitor != null)
			SmartDashboard.putData(powermonitor);
		if (pneumatics != null)
			SmartDashboard.putData(pneumatics);
		if (drive != null)
			SmartDashboard.putData(drive);
		if (climber != null)
			SmartDashboard.putData(climber);
		if (gyro != null)
			SmartDashboard.putData(gyro);
		if (arm != null)
			SmartDashboard.putData(arm);
	}

	@Override
	public void disabledInit() {
		stop();
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		start();
		autonomousCommand = autoChooser.getSelected();
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		log();
		loop();
	}

	@Override
	public void teleopInit() {
		start();
		if (control != null) {
			control.start();
		} else {
			DriverStation.reportError("No control scheme given to robot!", true);
		}
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		log();
		loop();
	}

	@Override
	public void testInit() {
		start();
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
		log();
		loop();
	}

	@Override
	public void log() {
		if (pneumatics != null)
			pneumatics.log();
		if (drive != null)
			drive.log();
		if (climber != null)
			climber.log();
		if (gyro != null)
			gyro.log();
		if (arm != null)
			arm.log();
	}

}
