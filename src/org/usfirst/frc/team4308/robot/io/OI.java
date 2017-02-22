package org.usfirst.frc.team4308.robot.io;

import java.util.ArrayList;

import org.usfirst.frc.team4308.robot.RobotMap;
import org.usfirst.frc.team4308.robot.commands.DriveAngular;
import org.usfirst.frc.team4308.robot.commands.DriveLinear;
import org.usfirst.frc.team4308.robot.commands.SlowMode;
import org.usfirst.frc.team4308.util.DualButton;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
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
	// private final JoystickButton[] buttons;
	private final DualButton climbButtons;

	private final int armAxis;

	// TODO: implement proper joystick recognition and axis/button assignment
	// TODO: check if button ranges are zero indexed
	public OI() {
		joystick = new Joystick(RobotMap.CONTROL.driveStick);
		type = JoystickType.fromJoystick(joystick);

		switch (type) {
		case FLIGHT: // 2 DoF joystick
			new JoystickButton(joystick, RobotMap.CONTROL.FLIGHT.eastB).whenPressed(new SlowMode());
			new JoystickButton(joystick, RobotMap.CONTROL.FLIGHT.eastA).whenPressed(null);
			climbButtons = new DualButton(joystick, 8, 9);
			armAxis = RobotMap.CONTROL.FLIGHT.throttle;
			break;
		case STANDARD: // 2 stick PlayStation style controller
			new JoystickButton(joystick, RobotMap.CONTROL.STANDARD.a).whenPressed(new DriveAngular(-180.0));
			new JoystickButton(joystick, RobotMap.CONTROL.STANDARD.b).whenPressed(new DriveAngular(180.0));
			new JoystickButton(joystick, RobotMap.CONTROL.STANDARD.y).whenPressed(new DriveAngular(-90.0));
			new JoystickButton(joystick, RobotMap.CONTROL.STANDARD.x).whenPressed(new DriveAngular(90.0));
			new JoystickButton(joystick, RobotMap.CONTROL.STANDARD.start).whenPressed(new SlowMode());
			climbButtons = new DualButton(joystick, RobotMap.CONTROL.STANDARD.leftBumper,
					RobotMap.CONTROL.STANDARD.rightBumper);
			armAxis = RobotMap.CONTROL.STANDARD.leftTrigger;
			break;
		default:
			DriverStation.reportError("Invalid number of axis on control joystick", true);
			climbButtons = null;
			armAxis = 0;
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

	public DualButton getClimbButtons() {
		return climbButtons;
	}

	public double getArmAxis() {
		return joystick.getRawAxis(armAxis);
	}
}
