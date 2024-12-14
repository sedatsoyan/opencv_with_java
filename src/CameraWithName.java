import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

import javax.swing.*;
import java.awt.*;

public class CameraWithName {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    private JFrame frame;
    private JLabel imageLabel;
    private CascadeClassifier faceDetector;

    public static void main(String[] args) {
        CameraWithName camera = new CameraWithName();
        camera.initGUI();
        camera.loadCascade();
        camera.runMainLoop();
    }

    private void loadCascade() {
        String cascadePath = "data/haarcascade_frontalface_alt.xml";
        faceDetector = new CascadeClassifier(cascadePath);
    }

    private void initGUI() {
        frame = new JFrame("Face Detection with Name");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        frame.setUndecorated(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        imageLabel = new JLabel();
        imageLabel.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.add(imageLabel);
        frame.setVisible(true);
    }

    private void runMainLoop() {
        imgProcessor imageProcessor = new imgProcessor();
        Mat webcamImage = new Mat();
        Image tempImage;


        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();

        VideoCapture capture = new VideoCapture(0);
        capture.set(Videoio.CAP_PROP_FRAME_WIDTH, screenWidth);
        capture.set(Videoio.CAP_PROP_FRAME_HEIGHT, screenHeight);

        if (capture.isOpened()) {
            while (true) {
                capture.read(webcamImage);
                if (!webcamImage.empty()) {
                    detectAndDrawFaces(webcamImage, "Sedat Soyan","YHAWCH");
                    tempImage = imageProcessor.toBufferImage(webcamImage);

                    ImageIcon imageIcon = new ImageIcon(tempImage.getScaledInstance(screenWidth, screenHeight, Image.SCALE_FAST));
                    imageLabel.setIcon(imageIcon);
                } else {
                    JOptionPane.showMessageDialog(frame, "Kamera görüntüsü alınamadı.", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            System.out.println("Kamera açılamadı.");
        }
    }

    private void detectAndDrawFaces(Mat image, String name,String status) {
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image, faceDetections, 1.1, 7, 0, new Size(50, 50), new Size());

        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0), 3);


            Point nameposition = new Point(rect.x + rect.width +10, rect.y+ rect.height /2);
            Imgproc.putText(image, "name : " + name,nameposition,Imgproc.FONT_HERSHEY_SIMPLEX,0.5,new Scalar(255,0,0),2);
            Point statusposition = new Point(rect.x + rect.width + 10, rect.y + rect.height /2 +20);
            Imgproc.putText(image,"Status:" + status,statusposition,Imgproc.FONT_HERSHEY_SIMPLEX,0.5, new Scalar(255,0,0),2);
        }

    }
}
