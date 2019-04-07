package org.firstinspires.ftc.teamcode;

        import android.graphics.Color;
        import android.text.style.BulletSpan;

//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
        import com.qualcomm.robotcore.eventloop.opmode.OpMode;
        import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.Servo;
        import com.qualcomm.robotcore.util.ElapsedTime;
        import com.qualcomm.robotcore.util.Range;
        import com.qualcomm.robotcore.hardware.ColorSensor;

/**
 * This file contains an example of an iterative (Non-Linear) "OpMode".
 * An OpMode is a 'program' that runs in either the autonomous or the teleop period of an FTC match.
 * The names of OpModes appear on the menu of the FTC Driver Station.
 * Roses are red, So is the state, Comrade you are great.
 * When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all iterative OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="TeleOpBaseKiddy", group="Iterative Opmode")
public class TeleOpKiddy extends OpMode


{
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private HardwareTractor bot = null;
    @Override
    public void init() {
        bot = new HardwareTractor();
        bot.init(hardwareMap);
        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        bot.left_drive.setDirection(DcMotor.Direction.FORWARD);
        bot.right_drive.setDirection(DcMotor.Direction.REVERSE);
        bot.lifter.setDirection(DcMotor.Direction.REVERSE);

        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "System overload, initializing Self Destruction Sequence Activated");
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */

    @Override


    public void loop() {
        move();
        // doLifter();
        //doCollector();
        //lock_lifter();
        //  dropMarker();
        /*float hsvValues[] = {0F, 0F, 0F};
        Color.RGBToHSV(sensor.red() *8,sensor.green() *8, sensor.blue() *8, hsvValues);
        final float vlaues [] = hsvValues;
        telemetry.addData("Red", sensor.red());
        telemetry.addData("Green", sensor.green());
        telemetry.addData("Blue", sensor.green());
        telemetry.update();*/
    }

   /* private boolean bumperPressed = false;
    private boolean collectorOn = true;
    private void doCollector() {
        if (bumperPressed == false && gamepad1.left_bumper){
            collectorOn = !collectorOn;
        }
        bumperPressed = gamepad1.left_bumper;

        if (collectorOn) {


            float CollAjustraw = gamepad1.right_stick_y;
            float CollAjustbackraw = -gamepad1.right_stick_y;

            double CollAjustData = Range.clip(CollAjustraw + 0, -1, 1);
            double CollAjustBackData = Range.clip(CollAjustbackraw + 0, -1, 1);
            double CollMot = Range.clip(CollAjustData - CollAjustBackData, -.5, .5);

            if (CollMot == 0) {
                CollMot = -0.2;
            }

            bot.collector.setPower(CollMot);
        }
        else {
            bot.collector.setPower(0);
        }
    }

    private String lifterState = "inactive";
    private ElapsedTime lifterTime = new ElapsedTime();

    private void doLifter() {

        switch (lifterState) {
            case "inactive": doLifterInactive(); break;
            case "lifting": doLifterLifting(); break;
            case "lowering": dolifterlowering(); break;
            case "locking": doLifterLocking(); break;
            case "extending": doLifterExtending(); break;

        }
    }

    private void doLifterExtending(){
        if (lifterTime.seconds() >= 2){
            bot.LifterOff();
            lifterState = "inactive";
        }
    }

    private void doLifterLocking(){
        if (lifterTime.seconds() >=1){
            lifterState = "inactive";
            bot.LifterOff();
        }

    }

    private void doLifterInactive(){

        if (gamepad1.a){
            lifterState = "lifting";
            bot.Lift();
            lifterTime.reset();
        }

        else if (gamepad1.x){
            lifterState = "lowering";
            bot.Lower();
            bot.UnlockLiftrer();
            lifterTime.reset();
        }
        else if (gamepad1.y){
            lifterState = "extending";
            lifterTime.reset();
            bot.ExtendLifter();
        }
    }



    private void doLifterLifting(){
        if (gamepad1.a == false){
            lifterState = "lowering";
            bot.Lower();
            lifterTime.reset();

        }
        else if (lifterTime.seconds() >= 3.0){
            lifterState = "locking";
            bot.LockLifter();
            lifterTime.reset();
        }
    }


    private void lock_lifter() {
        boolean lock = gamepad1.dpad_right;
        boolean unlock = gamepad1.dpad_left;

        if (lock) {
            bot.lifter.setPower(.3);
            bot.LockLifter();
        }
        if (unlock) {
            bot.UnlockLiftrer();
        }
    }

    private void dolifterlowering() {

        if (lifterTime.seconds() >= 3.0){
            lifterState = "inactive";
            bot.LifterOff();
        }
    }


   /* private void dropMarker(){
        boolean markerUp = gamepad2.dpad_up;
        boolean markerDown = gamepad2.dpad_down;

        if(markerUp == true){
            marker_servo.setPosition(100);
        }
        if(markerDown == true){
            marker_servo.setPosition(0);
        }
    }*/

    private void move() {
        double scale = .5; //Sets scale to .5

        double Drive = gamepad1.left_stick_y; //Sets Drive to how far the left joystick is pushed on the Y axis
        double Turn = -gamepad1.left_stick_x; //Sets Turn to how far the left joystick is pushed on the X axis
        double speedMode = gamepad1.right_trigger;

        if(speedMode > .0000000001){
            scale = 1;
        }

        double LeftMotPower = Range.clip(Drive + Turn, -0.25, 0.25) * scale; //Keeps the motor power inside -1 and 1
        double RightMotPower = Range.clip(Drive - Turn, -0.25, 0.25) * scale; //Keeps motor power inside -1 and 1


        bot.left_drive.setPower(LeftMotPower); //Renames variables to work with RC phone's config
        bot.right_drive.setPower(RightMotPower); //Renames variables to work with RC phone's config
    }
    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
