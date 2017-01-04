package com.qualcomm.ftcrobotcontroller.RobotFunctions;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

public class DriveMotors {

    public DcMotorController driveController;
    public DcMotor driveLeft, driveRight;
    private final double circumference = 16 * Math.PI;

    //DriveMotors Constructor
    public DriveMotors(DcMotor left, DcMotor right, DcMotorController controller) {
        this.driveController = controller;
        driveLeft = left;
        driveRight = right;
        driveLeft.setDirection(DcMotor.Direction.REVERSE);
        runEncoders();
        setWrite();
    }

    //Drive robot forwards
    public void driveForwards(double power) {
        driveLeft.setPower(power);
        driveRight.setPower(power);
    }

    //Drive robot backwards
    public void driveBackwards(double power) {
        driveLeft.setPower(-power);
        driveRight.setPower(-power);
    }

    //Turn robot to the right
    public void turnRight(double power) {
        driveLeft.setPower(power);
        driveRight.setPower(-power);
    }

    //Turn robot to the left
    public void turnLeft(double power) {
        driveLeft.setPower(power);
        driveRight.setPower(power);
    }

    //Stop both drive motors
    public void stop() {
        driveLeft.setPower(0);
        driveRight.setPower(0);
    }

    //Set the distance to be driven
    public void setDistance(int left, int right) {
        driveRight.setTargetPosition(right);
        driveLeft.setTargetPosition(left);
    }

    //set motor controller to be in WRITE_ONLY mode
    private void setWrite() {
        driveController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
    }

    //set motors to RUN_TO_POSITON mode
    public void runPosition() {
        driveRight.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
        driveLeft.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
    }

    //set motors to RUN_USING_ENCODERS mode
    public void runEncoders() {
        driveRight.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        driveLeft.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
    }

    //set motors to RESET_ENCODERS mode
    public void resetEncoders() {
        driveLeft.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        driveRight.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    }

    //calculate the angle turned by the robot based on portion of circumference turned
    public double angleToInch(double angle) {
        double ratio = angle / (360);
        return (ratio * circumference);
    }

}
