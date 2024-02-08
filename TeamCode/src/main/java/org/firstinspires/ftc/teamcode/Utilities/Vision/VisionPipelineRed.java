package org.firstinspires.ftc.teamcode.Utilities.Vision;

import static org.opencv.core.Core.inRange;
import static org.opencv.core.CvType.CV_8U;
import static org.opencv.imgproc.Imgproc.CHAIN_APPROX_SIMPLE;
import static org.opencv.imgproc.Imgproc.COLOR_BGR2HSV;
import static org.opencv.imgproc.Imgproc.COLOR_RGB2HSV;
import static org.opencv.imgproc.Imgproc.FONT_HERSHEY_COMPLEX;
import static org.opencv.imgproc.Imgproc.RETR_TREE;
import static org.opencv.imgproc.Imgproc.boundingRect;
import static org.opencv.imgproc.Imgproc.dilate;
import static org.opencv.imgproc.Imgproc.drawContours;
import static org.opencv.imgproc.Imgproc.erode;
import static org.opencv.imgproc.Imgproc.findContours;
import static org.opencv.imgproc.Imgproc.rectangle;

import com.acmerobotics.dashboard.config.Config;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

@Config
public class VisionPipelineRed extends OpenCvPipeline {
    public static int max_H = 255;
    public static int max_S = 70;
    public static int max_V = 255;

    public static Rect largestRect;

    public static int min_H = 155;
    public static int min_S = 35;
    public static int min_V = 120;

    //sets up for erode/dilate to get rid of stray pixels
    public static int erodeConstant = 1;
    public static int dilateConstant = 1;

    public Rect rectangleOutline;

    public static boolean targetDetected = false;


    private static int IMG_HEIGHT = 0;
    private static int IMG_WIDTH = 0;
    //sets up variables to collect image details

    private Mat output = new Mat(),
            modified = new Mat();
    private ArrayList<MatOfPoint> contours = new ArrayList<>();

    private Mat hierarchy = new Mat();
    //stuff for variables


    // colour settings to set up output image, you will see these colors if you choose to output camera stream to dashboard
    private Scalar orange = new Scalar(252, 186, 3);
    private Scalar lightBlue = new Scalar(3, 252, 227);

    //not currently used, will allow to write text
    // and the thickness variable can be used when drawing bounding rectangles or contours
    private int thickness = 10;
    private int font = FONT_HERSHEY_COMPLEX;


    @Override
    public Mat processFrame(Mat input) {
        //color data, using HSV colorspace, H=0-180, S=0-255, V=0-255

        input.copyTo(output);


        IMG_HEIGHT = input.rows();
        IMG_WIDTH = input.cols();
        //just saving info

        //Scalar MIN_THRESH_PROP = new Scalar(0, 35, 38);
        //  Scalar MAX_THRESH_PROP = new Scalar(15, 100, 63);

        Scalar MIN_THRESH_PROP = new Scalar(min_H, min_S, min_V);
        Scalar MAX_THRESH_PROP = new Scalar(max_H, max_S, max_V);
        //setting up all the color thresholds


        // Imgproc.cvtColor(input, modified, COLOR_RGB2HSV);
        Imgproc.cvtColor(input, modified, COLOR_BGR2HSV);

        //goes from RGB to HSV color space

        //to detect red change to BGR2HSV


        inRange(modified, MIN_THRESH_PROP, MAX_THRESH_PROP, modified);


        Rect submatRect = new Rect(new Point(4, 4), new Point(IMG_WIDTH, IMG_HEIGHT));
        modified = modified.submat(submatRect);
        //actual threshold thing to correct for top of screen being wierd and glitchy


//erode and dilate get rid of stray pixels and clean up data, can be made bigger
        erode(modified, modified, new Mat(erodeConstant, erodeConstant, CV_8U));
        //erode constant currently = 1, change to erode more or less
        dilate(modified, modified, new Mat(dilateConstant, dilateConstant, CV_8U));
        //dilate constant currently 1, should have erode/dilate be equal




        contours = new ArrayList<>();

        findContours(modified, contours, hierarchy, RETR_TREE, CHAIN_APPROX_SIMPLE);
        //figures out all the pixels on the edges of the blob, useful for finding center


        List<Rect> rects = new ArrayList<>();
        for (int i = 0; i < contours.size(); i++) {
            Rect rect = boundingRect(contours.get(i));
            rects.add(rect);
        }//creates a bounding rectangle


        if (rects.size() != 0) {
//if anything is detected, find the biggest and use that
            this.largestRect = VisionUtils.sortRectsByMaxOption(1, VisionUtils.RECT_OPTION.AREA, rects).get(0);
//draws rectangle around biggest shape
            rectangle(output, largestRect, orange, 10); //, thickness);

            targetDetected = true;


        } else {
            targetDetected = false;
        }
        //draws contours around shapes
        drawContours(output, contours, -1, lightBlue);




        return output;
    }


    public int getTeampropPosition(){
        int pos = 2;
        if (largestRect != null){
            double center = largestRect.x + (largestRect.width /2);
            if (center < IMG_WIDTH/4){
                pos = 1;
            }
            if (center > IMG_WIDTH/3 *2){
                pos = 3;}
        }

        return pos;
    }
}
