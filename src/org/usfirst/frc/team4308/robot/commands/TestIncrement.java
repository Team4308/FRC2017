package org.usfirst.frc.team4308.robot.commands;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

public class TestIncrement extends Command {

	public static final int MAX_ID = 6;
	public static int id = 0;

	private boolean first = true;
	private boolean stopLast;

	private boolean flip = false;
	
	public TestIncrement(boolean stopLast) {
		super();
		this.stopLast = stopLast;
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		super.execute();

		if (first) {
			first = false;
		} else {

			flip = !flip;
			
			if (flip){
				new CANTalon(1).set(0.65);
				new CANTalon(2).set(0.65);
				new CANTalon(3).set(0.65);
				new CANTalon(0).set(0);
				new CANTalon(4).set(0);
				new CANTalon(6).set(0);
			}
			else{
				new CANTalon(1).set(0);
				new CANTalon(2).set(0);
				new CANTalon(3).set(0);
				new CANTalon(0).set(0.65);
				new CANTalon(4).set(0.65);
				new CANTalon(6).set(0.65);
			}
//			if (stopLast) {
//				new CANTalon(id).set(0);
//			}
//
//			id += 1;
//			if (id > MAX_ID) {
//				id = 0;
//			}
		}

//		DriverStation.reportWarning("Testing motor: " + id, false);

//		new CANTalon(id).set(0.65);
	}

	@Override
	protected boolean isFinished() {
		return true;

	}

}
