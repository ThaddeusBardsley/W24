package org.firstinspires.ftc.teamcode.ExampleOpModes;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.TeleOP.Drivetrain;


@Autonomous(name="Basic: Linear OpMode", group="Linear OpMode")
public class LinearAuto extends LinearOpMode {

    Drivetrain drivetrain;
    DcMotor rightslides;
    DcMotor leftslides;

    int degrees = 3000;

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode(){

        setOpMode(this);

        drivetrain = new Drivetrain();

        rightslides = hardwareMap.get(DcMotor.class, "rightslides");
        rightslides.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftslides = hardwareMap.get(DcMotor.class, "leftslides");
        leftslides.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        resetRuntime();
        runtime.reset();





        if (opModeIsActive()) {


            //TODO CODE THAT RUNS AFTER START
            //For example:
            drivetrain.autoDrive(50,1, 0,1, 0);

                telemetry.addData("Auto Status", "Online");
                telemetry.update();

            rightslides.setTargetPosition(degrees);
            rightslides.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightslides.setPower(1);

            rightslides.setTargetPosition(degrees);
            rightslides.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightslides.setPower(1);
            }
        }

}

