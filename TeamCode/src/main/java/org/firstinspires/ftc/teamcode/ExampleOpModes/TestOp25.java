package org.firstinspires.ftc.teamcode.ExampleOpModes;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.TeleOP.drive25;

@Disabled
@TeleOp(name="TestOp25", group="TestOp25")
public abstract class TestOp25 extends OpMode {

    public drive25 drivetrain;

    @Override
    public void init () {
        setOpMode(this);
        drivetrain = new drive25();
    }

    @Override
    public void init_loop() {

    }

    @Override
    public void start() {

    }

    @Override
    public void loop() {

        if (gamepad1.left_stick_y > 0.05) {
            drivetrain.drive(0.25);
        }
    }

    @Override
    public void stop() {

    }
}
