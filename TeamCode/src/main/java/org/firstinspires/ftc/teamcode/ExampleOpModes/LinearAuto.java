package org.firstinspires.ftc.teamcode.ExampleOpModes;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
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
    Servo blue;
    Servo yellow;
    Servo claw;

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


        yellow = hardwareMap.get(Servo.class, "yellow");
        blue = hardwareMap.get(Servo.class, "blue");
        claw = hardwareMap.get(Servo.class, "claw");

        rightSlides = hardwareMap.get(DcMotor.class, "rightSlides");
        rightSlides.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightSlides.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightSlides.setTargetPosition(0);
        rightSlides.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightSlides.setPower(1);


        leftSlides = hardwareMap.get(DcMotor.class, "leftSlides");
        leftSlides.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftSlides.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftSlides.setTargetPosition(0);
        leftSlides.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftSlides.setPower(1);

        telemetry.addData("heading", drivetrain.gyro.getHeading());
        telemetry.update();




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

        telemetry.addData("heading", drivetrain.gyro.getHeading());
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        resetRuntime();
        runtime.reset();





        if (opModeIsActive()) {

            //TODO CODE

//            blue.setPosition(0.5);
//            yellow.setPosition(0.5);
//            claw.setPosition(0.5);
//            drivetrain.autoDrive(1000,1, 0,0.25, 0);
//            drivetrain.turn(90,0.5);
//            leftSlides.setTargetPosition(1000);
//            rightSlides.setTargetPosition(1000);
//            blue.setPosition(1);
//            yellow.setPosition(0);
//            drivetrain.autoDrive(900,1,0,0.25,0);
//            drivetrain.turn(-55,0.5);
//            drivetrain.autoDrive(500,-1,0,0.25,0);
//            claw.setPosition(1);
//            wait(2);
//            blue.setPosition(0.1);
//            yellow.setPosition(0.9);
//            drivetrain.autoDrive(300,1,0,0.25,0);
//            wait(2);
//            drivetrain.autoDrive(400,-1,0,0.25,0);

                claw.setPosition(0.4);
                blue.setPosition(1);
                yellow.setPosition(0);
                //drivetrain.autoDrive(1300,1, 0,0.25, 0);
                //drivetrain.turn(90,0.5);
                leftSlides.setTargetPosition(1000);
                rightSlides.setTargetPosition(1000);
                //blue.setPosition(1);
                //yellow.setPosition(0);
                //drivetrain.autoDrive(900,1,0,0.25,0);
                //drivetrain.turn(-60,0.5);
                //drivetrain.autoDrive(500,-1,0,0.25,0);
                //claw.setPosition(1);
                //wait(2);
                //blue.setPosition(0.1);
                //yellow.setPosition(0.9);
                //drivetrain.autoDrive(300,1,0,0.25,0);
                //wait(2);
                //drivetrain.autoDrive(600,-1,0,0.25,0);
            while (opModeIsActive())



            multTelemetry.addData("position", visionProcessor.getTeampropPosition());
            telemetry.addData("heading", drivetrain.gyro.getHeading());
            telemetry.update();

        }
        }

        public void setSlides(){
            leftSlides.setTargetPosition(1500);
            rightSlides.setTargetPosition(1500);

        }

        public void wait(int seconds){
            ElapsedTime time = new ElapsedTime();
            while(time.seconds() < seconds){

            }
        }

}

