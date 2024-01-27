package org.firstinspires.ftc.teamcode.TeleOP;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.ExampleOpModes.IterativeTeleOp;
import org.firstinspires.ftc.teamcode.Utilities.Vision.VisionProcessing;
import org.firstinspires.ftc.vision.VisionPortal;

@TeleOp(name="vision", group="Iterative Opmode")
public class vision extends OpMode {
private VisionProcessing visionProcessor = new VisionProcessing();
private VisionPortal visionPortal;
private WebcamName webcam1;

    @Override
    public void init() {
telemetry.update();
webcam1 = hardwareMap.get(WebcamName.class, "Webcam 1");
visionPortal = new VisionPortal.Builder()
        .setCamera(webcam1)
        .addProcessor(visionProcessor)
        .build();
    }

    @Override
    public void loop() {

    }
}
