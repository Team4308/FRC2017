//package org.usfirst.frc.team4308.robot;
//
//import org.usfirst.frc.team4308.robot.commands.DriveLinear;
//import org.usfirst.frc.team4308.robot.commands.SlowMode;
//import org.usfirst.frc.team4308.robot.commands.TankControl;
//import org.usfirst.frc.team4308.util.DualButton;
//import org.usfirst.frc.team4308.robot.commands.ArcadeControl;
//import org.usfirst.frc.team4308.robot.commands.DriveAngular;
//
//import edu.wpi.first.wpilibj.DriverStation;
//import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.buttons.JoystickButton;
//import edu.wpi.first.wpilibj.command.Command;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//
///**
// * This class is the glue that binds the controls on the physical operator
// * interface to the commands and command groups that allow control of the robot.
// */
//public class OI {
//	//// CREATING BUTTONS
//	// One type of button is a joystick button which is any button on a
//	//// joystick.
//	// You create one by telling it which joystick it's on and which button
//	// number it is.
//	// Joystick stick = new Joystick(port);
//	// Button button = new JoystickButton(stick, buttonNumber);
//
//	// There are a few additional built in buttons you can use. Additionally,
//	// by subclassing Button you can create custom triggers and bind those to
//	// commands the same as any other Button.
//
//	//// TRIGGERING COMMANDS WITH BUTTONS
//	// Once you have a button, it's trivial to bind it to a button in one of
//	// three ways:
//
//	// Start the command when the button is pressed and let it run the command
//	// until it is finished as determined by it's isFinished method.
//	// button.whenPressed(new ExampleCommand());
//
//	// Run the command while the button is being held down and interrupt it once
//	// the button is released.
//	// button.whileHeld(new ExampleCommand());
//
//	// Start the command when the button is released and let it run the command
//	// until it is finished as determined by it's isFinished method.
//	// button.whenReleased(new ExampleCommand());
//
//	private final Joystick joystick;
//	private final JoystickButton[] buttons;
//	private final DualButton climbButtons;
//
//	public final Command controlScheme;
//
//	// TODO: implement proper joystick recognition and axis/button assignment
//	// TODO: check if button ranges are zero indexed
//	public OI() {
//		joystick = new Joystick(RobotMap.CONTROL.driveStick);
//
//		buttons = new JoystickButton[10];
//		for (int i = 0; i < buttons.length; i++) {
//			buttons[i] = new JoystickButton(joystick, i);
//		}
//
//		switch (joystick.getAxisCount()) {
//		case 3: // 2 DoF joystick
//			controlScheme = new ArcadeControl();
//			new JoystickButton(joystick, 2).whenPressed(new DriveAngular(-180.0));
//			new JoystickButton(joystick, 3).whenPressed(new DriveAngular(180.0));
//			new JoystickButton(joystick, 4).whenPressed(new DriveAngular(-90.0));
//			new JoystickButton(joystick, 5).whenPressed(new DriveAngular(90.0));
//			new JoystickButton(joystick, 6).whenPressed(new SlowMode());
//			climbButtons = new DualButton(joystick, 8, 9);
//			break;
//		case 6: // 2 stick PlayStation style controller
//			controlScheme = new TankControl();
//			new JoystickButton(joystick, 1).whenPressed(new DriveAngular(-180.0));
//			new JoystickButton(joystick, 2).whenPressed(new DriveAngular(180.0));
//			new JoystickButton(joystick, 4).whenPressed(new DriveAngular(-90.0));
//			new JoystickButton(joystick, 3).whenPressed(new DriveAngular(90.0));
//			climbButtons = new DualButton(joystick, 5, 6);
//			break;
//		default:
//			DriverStation.reportError("Invalid number of axis on control joystick", true);
//			climbButtons = null;
//			controlScheme = null;
//			break;
//		}
//
//		SmartDashboard.putData("Drive Forward", new DriveLinear());
//	}
//
//	public Joystick getJoystick() {
//		return joystick;
//	}
//
//	public DualButton getClimbButtons() {
//		return climbButtons;
//	}
//}
