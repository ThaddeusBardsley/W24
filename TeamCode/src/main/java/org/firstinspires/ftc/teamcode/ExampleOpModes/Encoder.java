package org.firstinspires.ftc.teamcode.ExampleOpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name = "autopractice")
public class Encoder extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        //ticks per revolution : 384.5
        DcMotorEx rightslides = hardwareMap.get(DcMotorEx.class, "rightslides");
        rightslides.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        int degrees = 100;

        waitForStart();

        while(opModeIsActive()) {
            rightslides.setTargetPosition(degrees);
            rightslides.setTargetPositionTolerance(3);
            rightslides.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightslides.setPower(1);
        }
    }
}
