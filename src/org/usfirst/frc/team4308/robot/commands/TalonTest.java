package org.usfirst.frc.team4308.robot.commands;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class TalonTest extends Command {

	CANTalon current;
	double time = -1;
	boolean finished = false;

	public TalonTest(int motor) {
		super();
		// TODO Auto-generated constructor stub
		current = new CANTalon(motor);

	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		super.execute();
		if (time == -1) {
			time = Timer.getFPGATimestamp();
		}
		if (Timer.getFPGATimestamp() - time < 1){
			current.set(0.65);
			
		}
		else if (Timer.getFPGATimestamp()- time < 2){
			current.set(0);
		}
		else if (Timer.getFPGATimestamp()- time < 3){
			current.set(-0.65);
		}
		else{
			current.set(0);
			finished = true;
		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return finished;

	}

}
