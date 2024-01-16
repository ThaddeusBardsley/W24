package org.firstinspires.ftc.teamcode.Utilities;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;

import com.qualcomm.hardware.bosch.BHI260IMU;
import com.qualcomm.hardware.bosch.BNO055IMU;

import org.firstinspires.ftc.ftccommon.internal.manualcontrol.parameters.ImuParameters;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

import java.util.ArrayList;

public class Gyro {

    private final BHI260IMU controlHubIMU;
    private double wrappedHeading = 0;
    private double rawHeading = 0;
    private double offset = 0;
    double previousTime = 0;
    double previousChange = 0;


    public Gyro(String name) {

        controlHubIMU = hardwareMap.get(BHI260IMU.class, name);
        ImuParameters parameters = new ImuParameters(

        );


        controlHubIMU.initialize();

        controlHubIMU.resetYaw();


    }


    public double getHeading() {
        return wrappedHeading;
    }


    public double getRateOfChange() {
        double change = getHeading();
        double deltaTime = (System.currentTimeMillis() - previousTime) / 1000.0;
        double deltaChange = change - previousChange;
        double rateOfChange = deltaChange / deltaTime;
        previousTime = System.currentTimeMillis();
        previousChange = change;
        return Math.abs(rateOfChange);
    }


    public void update() {

        //TODO revHub orientation might matter
        double rawYaw;
        rawYaw = controlHubIMU.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);


        rawHeading = rawYaw;

        wrappedHeading = wrapAngle(rawYaw) - offset;
//        BaseOpMode.addData("offset", offset);

    }

    private double wrapAngle(double angle) {


        while (angle > Math.PI) {
            angle -= 2 * Math.PI;
        }

        while (angle < -Math.PI) {
            angle += 2 * Math.PI;
        }

        return angle;
    }


}