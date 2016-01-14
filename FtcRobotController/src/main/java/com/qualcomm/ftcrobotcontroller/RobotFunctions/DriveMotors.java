package com.qualcomm.ftcrobotcontroller.RobotFunctions;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.ElapsedTime;

public class DriveMotors {
    ElapsedTime timer;
    DcMotorController drive;
    private DcMotor driveLeft, driveRight;
    private double distance;
    private double circumference;

    //DriveMotors Constructor
    public DriveMotors(DcMotor left, DcMotor right, DcMotorController controller){
        timer = new ElapsedTime();
        drive = controller;
        driveLeft = left;
        driveRight = right;
        distance = 0;
        circumference = Math.pow(18.7325, 2) * Math.PI;
    }

    //Drive robot forwards
    public void driveForwards() {
        this.setWrite();
        driveLeft.setPower(.5);
        driveRight.setPower(.5);
        this.setRead();
    }

    //Drive robot backwards
    public void driveBackwards() {
        this.setWrite();
        driveLeft.setPower(-.5);
        driveRight.setPower(-.5);
        this.setRead();
    }

    //Turn robot to the right until desired angle is reached
    public void turnRight(double angle) {
        this.setWrite();
        driveLeft.setPower(.5);
        driveRight.setPower(-.5);
        this.setRead();
        double target = this.angleToDistance(angle);
        while(this.getDistance() < target){
            //wait until turn is complete
        }
        this.stop();
    }

    //Turn robot to the left until desired angle is reached
    public void turnLeft(double angle) {
        this.setWrite();
        driveLeft.setPower(-.5);
        driveRight.setPower(.5);
        this.setRead();
        double target = this.angleToDistance(angle);
        timer.startTime();
        while(this.getDistance() < target){
            if(timer.time() > 3){
                this.stop();
            }
        }
        this.stop();
    }

    //Get the total distance traveled by the robot since last reset
    public double getDistance(){
        this.setRead();
        distance +=  driveRight.getCurrentPosition() * 23.8125;
        this.setWrite();
        return distance;
    }

    //Reset both the encoder distance and instance variable value
    //WARNING!! This method should only be used when drive motors are not running to ensure accurate calibration
    public void resetDistance(){
        this.setWrite();
        distance = 0;
        driveLeft.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        driveRight.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        this.setRead();
    }

    //Stop both drive motors
    public void stop(){
        this.setWrite();
        driveLeft.setPower(0);
        driveRight.setPower(0);
        this.setRead();
    }

    //calculate the angle turned by the robot based on portion of circumference turned
    private double angleToDistance(double angle) {
        double ratio = angle / (2 * Math.PI);
        return ratio * circumference;
    }

    private void setWrite(){
        drive.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
    }

    private void setRead(){
        drive.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_ONLY);
    }

}
