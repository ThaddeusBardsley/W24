package org.firstinspires.ftc.teamcode.TeleOP;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.telemetry;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class drive25 {

    DcMotor fl;
    DcMotor fr;
    DcMotor bl;
    DcMotor br;

    public drive25() {

        fl = hardwareMap.get(DcMotor.class, "fl");
        fr = hardwareMap.get(DcMotor.class, "fr");
        bl = hardwareMap.get(DcMotor.class, "bl");
        br = hardwareMap.get(DcMotor.class, "br");
    }

//    public void drive(double Speed, double Drive, double Turn, double Strafe) {
//
//    }

//    public void drive (double Speed){
//        fl.setPower(Speed);
//        fr.setPower(Speed);
//        bl.setPower(Speed);
//        br.setPower(Speed);
//    }
//
//    public void turn (double Speed){
//        fl.setPower(Speed);
//        fr.setPower(Speed);
//        br.setPower(Speed);
//        bl.setPower(Speed);
//    }

    public void drive (double YPower, double XPower, double HeadingPower, double Speed){

        //
        fl.setPower(-(YPower - XPower + HeadingPower) * Speed);
        //
        fr.setPower((YPower + XPower - HeadingPower) * Speed);
        //
        bl.setPower(-(YPower + XPower + HeadingPower) * Speed);
        //backwards
        br.setPower((YPower - XPower - HeadingPower) * Speed);

        telemetry.addData("Y = ", YPower);
        telemetry.addData("X = ", XPower);
        telemetry.addData("HeadingPower", HeadingPower);

    }
}
