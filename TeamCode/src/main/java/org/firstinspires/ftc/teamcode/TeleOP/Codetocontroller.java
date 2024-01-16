package org.firstinspires.ftc.teamcode.TeleOP;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
@TeleOp
public class Codetocontroller extends OpMode {
    public CRServo servo;
    @Override
    public void init(){
        servo = hardwareMap.get(CRServo.class, "servo");
    }

@Override
    public void loop(){
      while(gamepad1.a){
          servo.setPower(1);
      }
      while(gamepad1.b){
          servo.setPower(-1);
      }
      servo.setPower(0);

    }
}


