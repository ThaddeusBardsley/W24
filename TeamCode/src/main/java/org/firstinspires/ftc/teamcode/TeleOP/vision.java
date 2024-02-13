package org.firstinspires.ftc.teamcode.TeleOP;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Utilities.Vision.VisionPipelineBlue;
import org.firstinspires.ftc.teamcode.Utilities.Vision.VisionPipelineGreen;
import org.firstinspires.ftc.teamcode.Utilities.Vision.VisionPipelineRed;
import org.firstinspires.ftc.teamcode.Utilities.Vision.VisionProcessing;
import org.firstinspires.ftc.vision.VisionPortal;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;


@TeleOp(name="vision", group="Iterative Opmode")
public class vision extends OpMode {
private VisionProcessing visionProcessor = new VisionProcessing();
private VisionPortal visionPortal;
private WebcamName webcam1;

//To change between colors, comment one and comment the other.
private OpenCvCamera camera;
//VisionPipelineBlue pipeline = new VisionPipelineBlue();
VisionPipelineRed pipeline = new VisionPipelineRed();
    //VisionPipelineGreen pipeline = new VisionPipelineGreen();

    @Override
    public void init() {
    telemetry.update();

//for visionProccesor
//webcam1 = hardwareMap.get(WebcamName.class, "Webcam 1");
//visionPortal = new VisionPortal.Builder()
//        .setCamera(webcam1)
//        .addProcessor(visionProcessor)
//        .build();

        //for vision pipeline

        int cameraMoniterViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMoniterViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMoniterViewId);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            // Start streaming
            @Override
            public void onOpened() {
                camera.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);

//this just sets up the camera
                //  camera.resumeViewport();

            }

            @Override
            public void onError(int errorCode) {
//When camera doesn't work nothing happens
            }
        });
        camera.setPipeline(pipeline);
        FtcDashboard.getInstance().startCameraStream(camera, 30);

    }

    @Override
    public void loop() {


        telemetry.addData("position", visionProcessor.getTeampropPosition());
        telemetry.update();
    }
}
