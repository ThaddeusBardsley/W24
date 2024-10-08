package org.firstinspires.ftc.teamcode.ExampleOpModes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;


@Disabled
@TeleOp(name = "autopractice")
public class Encoder extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        //ticks per revolution : 1000
        DcMotorEx rightslides = hardwareMap.get(DcMotorEx.class, "rightslides");
        rightslides.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        int degrees = 3000;

        waitForStart();

        while(opModeIsActive()) {
            rightslides.setTargetPosition(degrees);
            rightslides.setTargetPositionTolerance(3);
            rightslides.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightslides.setPower(1);
        }
    }
}
