package org.usfirst.frc.team4308.robot.subsystems;

import org.opencv.core.Mat;
import org.usfirst.frc.team4308.robot.RobotMap;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;

/**
 * IP camera feed controller Requires default credentials for access
 * 
 * @author Michael
 *
 */
public class AxisVision extends Vison {

	// TODO: test

	public AxisVision() {
		super();
	}

	@Override
	public boolean initialize() {
		try {
			visionThread = new Thread(() -> {
				camera = CameraServer.getInstance().addAxisCamera(RobotMap.Camera.axisName);
				camera.setResolution(RobotMap.Camera.videoWidth, RobotMap.Camera.videoHeight);

				cvsink = CameraServer.getInstance().getVideo();
				outputStream = CameraServer.getInstance().putVideo(getName(), RobotMap.Camera.videoWidth, RobotMap.Camera.videoHeight);

				Mat source = new Mat();

				while (!Thread.interrupted()) {
					if (cvsink.grabFrame(source) == 0) {
						outputStream.notifyError(cvsink.getError());
						continue;
					}
					outputStream.putFrame(source);
				}
			});
			visionThread.setName(RobotMap.Camera.axisName);
			visionThread.setDaemon(true);

		} catch (Exception e) {
			DriverStation.reportError(e.getMessage(), true);
			return false;
		}
		return true;
	}

	@Override
	public synchronized boolean start() {
		try {
			if (visionThread.isAlive()) {
				visionThread.notify();
			} else {
				visionThread.start();
			}
		} catch (IllegalThreadStateException itse) {
			return false;
		}
		return true;
	}

	@Override
	public synchronized boolean stop() {
		try {
			visionThread.wait();
		} catch (IllegalThreadStateException | InterruptedException e) {
			DriverStation.reportError(e.getMessage(), true);
			return false;
		}
		return true;
	}

	@Override
	protected void initDefaultCommand() {
	}

}
