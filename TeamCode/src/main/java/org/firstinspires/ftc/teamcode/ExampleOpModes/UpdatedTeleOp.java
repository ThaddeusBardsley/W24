
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
    private DcMotor leftslides = null;
    private DcMotor rightslides = null;
    //private DcMotor karl = null;
    //DcMotor karl2;

    int position1 = 0;
    int position2 = 1000;
    int position3 = 2000;
    int position4 = 3000;

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

        leftslides = hardwareMap.get(DcMotor.class, "leftslides");
        leftslides.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftslides.setMode(DcMotor.RunMode.RUN_WITH_ENCODER);

        rightslides = hardwareMap.get(DcMotor.class, "rightslides");
        rightslides.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightslides.setMode(DcMotor.RunMode.RUN_WITH_ENCODER);

        //karl  = hardwareMap.get(DcMotor.class, "karl");
        //karl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        casie = hardwareMap.get(Servo.class, "casie");
        blue = hardwareMap.get(Servo.class,"blue");
        yellow = hardwareMap.get(Servo.class, "yellow");

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
        double speed = 1;
        Point driveStrafePoint = new Point(driver1.get(LEFT,X), driver1.get(LEFT, Y));
        driveStrafePoint = MathUtils.shift(driveStrafePoint, Math.toDegrees(drivetrain.gyro.getHeading()));
        double drive = driveStrafePoint.y;
        double strafe = driveStrafePoint.x;
        double turn = driver1.get(RIGHT, INVERT_X);

        if (driver1.get(LB2, DOWN)) {
            speed = .5;
        }

        if (driver1.get(RB2, DOWN)) {
            speed = .25;
        }


        drivetrain.drive(drive, strafe, turn, speed);

        if (gamepad2.a) {
            casie.setPosition(0.75);
        } else{
            casie.setPosition(0);
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
        double loadingPosition = 0.1;
        double lowScoringPosition = 0.9;
        double highScoringPosition = 0.75;
        if (gamepad2.dpad_down) {
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



//        if (gamepad2.left_trigger > .5 /*&& leftslides.getCurrentPosition() < 0  */) {
//            leftslides.setPower(-1);
//            rightslides.setPower(-1);
//            //karl.setPower(-1);
//        } else if (gamepad2.right_trigger > .5 /*&& leftslides.getCurrentPosition() > -2800  */) {
//            leftslides.setPower(1);
//            rightslides.setPower(1);
//            //karl.setPower(1);
//        } else{
//            rightslides.setPower(0);
//            leftslides.setPower(0);
//        }


        if (gamepad2.right_trigger > .5) {
            leftslides.setTargetPosition(2800);
            leftslides.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightslides.setTargetPosition(2800);
            rightslides.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }

        if (gamepad2.left_trigger > .5 ) {
            leftslides.setTargetPosition(0);
            leftslides.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightslides.setTargetPosition(0);
            rightslides.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }

        telemetry.addData("Yellow Pos", yellow.getPosition());
        telemetry.addData("Blue Pos", blue.getPosition());
       telemetry.addData("slidepos", leftslides.getCurrentPosition());
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