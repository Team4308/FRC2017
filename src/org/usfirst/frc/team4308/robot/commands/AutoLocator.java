package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.RobotMap;
import org.usfirst.frc.team4308.util.Vector2;

import edu.wpi.first.wpilibj.command.PIDCommand;

public class AutoLocator extends PIDCommand {
	
	DriveAngular angle;
	DriveLinear distance;
	Vector2 magnitude;
	Vector2 destination;
	
	public AutoLocator(Vector2 destination) {
		super(RobotMap.kProportional, RobotMap.kIntegral, RobotMap.kDifferential);
		// TODO Auto-generated constructor stub
		magnitude = destination.toPolar();
		this.destination = destination;
	}

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub

	}


	protected boolean isFinished() {
		// TODO Auto-generated method stub
		Vector2 current = Robot.autonomous.fieldLocation();
		Vector2 delta = (Vector2) destination.sub(current);
		double mag = delta.magnitude();
		
		
		return false;
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		super.execute();
		
		angle = new DriveAngular(magnitude.y);
		
		distance = new DriveLinear(magnitude.x);
		while(!distance.isFinished());
	}

	
}
