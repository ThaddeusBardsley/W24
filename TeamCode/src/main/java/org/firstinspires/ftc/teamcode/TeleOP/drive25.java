package org.firstinspires.ftc.teamcode.TeleOP;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotor;

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

    public void drive (double Speed){
        fl.setPower(Speed);
        fr.setPower(Speed);
        bl.setPower(Speed);
        br.setPower(Speed);
    }
}
