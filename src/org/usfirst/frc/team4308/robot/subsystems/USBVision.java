package org.usfirst.frc.team4308.robot.subsystems;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;

public class USBVision extends Vison {

	public USBVision(String host, int width, int height) {
		super(host, width, height);
	}

	public USBVision(String name, String host, int width, int height) {
		super(name, host, width, height);
	}

	@Override
	public boolean initialize() {
		try {
			visionThread = new Thread(() -> {
				camera = CameraServer.getInstance().startAutomaticCapture();
				camera.setResolution(width, height);

				cvsink = CameraServer.getInstance().getVideo();
				outputStream = CameraServer.getInstance().putVideo(getName(), width, height);

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
			visionThread.setName(host);
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
