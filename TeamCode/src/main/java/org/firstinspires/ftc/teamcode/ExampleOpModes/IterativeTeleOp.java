package org.firstinspires.ftc.teamcode.ExampleOpModes;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.TeleOP.Drivetrain;
import org.firstinspires.ftc.teamcode.Utilities.MathUtils;
import org.opencv.core.Point;


//@Disabled
@TeleOp(name="Iterative TeleOp", group="Iterative Opmode")
public class IterativeTeleOp extends OpMode {

    // Declare OpMode members.
    private final ElapsedTime runtime = new ElapsedTime();
    public Drivetrain drivetrain;
    Servo casie;
    Servo jamie;
    Servo servo;
    DcMotor motor1;


    /*
    Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        setOpMode(this);

        drivetrain = new Drivetrain();
        casie = hardwareMap.get(Servo.class, "casie");
        servo = hardwareMap.get(Servo.class,"servo");
        jamie = hardwareMap.get(Servo.class, "jamie");

        multTelemetry.addLine("Launching Espire Console 0.032");
        multTelemetry.addData("Status", "Initialized");
        multTelemetry.addLine(":-)");

        multTelemetry.update();
    }


    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {

        /*
                    Y O U R   C O D E   H E R E
                                                    */

        multTelemetry.addData("Status", "InitLoop");
        multTelemetry.update();
    }


    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();


        /*
                    Y O U R   C O D E   H E R E
                                                   */

        multTelemetry.addData("Status", "Started");
        multTelemetry.update();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */

    @Override
    public void loop() {
        drivetrain.gyro.update();


        double speed = 1;
        Point driveStrafePoint = new Point(gamepad1.left_stick_x, gamepad1.left_stick_y);
        driveStrafePoint = MathUtils.shift(driveStrafePoint, Math.toDegrees(drivetrain.gyro.getHeading()));
        double drive = driveStrafePoint.y;
        double strafe = driveStrafePoint.x;
        double turn = -gamepad1.right_stick_x;

        if (gamepad1.left_trigger > .5) {
            speed = .5;
        }
        //This changes the speed
        drivetrain.drive(drive, strafe, turn, speed);

int jamiePos = 0;

        if (gamepad1.a){
            casie.setPosition(1);
        } else {
            casie.setPosition(0);
        }

        if (gamepad1.b){
            jamiePos = 1;
            jamie.setPosition(jamiePos);
        }
        if (jamiePos == 1 && gamepad1.b){
            jamiePos = -1;
            jamie.setPosition(jamiePos);
        }

        if (gamepad1.left_bumper){
            servo.setPosition(1);
        }

        if (gamepad1.right_bumper){
            servo.setPosition(-1);
        }

        multTelemetry.addData("Status", "Loop Active");
        multTelemetry.addData("heading", drivetrain.gyro.getHeading());
        multTelemetry.update();

    }

    /*
     * Code to run ONCE when the driver hits STOP
     */
    @Override
    public void stop() {

        /*
                    Y O U R   C O D E   H E R E
                                                   */
    }


}