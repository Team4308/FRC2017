package org.usfirst.frc.team4308.robot.subsystems;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team4308.robot.RobotMap;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;

/**
 * Onboard USB camera feed controller
 * @author Michael
 *
 */
public class USBVision extends Vison {

	// TODO: test
	
	public USBVision() {
		super();
	}

	public USBVision(String name) {
		super(name);
	}

	@Override
	public boolean initialize() {
		try {
			visionThread = new Thread(() -> {
				camera = CameraServer.getInstance().startAutomaticCapture();
				camera.setResolution(RobotMap.CAMERA.videoWidth, RobotMap.CAMERA.videoHeight);

				cvsink = CameraServer.getInstance().getVideo();
				outputStream = CameraServer.getInstance().putVideo(getName(), RobotMap.CAMERA.videoWidth, RobotMap.CAMERA.videoHeight);

				Mat source = new Mat();
				Mat output = new Mat();

				while (!Thread.interrupted()) {
					if (cvsink.grabFrame(source) == 0) {
						outputStream.notifyError(cvsink.getError());
						continue;
					}
					Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2GRAY);
					outputStream.putFrame(source);
				}
			});
			visionThread.setName(RobotMap.CAMERA.axisName);
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
		// TODO Auto-generated method stub

	}

}
