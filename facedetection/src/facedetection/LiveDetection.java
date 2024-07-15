package facedetection;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.HighGui;
import org.opencv.videoio.VideoCapture;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class LiveDetection {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        VideoCapture camera = new VideoCapture(0);
        if (!camera.isOpened()) {
            System.out.println("Error: Camera is not available.");
            return;
        }
        String classifierPath = "src/assets/haarcascade_frontalface_default.xml";
        CascadeClassifier faceDetector = new CascadeClassifier(classifierPath);
        Mat frame = new Mat();

        while (true) {
            camera.read(frame);
            if (frame.empty()) {
                System.out.println("No captured frame. Exiting.");
                break;
            }
            MatOfRect faceDetections = new MatOfRect();
            faceDetector.detectMultiScale(frame, faceDetections);
            for (Rect rect : faceDetections.toArray()) {
                Imgproc.rectangle(frame, new org.opencv.core.Point(rect.x, rect.y),
                        new org.opencv.core.Point(rect.x + rect.width, rect.y + rect.height),
                        new Scalar(0, 255, 0));
            }
            HighGui.imshow("Live Face Detection", frame);
            if (HighGui.waitKey(2) == 'q') {
                break;
            }
        }
        camera.release();
        HighGui.destroyAllWindows();
    }
}

