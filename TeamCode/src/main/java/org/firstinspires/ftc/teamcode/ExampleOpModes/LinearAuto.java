package org.firstinspires.ftc.teamcode.ExampleOpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name="Basic: Linear OpMode", group="Linear OpMode")
public class LinearAuto extends LinearOpMode {
    DcMotor fl;
    DcMotor fr;
    DcMotor bl;
    DcMotor br;

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();


        //CODE THAT RUNS AFTER INIT
        fl = hardwareMap.get(DcMotor.class,"fl");
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        fr = hardwareMap.get(DcMotor.class,"fr");
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        br = hardwareMap.get(DcMotor.class,"br");
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        bl = hardwareMap.get(DcMotor.class,"bl");
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();
        resetRuntime();
        runtime.reset();




        if (opModeIsActive()) {


            //TODO CODE THAT RUNS AFTER START
            //For example:

            drive(100,1,0,0,1);



                telemetry.update();
            }
        }





    public void drive(double distance, double drive, double strafe, double turn, double speed){
        double distanceDriven = 0;

        
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fl.setDirection(DcMotorSimple.Direction.REVERSE);
        bl.setDirection(DcMotorSimple.Direction.REVERSE);

        while (Math.abs(distanceDriven) <= distance) {

            distanceDriven = (Math.abs(fl.getCurrentPosition()) + Math.abs(fr.getCurrentPosition()) + Math.abs(br.getCurrentPosition()) + Math.abs(bl.getCurrentPosition())) /4;

            fl.setPower((drive - strafe + turn) * speed);
            fr.setPower((drive + strafe - turn) * speed);
            bl.setPower((drive + strafe + turn) * speed);
            br.setPower((drive - strafe - turn) * speed);

        }

    }

}

