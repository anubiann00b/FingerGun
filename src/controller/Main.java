package controller;

import java.io.IOException;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;
import org.opencv.imgproc.Imgproc;

public class Main {
    
    public static void main(String[] args) throws IOException, InterruptedException {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        
        Mat raw = new Mat();
        VideoCapture c = new VideoCapture(0);
        
        Scalar hsv_min = new Scalar(0,200,64,0);
        Scalar hsv_max = new Scalar(0,255,192,0);
        Scalar hsv_min2 = new Scalar(179,200,64,0);   
        Scalar hsv_max2 = new Scalar(179,255,192,0);
        
        while (!c.isOpened())
            Thread.sleep(5);
        
        c.read(raw);
        
        Mat hsv = new Mat();
        Imgproc.cvtColor(raw,hsv,Imgproc.COLOR_BGR2HSV);
        
        Mat thresh1 = new Mat();
        Mat thresh2 = new Mat();
        Mat thresh = new Mat();
        
        Core.inRange(hsv, hsv_min, hsv_max, thresh1);
        Core.inRange(hsv, hsv_min, hsv_max, thresh1);
        Core.inRange(hsv, hsv_min2, hsv_max2, thresh2);
        Core.bitwise_or(thresh1, thresh2, thresh);
        
        Highgui.imwrite("1.png", raw);
        Highgui.imwrite("2.png", hsv);
        Highgui.imwrite("3.png", thresh1);
        Highgui.imwrite("4.png", thresh2);
        Highgui.imwrite("5.png", thresh);
        
        c.release();
    }
}
