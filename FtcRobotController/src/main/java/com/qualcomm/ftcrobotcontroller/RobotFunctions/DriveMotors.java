package com.qualcomm.ftcrobotcontroller.RobotFunctions;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.ElapsedTime;

public class DriveMotors {
    private DcMotor driveLeft, driveRight;
    private NxtGyro nxtGyro;
    private ElapsedTime timer;
    private double distance;

    //DriveMotors Constructor
    public DriveMotors(DcMotor left, DcMotor right, NxtGyro gyro, ElapsedTime time){
        driveLeft = left;
        driveRight = right;
        nxtGyro = gyro;
        timer = time;
        distance = 0;
    }

    //Drive robot forwards
    public void DriveForwards() {
        this.checkMode();
        driveLeft.setPower(100);
        driveRight.setPower(100);
    }

    //Drive robot backwards
    public void DriveBackwards() {
        this.checkMode();
        driveLeft.setPower(-100);
        driveRight.setPower(-100);
    }

    //Turn robot to the right until desired angle is reached
    public void turnRight(double angle){
        this.checkMode();
        driveLeft.setPower(100);
        driveRight.setPower(-100);
        timer.reset();
        while(nxtGyro.getHeading() < angle){
            //wait until turn is complete
        }
        nxtGyro.resetHeading();
        this.stop();
    }

    //Turn robot to the left until desired angle is reached
    public void turnLeft(double angle){
        this.checkMode();
        driveLeft.setPower(-100);
        driveRight.setPower(100);
        timer.reset();
        while(nxtGyro.getHeading() < angle){
            //wait until turn is complete
        }
        nxtGyro.resetHeading();
        this.stop();
    }

    //Get the total distance traveled by the robot since last reset
    public double getDistance(){
        distance +=  driveRight.getCurrentPosition() * 23.8125;
        return distance;
    }

    //Reset both the encoder distance and instance variable value
    //WARNING!! This method should only be used when drive motors are not running to ensure accurate calibration
    public void resetDistance(){
        distance = 0;
        driveLeft.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        driveRight.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        this.checkMode();
    }

    //Stop both drive motors
    public void stop(){
        driveLeft.setPower(0);
        driveRight.setPower(0);
    }

    //check the encoder state of both motors; if in autonomous mode, set motors to run with encoders
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
