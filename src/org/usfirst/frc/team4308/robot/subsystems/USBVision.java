package org.usfirst.frc.team4308.robot.subsystems;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team4308.robot.RobotMap;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.VideoCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;

/**
 * Onboard USB camera feed controller
 * 
 * @author Michael
 *
 */
public class USBVision extends Vison {

	// TODO: test usb vision

	public USBVision() {
		super();
	}

	@Override
	public boolean initialize() {
		try {
			visionThread = new Thread(new AxisThread(camera, cvsink, outputStream));
			visionThread.setName(RobotMap.Camera.axisName);
			visionThread.setDaemon(true);
		} catch (Exception e) {
			DriverStation.reportError(e.getMessage(), true);
			return false;
		}
		return true;
	}

	@Override
	protected void initDefaultCommand() {
	}

}

/**
 * Vision Thread for handling video input from a network-attached Axis Camera.
 * 
 * @author Michael Brown
 *
 */
class AxisThread implements Runnable {

	CvSource outputStream;
	VideoCamera camera;
	CvSink cvsink;

	public AxisThread(VideoCamera camera, CvSink cvsink, CvSource outputStream) {
		this.outputStream = outputStream;
		this.camera = camera;
		this.cvsink = cvsink;
	}

	@Override
	public void run() {
		camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(RobotMap.Camera.videoWidth, RobotMap.Camera.videoHeight);

		cvsink = CameraServer.getInstance().getVideo();
		outputStream = CameraServer.getInstance().putVideo(RobotMap.Camera.axisName, RobotMap.Camera.videoWidth,
				RobotMap.Camera.videoHeight);

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
	}

}
