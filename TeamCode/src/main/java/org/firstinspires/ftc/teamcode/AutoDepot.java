
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;



@Autonomous(name="AutoDepot", group="Linear Opmode")
//Disabled
public class AutoDepot extends LinearOpMode {

    protected HardwareTractor robot;
    private ElapsedTime runtime = new ElapsedTime();

    static final double     COUNTS_PER_MOTOR_REV    = 288 ;
     static final double     WHEEL_DIAMETER_INCHES   = 6.75 ;// For figuring circumference
    static final double     WHEEL_SEPARATION = 15.25 ;
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 0.45;
    static final double     TURN_SPEED              = 0.3;
    static final double     TIME_PER_INCH           = 0.5;
    int pathSel = 1;
    public double leftAbort;
    public double rightAbort;
    public double abortTime;


    public void markerDropArm(double robotPath){
        robot.collector.setPower(-1);
        sleep(50);
        robot.collector.setPower(-.2);
        sleep(100);
        robot.collector.setPower(.5);
        sleep(2000);
        robot.collector.setPower(-.5);
        sleep(300);
        robot.collector.setPower(-.2);
        if(robotPath == 1){
            //robotTurn(.5, 15);
            robot.robotDrive(this,.3,3,-3,0);
            robot.robotDrive(this,.7, -60,-60,0);
           // robotTurn(.3, -5);
            //robotDrive(.7, -20,-20,0);
        }
        if(robotPath == 2) {
            robot.robotDrive(this,.3,-1.5,1.5,0);
            //robotTurn(.5, -12);
            robot.robotDrive(this,.7, -60, -60, 0);
            //robotTurn(.3, 5);
            //robotDrive(.7, -20, -20, 0);
        }
    }

    public void markerDrop(){
        robot.marker_servo.setPosition(4.5);
        sleep(1200);
        robot.marker_servo.setPosition(.5);
        robot.robotDrive(this,.75, -73,-73,0);
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


    public void LowerRobot() {
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
    public void upLifter(){
        robot.lifter.setPower(-.25);
        sleep(170);
        robot.lifter.setPower(0);
        }

    public void robotLifter(){
        LowerRobot();
        upLifter();
        //Lock();
    }

    public void robotDrive(double driveSpeed, double leftInches, double rightInches, double timeout) {

        //if(timeout == 0){
         //   leftAbort = (leftInches/driveSpeed)*TIME_PER_INCH;
          //  rightAbort = (rightInches/driveSpeed)*TIME_PER_INCH;
       // } else {
        //    leftAbort = timeout;
        //    rightAbort = timeout;
        //}

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

        abortTime = getRuntime();
        while(opModeIsActive() && (robot.left_drive.isBusy() || robot.right_drive.isBusy())) {
            sleep(100);
        }

        robot.left_drive.setPower(0);
        robot.right_drive.setPower(0);
        //sleep(0020);
        robot.left_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.right_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }


    public void robotSteps(){

      pathOne();

    }





    private void pathOne() {
        robot.robotDrive(this,.2,2,2,0);
        robot.robotTurn(this,.3,45);
        //robotDrive(DRIVE_SPEED, 30,30);
        //robotTurn(TURN_SPEED, -45);
        //robotDrive(DRIVE_SPEED, 15,15);
        //robotTurn(DRIVE_SPEED, 45);
        robot.robotDrive(this,DRIVE_SPEED, 38,38,0);
       //robotDrive(DRIVE_SPEED,-5,-5,0);
        robot.robotTurn(this,TURN_SPEED,80);
        robot.robotDrive(this,DRIVE_SPEED, 15,15,0);
        //robotTurn(.3, -40);
        markerDropArm(1);
    }

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        robot = new HardwareTractor();
        robot.init(hardwareMap);
        robot.marker_servo.setPosition(.5);





        waitForStart();
        runtime.reset();

        robotLifter();
        robotSteps();
        while(opModeIsActive()){
            robot.left_drive.setPower(-.4);
            robot.left_drive.setPower(-.4);
            sleep(100);
        }
    }
}
