package org.usfirst.frc.team4308.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	public static class GAME {
		public static final int maxTimeSeconds = 75;
	}

	public static class ARM {
		public static final int pistonChannel = 1;
		public static final int armChannel = 6;
		public static final double tolerancePercent = 10.0;
		public static final int potentiometerChannel = 0;
		public static final double potentiometerRange = 360.0;
	}

	public static class AUTONOMOUS {
		public static final double maxLinearSpeed = 0.5;
		public static final double maxRotateSpeed = 1.0;
		public static final double defaultDistance = 10.0;
	}

	public static class CONSTANT {
		public static final double proportional = 0.1;
		public static final double integral = 0.001;
		public static final double differential = 0.0;
		public static final double feedForward = 0.0;
	}

	public static class CONTROL {
		public static final int driveStick = 0;

		public static class STANDARD {
			public static final int leftX = 0;
			public static final int leftY = 1;
			public static final int leftTrigger = 2;
			public static final int rightTrigger = 3;
			public static final int rightX = 4;
			public static final int rightY = 5;

			public static final int a = 1; // TODO test that these are right
			public static final int b = 2;
			public static final int x = 3;
			public static final int y = 4;
			public static final int leftBumper = 5;
			public static final int rightBumper = 6;
			public static final int back = 7;
			public static final int start = 8;
			public static final int leftClick = 9;
			public static final int rightClick = 10;
		}

		public static class FLIGHT {
			public static final int roll = 0;
			public static final int pitch = 1;
			public static final int throttle = 2;

			public static final int trigger = 1;
			public static final int down = 2;
			public static final int up = 3;
			public static final int left = 4;
			public static final int right = 5;
			public static final int westA = 6;
			public static final int westB = 7;
			public static final int southA = 8;
			public static final int southB = 9;
			public static final int eastA = 10;
			public static final int eastB = 11;
		}

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
		
		public static final double curveSensitivity = 1.0;
		public static final double encoderPulseDistance = 0.042;
		
		public static class SLOW {
			public static final double normal = 1.0;
			public static final double slow = 0.42;
		}
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

	public static class POWER {
		public static final int primaryAmpLimit = 40;
		public static final int secondaryAmpLimit = 30;
		public static final int breakerAmpLimit = 120;

		public enum BatteryLevel {
			NOMINAL(12, 0.5), LOW(10.5, 1.0), DISCHARGED(8.0, 1.0);

			public final double center;
			public final double range;

			BatteryLevel(double center, double range) {
				this.center = center;
				this.range = range;
			}

			public static BatteryLevel level(double level) {
				if (level > NOMINAL.center - NOMINAL.range) {
					return BatteryLevel.NOMINAL;
				} else if (level > LOW.center - LOW.range) {
					return BatteryLevel.LOW;
				} else {
					return BatteryLevel.DISCHARGED;
				}
			}
		}

		public static final double warningTemp = 60.0;
		public static final double dangerTemp = 85.0;
	}

	public static final int pressureSensorChannel = 0;
}
