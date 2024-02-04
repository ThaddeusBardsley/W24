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

    }
}
