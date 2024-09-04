// hello
package org.firstinspires.ftc.teamcode.ExampleOpModes;


import static org.firstinspires.ftc.teamcode.Utilities.Controls.ButtonControls.ButtonState.DOWN;
import static org.firstinspires.ftc.teamcode.Utilities.Controls.ButtonControls.ButtonState.TAP;
import static org.firstinspires.ftc.teamcode.Utilities.Controls.ButtonControls.Input.A;
import static org.firstinspires.ftc.teamcode.Utilities.Controls.ButtonControls.Input.LB2;
import static org.firstinspires.ftc.teamcode.Utilities.Controls.ButtonControls.Input.RB2;
import static org.firstinspires.ftc.teamcode.Utilities.Controls.JoystickControls.Input.LEFT;
import static org.firstinspires.ftc.teamcode.Utilities.Controls.JoystickControls.Input.RIGHT;
import static org.firstinspires.ftc.teamcode.Utilities.Controls.JoystickControls.Value.INVERT_X;
import static org.firstinspires.ftc.teamcode.Utilities.Controls.JoystickControls.Value.X;
import static org.firstinspires.ftc.teamcode.Utilities.Controls.JoystickControls.Value.Y;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


import org.firstinspires.ftc.teamcode.TeleOP.Drivetrain;
import org.firstinspires.ftc.teamcode.Utilities.Controls.Controller;
import org.firstinspires.ftc.teamcode.Utilities.MathUtils;
import org.opencv.core.Point;

@TeleOp(name="NewDriving", group="Iterative Opmode")
public class NewDriving extends OpMode
{
    // Declare OpMode members.
    private final ElapsedTime runtime = new ElapsedTime();
    private Drivetrain drivetrain = null;
    // public Drivetrain drivetrain;
    private Servo casie = null;
    private Servo yellow = null;
    private Servo blue = null;

    Controller driver1;
    Controller driver2;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        setOpMode(this);
        telemetry.addData("Status", "init");
        drivetrain = new Drivetrain();


        driver1 = new Controller(gamepad1);
        driver2 = new Controller(gamepad2);


    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
        telemetry.addData("Status", "init_loop");
        telemetry.addData("Mapped", "True");

    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        telemetry.addData("Status", "started");
        runtime.reset();

    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        Controller.update();

        telemetry.addData("Status", "loop");

        drivetrain.gyro.update();


        double speed = gamepad1.right_trigger;
        double drive = gamepad1.right_trigger;
        double strafe = gamepad1.left_stick_x;
        double turn = gamepad1.right_stick_x * -1;
        double reverse = gamepad1.left_trigger;

        drivetrain.drive(drive, strafe, turn, speed);
        //drivetrain.reverse(reverse, strafe, turn, speed);
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        telemetry.addData("Status", "Flatlined");
    }

}