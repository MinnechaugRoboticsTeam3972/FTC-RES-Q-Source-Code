package com.qualcomm.ftcrobotcontroller.RobotFunctions;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.ElapsedTime;

public class DriveMotors {
    DcMotor driveLeft, driveRight;
    NxtGyro nxtGyro;
    ElapsedTime timer;

    public DriveMotors(DcMotor left, DcMotor right, NxtGyro gyro, ElapsedTime time){
        driveLeft = left;
        driveRight = right;
        nxtGyro = gyro;
        timer = time;
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
        driveLeft.setPower(100);
        driveRight.setPower(-100);
        timer.reset();
        while(nxtGyro.getHeading() < 90){}
        driveLeft.setPower(0);
        driveRight.setPower(0);
    }

    public void turnLeft(double angle){
        this.checkMode();
        driveLeft.setPower(-100);
        driveRight.setPower(100);
        timer.reset();
        while(nxtGyro.getHeading() < 90){}
        driveLeft.setPower(0);
        driveRight.setPower(0);
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

//http://swerverobotics.org/wp/index.php/resources/ftc-programming/swerve-ftc-library/
