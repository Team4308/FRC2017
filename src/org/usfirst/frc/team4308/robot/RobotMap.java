package org.usfirst.frc.team4308.robot;

import org.usfirst.frc.team4308.robot.commands.ArcadeControl;
import org.usfirst.frc.team4308.robot.commands.Control;

import edu.wpi.first.wpilibj.Joystick;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;

	public static class AUTONOMOUS {
		public static final double maxLinearSpeed = 0.5;
		public static final double maxRotateSpeed = 1.0;
		public static final double defaultDistance = 10.0;
	}

	public static class CONTROL {
		public static final int driveStick = 0;
		public static final int tankDriveLeftAxis = 2;
		public static final int tankDriveRightAxis = 5;
	}

	// TODO: encoder channel correction
	// TODO: talonSRX device ID reassignment surgery
	public static class DRIVE {
		public static final int frontLeft = 3;
		public static final int backLeft = 1;
		public static final int frontRight = 0;
		public static final int backRight = 2;
		
		public static final int leftChannelA = 0;
		public static final int leftChannelB = 1;
		public static final int rightChannelA = 2;
		public static final int rightChannelB = 3;
		
		public static final Control controlScheme = new ArcadeControl();
	}

	public static class CAMERA {
		public static final int videoWidth = 640;
		public static final int videoHeight = 480;

		public static final String usbName = "cam0";
		public static final String axisName = "axis-camera.local";
	}
	
	public static class CLIMBER {
		public static final int masterChannel = 4;
		public static final int slaveChannel = 5;
	}
	
	public static final int pressureSensorChannel = 0;
}
