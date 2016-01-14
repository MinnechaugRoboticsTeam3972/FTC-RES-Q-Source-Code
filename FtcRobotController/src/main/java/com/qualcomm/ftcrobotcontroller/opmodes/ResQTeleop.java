package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

public class ResQTeleop extends OpMode {

    DcMotor motor_lever, driveLeft, driveRight;
    boolean onRamp;

    public void init() {
        //create sensor and motor objects for robot parts
        motor_lever = hardwareMap.dcMotor.get("motor_lever");
        driveLeft = hardwareMap.dcMotor.get("driveLeft");
        driveRight = hardwareMap.dcMotor.get("driveRight");

        onRamp = false;
        driveLeft.setDirection(DcMotor.Direction.REVERSE);

        //create hanging arm motor cluster for whole arm motions
    }

    public void loop() {

        //controller 1 controls drive with joystick

        double rightDrivePower = -gamepad1.right_stick_y;
        double leftDrivePower = -gamepad1.left_stick_y;

        if(gamepad1.right_bumper){
            if (onRamp){
                onRamp = false;
            }
            if(!onRamp){
                onRamp = true;
            }
        }

        if(onRamp){
            rightDrivePower = Range.clip(rightDrivePower, -.5, .5);
            leftDrivePower = Range.clip(leftDrivePower, -.5, .5);
        }
        else {
            rightDrivePower = Range.clip(rightDrivePower, -1, 1);
            leftDrivePower = Range.clip(leftDrivePower, -1, 1);
        }

        driveLeft.setPower(leftDrivePower);
        driveRight.setPower(rightDrivePower);

        //contoller 2 controls lever motor

        double power = -gamepad2.right_stick_y;
        power = Range.clip(power, -.25, .25);
        motor_lever.setPower(power);
    }
}
