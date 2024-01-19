package org.firstinspires.ftc.teamcode.ExampleOpModes;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.DriveSystem.Drive;
import org.firstinspires.ftc.teamcode.TeleOP.Drivetrain;
import org.firstinspires.ftc.teamcode.Utilities.MathUtils;
import org.opencv.core.Point;


//@Disabled
@Autonomous(name="Auto", group="Linear Opmode")
public class LinearAuto extends LinearOpMode {

    public Drivetrain drivetrain;
    drivetrain = new Drivetrain();
    

    @Override
    public void runOpMode() {
        while (opModeIsActive()) {
            drivetrain.drive()


        }
    }
}

