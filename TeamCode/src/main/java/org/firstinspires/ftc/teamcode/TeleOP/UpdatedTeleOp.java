
package org.firstinspires.ftc.teamcode.TeleOP;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="UpdatedTeleOp", group="Iterative Opmode")

public class UpdatedTeleOp extends OpMode
{
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor leftslides = null;
    private DcMotor rightslides = null;
    private DcMotor karl = null;


    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "init");

            leftslides = hardwareMap.get(DcMotor.class, "leftslides");
             rightslides = hardwareMap.get(DcMotor.class, "rightslides");
             karl  = hardwareMap.get(DcMotor.class, "karl");

    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
        telemetry.addData("Status", "init_loop");
        telemetry.addData("Mapped = ", "True");

    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        telemetry.addData("Status", "start");
        runtime.reset();

    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        telemetry.addData("Status", "loop");
if (gamepad1.left_trigger > .5) {
    rightslides.setPower(1);
    leftslides.setPower(1);
    karl.setPower(1);
} else rightslides.setPower(0); leftslides.setPower(0); karl.setPower(0);

if (gamepad1.right_trigger > .5) {
    rightslides.setPower(-1);
    leftslides.setPower(-1);
    karl.setPower(-1);
} else rightslides.setPower(0); leftslides.setPower(0); karl.setPower(0);
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        telemetry.addData("Status", "Flatlined");
    }

}