package org.usfirst.frc.team4308.robot.subsystems;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.util.Loop;
import org.usfirst.frc.team4308.util.Vector2;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Autonomous extends Subsystem implements Loop{
	// TODO: maybe use looping to constantly find heading
	// TODO: pre-calculated locations for all team stations
	// TOOD: possibly A star map for field
	// TODO: pre-calculated locations and orientations for gear dropoff/pickup
	private Vector2 fieldLocation;
	private double direction;

	public Autonomous() {
		// TODO: determine field location from alliance and team station
		// TODO: determine robot's heading
	}

	public boolean moveTo(Vector2 destination) {
		// TODO: calculate hypotenuse distance, rotate towards destination, move
		// forwards (or backwards) by hypotenuse distance
		
		return false;
	}

	@Override
	protected void initDefaultCommand() {

	}

	public Vector2 fieldLocation() {
		return fieldLocation;
	}

	public double direction() {
		return direction;
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loop() {
		// TODO Auto-generated method stub
		
	}

}
