package org.usfirst.frc.team4308.robot;

/**
 * A constants-style class that contains all the raw numbers responsible for
 * adapting the rest of the code to hardware specifications, this class allows
 * changes in hardware configuration to be smoothly implemented into code, as it
 * centralizes all values that would not change between code revisions, but
 * could between hardware revisions.
 */
public class RobotMap {
	
	public static final double kProportional = 0.1;
	public static final double kIntegral = 0.001;
	public static final double kDifferential = 0.0;
	public static final double kFeedForward = 0.0;
	
	public static final double encoderPulseDistance = 0.042;

	public static class Game {
		public static final int maxTimeSeconds = 75;
	}

	public static class GearArm {
		public static final int solenoidA = 0;
		public static final int solenoidB = 1;
		public static final int armChannel = 6;

		public static final double tolerancePercent = 10.0;
		public static final int potentiometerChannel = 0;
		public static final double potentiometerRange = 360.0;

		public static final double restingAngle = 45.0;

		public static final double feedForward = 0.3;

		public static final int sensorChannel = 0;
	}

	public static class Autonomous {
		public static final double maxLinearSpeed = 0.5;
		public static final double maxRotateSpeed = 1.0;
		public static final double defaultDistance = 10.0;
		public static final double defaultOrientation = 0.0;
		public static final double distancePercentTolerance = 10.0;
		public static final double defaultTimeout = 5.0;
		public static final double angularToleranceDegrees = 2.0;
	}

	public static class Constant {
		public static final double proportional = 0.1;
		public static final double integral = 0.001;
		public static final double differential = 0.0;
		public static final double feedForward = 0.0;
	}

	public static class Control {
		public static final int driveStick = 0;

		public static class Standard {
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
			public static final int leftAnalogClick = 9;
			public static final int rightAnalogClick = 10;
		}

		public static class Flight {
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
	public static class Drive {
		public static final int frontLeft = 3;
		public static final int backLeft = 1;
		public static final int frontRight = 0;
		public static final int backRight = 2;

		public static final int ChannelA = 0;
		public static final int ChannelB = 0;

		public static final double curveSensitivity = 0.5;
		public static final double encoderPulseDistance = 0.042;

		public static class Slow {
			public static final double normal = 1.0;
			public static final double slow = 0.42;
		}
	}

	public static class Camera {
		public static final int videoWidth = 640;
		public static final int videoHeight = 480;

		public static final String usbName = "cam0";
		public static final String axisName = "axis-camera.local";
	}

	public static class Climb {
		public static final int masterChannel = 4;
		public static final int slaveChannel = 5;

		public static final double maxForward = 1.0;
		public static final double maxBackward = -1.0;
		public static final double restingSpeed = 0.0;
	}

	public static class Power {
		public static final int primaryAmpLimit = 40;
		public static final int secondaryAmpLimit = 30;
		public static final int breakerAmpLimit = 120;
		public static final int pneumaticsAmpLimit = 17;

		public enum BatteryLevel {
			NOMINAL(12, 0.5), LOW(10.5, 1.0), DISCHARGED(8.0, 1.5);

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
		public static final double cautionThreshold = 0.9;
		public static final double warningThreshold = 0.8;
	}

}
