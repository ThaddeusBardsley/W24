package org.firstinspires.ftc.teamcode.TeleOP;

import static org.firstinspires.ftc.teamcode.Utilities.DashPos.servolocation;
import static org.firstinspires.ftc.teamcode.Utilities.DashPos.servo1pos;
import static org.firstinspires.ftc.teamcode.Utilities.DashPos.servo2pos;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;



@Disabled
@TeleOp(name="TeleOp servo", group="Iterative Opmode")
public class SERVO extends OpMode {
    Servo servo;
    Servo jamie;
    Servo casie;




    /*
    Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        setOpMode(this);
        servo = hardwareMap.get(Servo.class,"servo");
        jamie = hardwareMap.get(Servo.class, "jamie");
        casie = hardwareMap.get(Servo.class, "casie");

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
        //This changes the speed);
        servo.setPosition(servolocation);
        jamie.setPosition(servo2pos);
        casie.setPosition(servo1pos);

        multTelemetry.addData("Status", "Loop Active");
        multTelemetry.update();

    }


    /*
     * Code to run ONCE when the driver hits STOP
     */
    @Override
    public void stop(){

        /*
                    Y O U R   C O D E   H E R E
                                                   */

    }
}
