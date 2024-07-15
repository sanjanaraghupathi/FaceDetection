package facedetection;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class FaceDetection {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        String filePath = "src/assets/person.png";
        Mat image = Imgcodecs.imread(filePath);
        String classifierPath = "src/assets/haarcascade_frontalface_default.xml";
        CascadeClassifier faceDetector = new CascadeClassifier(classifierPath);
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image, faceDetections);
        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(image, new org.opencv.core.Point(rect.x, rect.y),
                    new org.opencv.core.Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(0, 255, 0));
        }
        String outputFilePath = "src/assets/detected_faces.jpg";
        Imgcodecs.imwrite(outputFilePath, image);
        System.out.println("Face detection completed.");
    }
}
