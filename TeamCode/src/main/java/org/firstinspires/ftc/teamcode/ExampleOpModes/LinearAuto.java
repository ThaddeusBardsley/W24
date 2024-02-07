package org.firstinspires.ftc.teamcode.ExampleOpModes;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.TeleOP.Drivetrain;
import org.firstinspires.ftc.teamcode.Utilities.Vision.VisionPipelineBlue;
import org.firstinspires.ftc.teamcode.Utilities.Vision.VisionPipelineRed;
import org.firstinspires.ftc.teamcode.Utilities.Vision.VisionProcessing;
import org.firstinspires.ftc.vision.VisionPortal;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;


@Autonomous(name="Basic: Linear OpMode", group="Linear OpMode")
public class LinearAuto extends LinearOpMode {

    Drivetrain drivetrain;
    DcMotor rightSlides;
    DcMotor leftSlides;

    private VisionProcessing visionProcessor = new VisionProcessing();
    private VisionPortal visionPortal;
    private WebcamName webcam1;

    //To change between colors, comment one and comment the other.
    private OpenCvCamera camera;
    VisionPipelineBlue pipeline = new VisionPipelineBlue();
    //VisionPipelineBlue pipeline = new VisionPipelineRed();


    int degrees = 3000;

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode(){

        setOpMode(this);

        drivetrain = new Drivetrain();

        rightSlides = hardwareMap.get(DcMotor.class, "rightslides");
        rightSlides.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightSlides.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightSlides.setTargetPosition(0);
        rightSlides.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightSlides.setPower(1);


        leftSlides = hardwareMap.get(DcMotor.class, "leftslides");
        leftSlides.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftSlides.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftSlides.setTargetPosition(0);
        leftSlides.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftSlides.setPower(1);




        int cameraMoniterViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMoniterViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMoniterViewId);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {


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


        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        resetRuntime();
        runtime.reset();





        if (opModeIsActive()) {

            //TODO CODE


            setSlides(200);
            multTelemetry.addData("position", visionProcessor.getTeampropPosition());
            multTelemetry.update();
            drivetrain.autoDrive(1000,1, 0,0.25, 0);
            drivetrain.turn(-270,0.5);
            drivetrain.autoDrive(1000,1,0,0.25,0);
            drivetrain.turn(-75,0.5);
            drivetrain.autoDrive(100,-1,0,0.25,0);

        }
        }

        public void setSlides(int pos){
            leftSlides.setTargetPosition(pos);
            rightSlides.setTargetPosition(pos);

        }

        public void wait(int seconds){
            ElapsedTime time = new ElapsedTime();
            while(time.seconds() < seconds){

            }
        }

}

