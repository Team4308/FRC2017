//package org.usfirst.frc.team4308.robot.commands;
//
//import org.usfirst.frc.team4308.robot.Robot;
//
//import edu.wpi.first.wpilibj.command.InstantCommand;
//
//public class SlowMode extends InstantCommand {
//
//	private static boolean slow = false;
//
//	public SlowMode() {
//		super();
//		requires(Robot.drivetrain);
//	}
//
//	@Override
//	protected void execute() {
//		if (slow) {
//			Robot.drivetrain.setLimit(0.5);
//		} else {
//			Robot.drivetrain.setLimit(1.0);
//		}
//		slow = !slow;
//		end();
//	}
//
//}
