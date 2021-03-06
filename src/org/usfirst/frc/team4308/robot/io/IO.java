package org.usfirst.frc.team4308.robot.io;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.RobotMap;
import org.usfirst.frc.team4308.robot.commands.ArcadeDrive;
import org.usfirst.frc.team4308.robot.commands.ArmControlDown;
import org.usfirst.frc.team4308.robot.commands.ArmControlUp;
import org.usfirst.frc.team4308.robot.commands.ClawSwitch;
import org.usfirst.frc.team4308.robot.commands.ClimberControl;
import org.usfirst.frc.team4308.robot.commands.PneumaticsToggle;
import org.usfirst.frc.team4308.robot.commands.SamsonDrive;
import org.usfirst.frc.team4308.robot.commands.SlowMode;
import org.usfirst.frc.team4308.robot.commands.SwitchGear;
import org.usfirst.frc.team4308.util.IAvailable;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class IO implements IAvailable {
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

	private Joystick joystick;
	private JoystickType type;

	private boolean isAvailable;

	private int leftAxis;
	private int rightAxis;
	private int turnAxis;
	private int armAxis;

	public IO() {
		joystick = new Joystick(RobotMap.Control.driveStick);
		type = JoystickType.fromJoystick(joystick);

		isAvailable = type != null;

		armAxis = 0;
		leftAxis = 0;
		rightAxis = 0;
		turnAxis = 0;

		// If there's a joystick available
		if (isAvailable) {
			switch (type) {
			case FLIGHT: // 2 DoF joystick
				new JoystickButton(joystick, RobotMap.Control.Flight.eastB).whenPressed(new SlowMode());
				new JoystickButton(joystick, RobotMap.Control.Flight.eastA).whenPressed(new ClimberControl(true));

				armAxis = RobotMap.Control.Flight.throttle;
				leftAxis = RobotMap.Control.Flight.pitch;
				rightAxis = RobotMap.Control.Flight.roll;

				Robot.control = new ArcadeDrive(Robot.io.getLeftAxis(), Robot.io.getRightAxis());
				break;
			case STANDARD: // 2 stick PlayStation style controller
				Robot.control = new SamsonDrive();

				new JoystickButton(joystick, RobotMap.Control.Standard.b).whenPressed(new ClawSwitch());
				new JoystickButton(joystick, RobotMap.Control.Standard.y).whenPressed(new ClimberControl(true));
				new JoystickButton(joystick, RobotMap.Control.Standard.a).toggleWhenPressed(new ArmControlUp());
				new JoystickButton(joystick, RobotMap.Control.Standard.x).toggleWhenPressed(new ArmControlDown());
				new JoystickButton(joystick, RobotMap.Control.Standard.rightBumper).whenPressed(new SwitchGear());
				// new JoystickButton(joystick,
				// RobotMap.Control.Standard.back).whenPressed(new
				// RumbleControl());
				new JoystickButton(joystick, RobotMap.Control.Standard.back).whenPressed(new ClimberControl(false));
				new JoystickButton(joystick, RobotMap.Control.Standard.start).whenPressed(new PneumaticsToggle());

				armAxis = RobotMap.Control.Standard.leftX;
				leftAxis = RobotMap.Control.Standard.leftY;
				rightAxis = RobotMap.Control.Standard.rightY;
				turnAxis = RobotMap.Control.Standard.rightX;
				break;
			case DIRECTINPUT:
				DriverStation.reportError("SWITCH CONTROLLER FRON DIRECT_INPUT MODE TO XINPUT AND REBOOT ROBOT CODE",
						false);
			default:
				Robot.control = null;
				DriverStation.reportError("Cannot assign control scheme to joystick!", false);
				DriverStation.reportError("Invalid number of axes on control joystick", true);
				break;
			}
		} else {
			DriverStation.reportWarning("type is null", true);
		}
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
		return isAvailable ? joystick.getRawAxis(leftAxis) : 0;
	}

	public int getMoveAxis() {
		return leftAxis;
	}

	public double getMoveValue() {
		return isAvailable ? joystick.getRawAxis(leftAxis) : 0;
	}

	public int getRightAxis() {
		return rightAxis;
	}

	public double getRightValue() {
		return isAvailable ? joystick.getRawAxis(rightAxis) : 0;
	}

	public int getRotateAxis() {
		return rightAxis;
	}

	public double getRotateValue() {
		return isAvailable ? joystick.getRawAxis(rightAxis) : 0;
	}

	public int getArmAxis() {
		return armAxis;
	}

	public double getArmValue() {
		return isAvailable ? joystick.getRawAxis(armAxis) : 0;
	}

	public int getTurnAxis() {
		return turnAxis;
	}

	public double getTurnValue() {
		return isAvailable ? joystick.getRawAxis(turnAxis) : 0;
	}

	@Override
	public boolean isAvailable() {
		return isAvailable;
	}

}
