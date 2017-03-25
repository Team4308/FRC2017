package org.usfirst.frc.team4308;

public class AbsoluteDashboard {

	private static TableWorker gyro;
	private static TableWorker auto;

	public static TableWorker Gyroscope() {
		if (gyro == null) {
			return gyro = new TableWorker("Gyroscope");
		} else {
			return gyro;
		}
	}

	public static TableWorker Autonomous() {
		if (auto == null) {
			return auto = new TableWorker("Autonomous");
		} else {
			return auto;
		}
	}
}
