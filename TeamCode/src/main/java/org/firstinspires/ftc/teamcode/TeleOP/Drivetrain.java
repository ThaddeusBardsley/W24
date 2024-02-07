package org.firstinspires.ftc.teamcode.TeleOP;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

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

    public void resetMotors(){
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
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


        public void autoDrive(double distance, double YPower, double XPower, double Speed, double HeadingPower){
        resetMotors();
        double distanceDriven = 0;

        while (Math.abs(distanceDriven) < distance) {

            distanceDriven = (Math.abs(fl.getCurrentPosition()) + Math.abs(fr.getCurrentPosition()) + Math.abs(br.getCurrentPosition()) + Math.abs(bl.getCurrentPosition())) / 4.0;


            fl.setPower((YPower - XPower + HeadingPower) * Speed);
            //
            fr.setPower(-(YPower + XPower - HeadingPower) * Speed);
            //
            bl.setPower((YPower + XPower + HeadingPower) * Speed);
            //backwards
            br.setPower(-(YPower - XPower - HeadingPower) * Speed);
//            fl.setPower(0.2);
//            fr.setPower(-0.2);
//            bl.setPower(0.2);
//            br.setPower(-0.2);

            multTelemetry.addData("Distance", distanceDriven);
        }

    }
    public void turn(double degrees, double speed){
        resetMotors();
        gyro.update();
        if(degrees > gyro.getHeading()){
            while(Math.toDegrees(gyro.getHeading()) < degrees){
                gyro.update();
                fl.setPower(-speed);
                bl.setPower(-speed);
                fr.setPower(-speed);
                br.setPower(-speed);
                multTelemetry.addData("heading", gyro.getHeading());
                multTelemetry.update();

            }
        }else{
            while(Math.toDegrees(gyro.getHeading()) > degrees){
                gyro.update();
                fl.setPower(speed);
                bl.setPower(speed);
                fr.setPower(speed);
                br.setPower(speed);
                multTelemetry.addData("heading", gyro.getHeading());
                multTelemetry.update();
            }
        }
    }

    }




