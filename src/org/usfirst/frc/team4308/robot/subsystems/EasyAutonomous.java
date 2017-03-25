package org.usfirst.frc.team4308.robot.subsystems;

import org.usfirst.frc.team4308.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class EasyAutonomous extends Subsystem {

	public EasyAutonomous() {
		// TODO Auto-generated constructor stub
	}

	public EasyAutonomous(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}
	
	public void go(){
		Robot.drive.setLeftRightMotorOutputs(0.9, 0.9);
		Timer.delay(2);
		Robot.drive.setLeftRightMotorOutputs(0, 0);
	}
	
}
