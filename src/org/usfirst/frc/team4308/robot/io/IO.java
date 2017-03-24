package org.usfirst.frc.team4308.robot.io;

import org.usfirst.frc.team4308.robot.RobotMap;
import org.usfirst.frc.team4308.robot.commands.ClawSwitch;
import org.usfirst.frc.team4308.robot.commands.ClimberControl;
import org.usfirst.frc.team4308.robot.commands.DriveAngular;
import org.usfirst.frc.team4308.robot.commands.DriveLinear;
import org.usfirst.frc.team4308.robot.commands.SlowMode;
import org.usfirst.frc.team4308.robot.commands.TalonSequence;
import org.usfirst.frc.team4308.robot.commands.TalonTest;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class IO {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());

	private final Joystick joystick;
	private final JoystickType type;

	private final int leftAxis;
	private final int rightAxis;
	private final int turnAxis;
	private final int armAxis;

	public IO() {
		joystick = new Joystick(RobotMap.Control.driveStick);
		type = JoystickType.fromJoystick(joystick);

		switch (type) {
		case FLIGHT: // 2 DoF joystick
			new JoystickButton(joystick, RobotMap.Control.Flight.eastB).whenPressed(new SlowMode());
			new JoystickButton(joystick, RobotMap.Control.Flight.eastA).toggleWhenActive(new ClimberControl());

			armAxis = RobotMap.Control.Flight.throttle;
			leftAxis = RobotMap.Control.Flight.pitch;
			rightAxis = RobotMap.Control.Flight.roll;
			turnAxis = 0;
			break;
		case STANDARD: // 2 stick PlayStation style controller
			new JoystickButton(joystick, RobotMap.Control.Standard.start).whenPressed(new SlowMode());
			new JoystickButton(joystick, RobotMap.Control.Standard.leftBumper).toggleWhenActive(new ClimberControl());
			new JoystickButton(joystick, RobotMap.Control.Standard.a).whenPressed(new ClawSwitch());
			new JoystickButton(joystick, RobotMap.Control.Standard.b).whenPressed(new TalonSequence());

			armAxis = RobotMap.Control.Standard.leftX;
			leftAxis = RobotMap.Control.Standard.leftY;
			rightAxis = RobotMap.Control.Standard.rightY;
			turnAxis = RobotMap.Control.Standard.rightX;
			break;
		default:
			DriverStation.reportError("Invalid number of axes on control joystick", true);
			armAxis = 0;
			leftAxis = 0;
			rightAxis = 0;
			turnAxis = 0;
			break;
		}

		SmartDashboard.putData("Drive Forward", new DriveLinear());
	}

	public JoystickType getJoystickType() {
		return type;
	}

	public Joystick getJoystick() {
		return joystick;
	}

	public int getLeftAxis() {
		return leftAxis;
	}

	public double getLeftValue() {
		return joystick.getRawAxis(leftAxis);
	}

	public int getMoveAxis() {
		return leftAxis;
	}

	public int getRightAxis() {
		return rightAxis;
	}

	public double getRightValue() {
		return joystick.getRawAxis(rightAxis);
	}

	public int getRotateAxis() {
		return rightAxis;
	}

	public int getArmAxis() {
		return armAxis;
	}

	public double getArmValue() {
		return joystick.getRawAxis(armAxis);
	}

	public int getTurnAxis() {
		return turnAxis;
	}

	public double getTurnValue() {
		return joystick.getRawAxis(turnAxis);
	}

}