package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name="AutoDepot", group="Linear Opmode")
//Disabled
public class AutoDepot extends LinearOpMode {

    protected HardwareTractor robot;
    private ElapsedTime runtime = new ElapsedTime();

    static final double     COUNTS_PER_MOTOR_REV    = 288 ;
    static final double     DRIVE_GEAR_REDUCTION    = 2 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 6.75 ;// For figuring circumference
    static final double     WHEEL_SEPARATION = 15.25 ;
    static final double     COUNTS_PER_INCH         = COUNTS_PER_MOTOR_REV / (WHEEL_DIAMETER_INCHES * 3.14159265359);
    static final double     DRIVE_SPEED             = 0.45;
    static final double     TURN_SPEED              = 0.3;
    static final double     TIME_PER_INCH           = 0.5;
    int pathSel = 1;

    public void markerDropArm(){
        robot.collector.setPower(-1);
        sleep(50);
        robot.collector.setPower(-.2);
        sleep(100);
        robot.collector.setPower(.5);
        sleep(500);
        //Does it work?
        robot.collector.setPower(-.5);
        sleep(300);
        robot.collector.setPower(-.2);
    }

    public void markerDrop(){
        robot.marker_servo.setPosition(4.5);
        sleep(1200);
        robot.marker_servo.setPosition(.5);
        robotDrive(.75, -73,-73,0, "");
    }

    public void Lock(){
        telemetry.addData("locking", "");
        telemetry.update();
        robot.lifter_lock.setPosition(.43);
    }
    public void unlock(){
        robot.lifter_lock.setPosition(.56);
        telemetry.addData("unlocking",robot.lifter_lock.getPosition());
        telemetry.update();

    }


    public void lowerRobot() {
        robot.lifter.setPower(.01);
        unlock();
        telemetry.addData("lowering", "");
        telemetry.update();
        ElapsedTime runtime = new ElapsedTime();
        while (opModeIsActive() && (runtime.seconds() < 4.3)) {
            telemetry.addData("lowering", runtime.seconds());
            telemetry.update();
            sleep(100);
        }
    }

    public void lifterGoUpSlightly(){
        telemetry.addData("Lifter Goes Up Slightly","");
        telemetry.update();
        robot.lifter.setPower(-.25);
        sleep(170);
        robot.lifter.setPower(0);
    }

    public void activateLifter(){
        lowerRobot();
        lifterGoUpSlightly();
    }

    public void robotDrive(double driveSpeed, double leftInches, double rightInches, double timeout, String description) {
        Show(description);

        robot.left_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.right_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.left_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.right_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        int newLeftTarget = robot.left_drive.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
        int newRightTarget = robot.right_drive.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
         robot.left_drive.setTargetPosition(newLeftTarget);
        robot.right_drive.setTargetPosition(newRightTarget);

        robot.left_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.right_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.left_drive.setPower(Math.abs(driveSpeed));
        robot.right_drive.setPower(Math.abs(driveSpeed));

        ElapsedTime runtime = new ElapsedTime();

        while(
                opModeIsActive()
                && (robot.left_drive.isBusy() || robot.right_drive.isBusy()) // either drive is busy
//                && (timeout=0.0 || runtime.seconds() < timeout)
                && runtime.seconds() < timeout
        ) {
            sleep(100);
            telemetry.addData("Step", description);
            telemetry.addData("Timeout", "%.1f", runtime.seconds());
            telemetry.update();
        }

        robot.left_drive.setPower(0);
        robot.right_drive.setPower(0);
        //sleep(0020);
        robot.left_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.right_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void robotTurn(double DRIVE_SPEED,double rightDegrees, double timeout,String description){
        double inches = (rightDegrees * WHEEL_SEPARATION/2 * 3.1415926 / 180)/2;
        robotDrive(DRIVE_SPEED, inches*2, -inches*2,timeout, description);
    }

     /*private void pathFour() {
        robotDrive(DRIVE_SPEED,3,3,0);
        robotTurn(TURN_SPEED,90,3);
        sleep(5000);
        robotDrive(DRIVE_SPEED,45,45,0);
        robot.marker_servo.setPosition(0);
        sleep(2000);
        robotDrive(DRIVE_SPEED,-22,-22,0);
    }

    private void pathThree() {
        robotDrive(DRIVE_SPEED,3,3,0);
        robotTurn(TURN_SPEED,90,3);
        robotDrive(DRIVE_SPEED,45,45,0);
        robot.marker_servo.setPosition(0);
        sleep(2000);
        robotDrive(DRIVE_SPEED,-22,-22,0);
    }
*/


    public void robotSteps() {
        robotDrive(.2,2,2,5, "Moving to Depot 2in");
        robotTurn(.3,45,1.5,"Moving to Depot Turn 45R");
        robotDrive(DRIVE_SPEED, 38,38,10,"Moving to Depot 38in");
        robotTurn(TURN_SPEED,80,3,"Moving to Depot 80R");
        robotDrive(DRIVE_SPEED, 15,15,5,"Moving to Depot 15in");

        Show ("Droping Marker");
        markerDropArm();

        robotDrive(.3,3,-3,10,"spinning towrds \"Crator\"");

        robotDrive(.7, -60,-60,20,
                "Driving Towrds opponents \"Crator\"");
    }

    protected void Show(String message) {
        telemetry.addData(message,"");
        telemetry.update();
    }

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        robot = new HardwareTractor();
        robot.init(hardwareMap);
        robot.marker_servo.setPosition(.5);


        Show("Waiting For Start!");


        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        activateLifter();
        robotSteps();
        //keep rover on creator
        Show("Wainting for end");
        while(opModeIsActive()){
            robot.left_drive.setPower(-.4);
            robot.left_drive.setPower(-.4);
            sleep(100);
        }
    }
}
