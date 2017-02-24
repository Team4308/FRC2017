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
 * IP camera feed controller Requires default credentials for access
 * 
 * @author Michael
 *
 */
public class AxisVision extends Vison {

	// TODO: test axis vision

	public AxisVision() {
		super();
	}

	@Override
	public boolean initialize() {
		try {
			visionThread = new Thread(new USBThread(camera, cvsink, outputStream));
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

class USBThread implements Runnable {

	CvSource outputStream;
	VideoCamera camera;
	CvSink cvsink;

	public USBThread(VideoCamera camera, CvSink cvsink, CvSource outputStream) {
		this.outputStream = outputStream;
		this.camera = camera;
		this.cvsink = cvsink;
	}

	@Override
	public void run() {
		camera = CameraServer.getInstance().addAxisCamera(RobotMap.Camera.axisName);
		camera.setResolution(RobotMap.Camera.videoWidth, RobotMap.Camera.videoHeight);

		cvsink = CameraServer.getInstance().getVideo();
		outputStream = CameraServer.getInstance().putVideo(RobotMap.Camera.usbName, RobotMap.Camera.videoWidth,
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
