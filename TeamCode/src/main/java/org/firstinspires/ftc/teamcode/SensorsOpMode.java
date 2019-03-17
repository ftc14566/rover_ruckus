package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;

import android.graphics.Color;

@TeleOp(name="Nate's Sensor reader", group="Iterative Opmode")
public class SensorsOpMode extends OpMode {
    private ColorSensor sensor = null;

    public final void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void init() {
        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        sensor = hardwareMap.get(ColorSensor.class, "sensor");

        sensor.enableLed(true);


        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery

        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "System overload, initializing Self Destruction Sequence Activated");
    }

    float hsvValues[] = {0F,0F,0F};
    final float[] values = hsvValues;

    @Override
    public void loop() {
        Color.RGBToHSV(sensor.red() * 8, sensor.green() * 8, sensor.blue() * 8, hsvValues);
        telemetry.addData("Red", sensor.red());
        telemetry.addData("Green", sensor.green());
        telemetry.addData("Blue", sensor.green());
        telemetry.addData("Hue", hsvValues[0]);
        telemetry.addData("Sat", hsvValues[1]);
        telemetry.addData("Val", hsvValues[2]);
        if (isGold()) {

            telemetry.addData("Gold", "");

        }
        telemetry.update();

        sleep(50);
    }

    private boolean isGold(){
        float h=hsvValues[0];
        float s=hsvValues[1];
        if (20<h && h<40 && 0.5<s && s<0.7)
            return true;
        else
            return false;

    }
    @Override
    public void stop() {
        sensor.enableLed(false);
    }

}