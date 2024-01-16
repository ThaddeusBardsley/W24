package org.firstinspires.ftc.teamcode.TeleOP;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Utilities.Gyro;

public class Drivetrain {
    DcMotor fl;
    DcMotor fr;
    DcMotor bl;
    DcMotor br;
    public Gyro gyro;
    public Drivetrain(){
        fl = hardwareMap.get(DcMotor.class, "fl");
        fr = hardwareMap.get(DcMotor.class, "fr");
        bl = hardwareMap.get(DcMotor.class, "bl");
        br = hardwareMap.get(DcMotor.class, "br");
        gyro = new Gyro("imu");
    }
    public void drive (double YPower, double XPower, double HeadingPower, double Speed){

        //
        fl.setPower(-(YPower - XPower + HeadingPower) * Speed);
        //
        fr.setPower((YPower + XPower - HeadingPower) * Speed);
        //
        bl.setPower(-(YPower + XPower + HeadingPower) * Speed);
        //backwards
        br.setPower((YPower - XPower - HeadingPower) * Speed);



    }
}
