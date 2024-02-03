package org.firstinspires.ftc.teamcode.TeleOP;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name= "TelemetryTest", group= "IterativeOpmode")
public abstract class TelemetryTest extends OpMode {

    @Override
    public void init(){
        setOpMode(this);
        multTelemetry.addData("Status", "Espire Console 0.032");
        multTelemetry.addLine(":-)");
        multTelemetry.update();
    }

    @Override
    public void init_loop() {
        multTelemetry.addData("Status", "InitLoop");
        multTelemetry.update();
    }

    @Override
    public void start() {
        multTelemetry.addData("Status", "Started");
        multTelemetry.update();
    }

    @Override
    public void loop() {
        multTelemetry.addData("Status", "Loop Active");
        multTelemetry.update();
    }

    @Override
    public void stop(){
        multTelemetry.addLine("program stopped");
        multTelemetry.update();
    }
}
