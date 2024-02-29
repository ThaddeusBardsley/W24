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

@TeleOp(name="UpdatedTeleOp", group="Iterative Opmode")
public class UpdatedTeleOp extends OpMode
{
    // Declare OpMode members.
    private final ElapsedTime runtime = new ElapsedTime();
    private Drivetrain drivetrain = null;
   // public Drivetrain drivetrain;
    private Servo casie = null;
    private Servo yellow = null;
    private Servo blue = null;
    Servo claw;
    private DcMotor lift = null;
    private DcMotor leftSlides = null;
    private DcMotor rightSlides = null;
    //private DcMotor karl = null;
    //DcMotor karl2;
    int slidesPos = 0;
    int position1 = 0;
    int position2 = 1000;
    int position3 = 2000;
    int position4 = 3000;
    boolean climbed;

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

        rightSlides = hardwareMap.get(DcMotor.class, "rightSlides");
//        rightSlides.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        rightSlides.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        rightSlides.setTargetPosition(0);
//        rightSlides.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        rightSlides.setPower(1);


        leftSlides = hardwareMap.get(DcMotor.class, "leftSlides");
//        leftSlides.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        leftSlides.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        leftSlides.setTargetPosition(0);
//        leftSlides.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        leftSlides.setPower(1);

        //karl  = hardwareMap.get(DcMotor.class, "karl");
        //karl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        lift = hardwareMap.get(DcMotor.class, "lift");

        casie = hardwareMap.get(Servo.class, "casie");
        blue = hardwareMap.get(Servo.class,"blue");
        yellow = hardwareMap.get(Servo.class, "yellow");
        claw = hardwareMap.get(Servo.class, "claw");

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


        //This changes the speed
//        double speed = 1;
//        Point driveStrafePoint = new Point(driver1.get(LEFT,X), driver1.get(LEFT, Y));
//        driveStrafePoint = MathUtils.shift(driveStrafePoint, Math.toDegrees(drivetrain.gyro.getHeading()));
//        double drive = driveStrafePoint.y;
//        double strafe = driveStrafePoint.x;
//        double turn = driver1.get(RIGHT, INVERT_X);


        double speed = 1;
        double drive = gamepad1.left_stick_y;
        double strafe = gamepad1.left_stick_x;
        double turn = gamepad1.right_stick_x * -1;

        if (driver1.get(LB2, DOWN)) {
            speed = .5;
        }

        if (driver1.get(RB2, DOWN)) {
            speed = .25;
        }



        drivetrain.drive(drive, strafe, turn, speed);



        if (gamepad2.x){
            casie.setPosition(0.915);
        } else if (gamepad2.b) {
            casie.setPosition(0.75);
        }


//        //claw loading
//        if (gamepad2.left_bumper) {
//            double blueLoading = 0.075;
//            blue.setPosition(blueLoading);  // -1 blue  offset of 0.075
//            double yellowLoading = 0.9;
//            yellow.setPosition(yellowLoading); //1 yellow 0.975  offset of 0.1
//        }
//
//        // claw scoring
//        if (gamepad2.right_bumper) {
//            double blueScoring= 0.975;
//            blue.setPosition(blueScoring); //1 blue 0.975  offset of 0.025
//            double yellowScoring = 0;
//            yellow.setPosition(yellowScoring); //-1 yellow  offset of zero
//        }


        // this code accounts for the stupid offset that the diffrences in the servos make.
        double armOffset = 0.025;
        double loadingPosition = 0.04;
        double lowScoringPosition = 0.95;
        double highScoringPosition = 0.75;
        if (gamepad2.dpad_down && rightSlides.getCurrentPosition() < 10) {
            blue.setPosition(loadingPosition);
            yellow.setPosition(1 - loadingPosition - armOffset);
        }
        if (gamepad2.dpad_left) {
            blue.setPosition(lowScoringPosition);
            yellow.setPosition(1 - lowScoringPosition - armOffset);
        }
        if (gamepad2.dpad_up) {
            blue.setPosition(highScoringPosition);
            yellow.setPosition(1 - highScoringPosition - armOffset);
        }

        //lift
        if (gamepad1.right_bumper && gamepad1.a) {
            lift.setPower(-1);
        }


        if (gamepad2.right_bumper) {
            leftSlides.setPower(-1);
            rightSlides.setPower(-1);
        } else if (gamepad2.left_bumper && yellow.getPosition() < 0.90) {
            leftSlides.setPower(1);
            rightSlides.setPower(1);

        } else if (gamepad2.y) {
            leftSlides.setPower(-0.5);
            rightSlides.setPower(-0.5);
        } else {
            rightSlides.setPower(0.05);
            leftSlides.setPower(0.05);
            }


        if (gamepad2.left_trigger > .5) {
            claw.setPosition(.95); //open
        } else if (gamepad2.right_trigger > .5) {
            claw.setPosition(.4); //close
        }

        if (blue.getPosition() > loadingPosition - 0.05 && blue.getPosition() < loadingPosition + 0.1){
            leftSlides.setPower(-0.15);
            rightSlides.setPower(-0.15);
        }
//        if (driver2.get(RB2,TAP)) {
//            slidesPos += 250;
//        }
//
//        if (driver2.get(LB2,TAP)) {
//            slidesPos -= 250;
//        }
//        leftSlides.setTargetPosition(slidesPos);
//        rightSlides.setTargetPosition(slidesPos);


//        if (gamepad2.right_trigger > .5) {
//            leftSlides.setTargetPosition(1500); //broken
//            rightSlides.setTargetPosition(1500);
//        } else if (gamepad2.left_trigger > .5) {
//            leftSlides.setTargetPosition(100);
//            rightSlides.setTargetPosition(100);
//        }


        telemetry.addData("leftslide pos", leftSlides.getCurrentPosition());
        telemetry.addData("rightslide pos", rightSlides.getCurrentPosition());
        telemetry.addData("Yellow Pos", yellow.getPosition());
        telemetry.addData("Blue Pos", blue.getPosition());
       telemetry.addData("heading", drivetrain.gyro.getHeading());
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        telemetry.addData("Status", "Flatlined");
    }

}