package org.firstinspires.ftc.teamcode.ExampleOpModes;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp(name="EHub Test", group="Iterative Opmode")
public class EHubTest extends OpMode {
    DcMotor motor1;
    DcMotor motor2;
    DcMotor motor3;
    @Override
    public void init() {
        setOpMode(this);

        motor1 = hardwareMap.get(DcMotor.class, "1");
        motor2 = hardwareMap.get(DcMotor.class, "2");
        motor3 = hardwareMap.get(DcMotor.class, "3");

    }

    @Override
    public void loop() {

        if(gamepad1.left_bumper){
            motor1.setPower(.5);
        }else{
            motor1.setPower(0);
        }

        if(gamepad1.right_bumper){
            motor2.setPower(-.5);
        }else{
            motor2.setPower(0);
        }

        if(gamepad1.circle){
            motor3.setPower(.5);
        }else{
            motor3.setPower(0);
        }


        //Results:
        /*

        TODO 1 Motor -> Port 0
        w/o slides - fine       w/ slides - failed at 3.29
        TODO 1 Motor -> Port 1
        w/o slides - fine       w/ slides - failed at 4.5
        TODO 1 Motor -> Port 2
        w/o slides - fine       w/ slides - failed at 4



         */



    }
}
