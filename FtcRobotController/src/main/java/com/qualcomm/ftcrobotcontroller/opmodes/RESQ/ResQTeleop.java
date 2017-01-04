package com.qualcomm.ftcrobotcontroller.opmodes.RESQ;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.ftcrobotcontroller.RobotFunctions.ServoConfig;

public class ResQTeleop extends OpMode {

    DcMotor leverLeft, leverRight, driveLeft, driveRight;
    boolean lowPower, driveBack;
    double rightDrivePower, leftDrivePower;
    Servo servoRight, servoLeft;

    public void init() {
        //create sensor and motor objects for robot parts
        leverLeft = hardwareMap.dcMotor.get("leverLeft");
        leverRight = hardwareMap.dcMotor.get("leverRight");
        driveLeft = hardwareMap.dcMotor.get("driveLeft");
        driveRight = hardwareMap.dcMotor.get("driveRight");
        servoRight = hardwareMap.servo.get("servoRight");
        servoLeft = hardwareMap.servo.get("servoLeft");

        lowPower = false;
        driveBack = false;

        leverRight.setDirection(DcMotor.Direction.REVERSE);
        driveLeft.setDirection(DcMotor.Direction.REVERSE);

        servoLeft.setPosition(ServoConfig.LEFT_UP.position);
        servoRight.setPosition(ServoConfig.RIGHT_UP.position);

    }

    public void loop() {

        //controller 1 controls drive with joystick

        rightDrivePower = -gamepad1.right_stick_y;
        leftDrivePower = -gamepad1.left_stick_y;

        if(gamepad1.right_bumper){
            lowPower = false;
        }
        if(gamepad1.left_bumper){
            lowPower = true;
        }
        if(gamepad1.right_stick_button){
            driveBack = false;
            updateDriveMode();
        }
        if (gamepad1.left_stick_button){
            driveBack = true;
            updateDriveMode();
        }

        clipPower();
        driveLeft.setPower(leftDrivePower);
        driveRight.setPower(rightDrivePower);

        //contoller 2 controls lever motor and servos
        double leverRightPower = -gamepad2.right_stick_y;
        double leverLeftPower = -gamepad2.left_stick_y;
        leverRightPower = Range.clip(leverRightPower, -.2, .2);
        leverLeftPower = Range.clip(leverLeftPower, -.2, .2);
        leverRight.setPower(leverRightPower);
        leverLeft.setPower(leverLeftPower);

        if(gamepad2.dpad_down){
            servoLeft.setPosition(ServoConfig.LEFT_OUT.position);
        }
        if(gamepad2.dpad_up){
            servoLeft.setPosition(ServoConfig.LEFT_UP.position);
        }
        if(gamepad2.a){
            servoRight.setPosition(ServoConfig.RIGHT_OUT.position);
        }
        if(gamepad2.y){
            servoRight.setPosition(ServoConfig.RIGHT_UP.position);
        }

    }

    public void updateDriveMode(){
        driveLeft.setPower(0);
        driveRight.setPower(0);
        if (driveBack) {
            driveRight = hardwareMap.dcMotor.get("driveLeft");
            driveRight.setDirection(DcMotor.Direction.FORWARD);
            driveLeft = hardwareMap.dcMotor.get("driveRight");
            driveLeft.setDirection(DcMotor.Direction.REVERSE);
        }
        else {
            driveRight = hardwareMap.dcMotor.get("driveRight");
            driveRight.setDirection(DcMotor.Direction.FORWARD);
            driveLeft = hardwareMap.dcMotor.get("driveLeft");
            driveLeft.setDirection(DcMotor.Direction.REVERSE);
        }
    }

    public void clipPower(){
        if(lowPower){
            rightDrivePower = Range.clip(rightDrivePower, -.5, .5);
            leftDrivePower = Range.clip(leftDrivePower, -.5, .5);
        }
        else {
            rightDrivePower = Range.clip(rightDrivePower, -1, 1);
            leftDrivePower = Range.clip(leftDrivePower, -1, 1);
        }

    }
}