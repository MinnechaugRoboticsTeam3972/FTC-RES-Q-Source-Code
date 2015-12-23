package com.qualcomm.ftcrobotcontroller.RobotFunctions;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

public class DriveMotors {
    DcMotor driveLeft, driveRight;
    NxtGyro nxtGyro;

    public DriveMotors(DcMotor left, DcMotor right, NxtGyro gyro){
        driveLeft = left;
        driveRight = right;
        nxtGyro = gyro;
    }

    public void DriveForwards(long time) throws InterruptedException{
        this.checkMode();
        driveLeft.setPower(100);
        driveRight.setPower(100);
        Thread.sleep(time);
    }

    public void DriveBackwards(long time) throws InterruptedException{
        this.checkMode();
        driveLeft.setPower(-100);
        driveRight.setPower(-100);
        Thread.sleep(time);
    }

    public void turnRight(double angle){
        this.checkMode();
    }

    public void turnLeft(double angle){
        this.checkMode();
    }

    private void checkMode(){
        if(driveLeft.getMode() != DcMotorController.RunMode.RUN_USING_ENCODERS){
            driveLeft.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        }
        if(driveLeft.getMode() != DcMotorController.RunMode.RUN_USING_ENCODERS){
            driveLeft.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        }
    }
}
