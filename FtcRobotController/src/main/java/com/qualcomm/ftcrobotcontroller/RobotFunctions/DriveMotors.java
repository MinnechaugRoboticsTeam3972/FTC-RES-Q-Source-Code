package com.qualcomm.ftcrobotcontroller.RobotFunctions;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.ElapsedTime;


public class DriveMotors {
    private DcMotor driveLeft, driveRight;
    private ElapsedTime timer;
    private double distance;
    private double angle;
    private double circumference;
    private final double angleInterval = 0.1;

    //DriveMotors Constructor
    public DriveMotors(DcMotor left, DcMotor right, ElapsedTime time){
        driveLeft = left;
        driveRight = right;
        timer = time;
        distance = 0;
        angle = 0;
        circumference = Math.pow(18.7325, 2) * Math.PI;
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
        while(this.getAngle() < angle){
            //wait until turn is complete
        }
        this.resetAngle();
        this.stop();
    }

    //Turn robot to the left until desired angle is reached
    public void turnLeft(double angle){
        this.checkMode();
        driveLeft.setPower(-100);
        driveRight.setPower(100);
        timer.reset();
        while(this.getAngle() < angle){
            //wait until turn is complete
        }
        this.resetAngle();
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

    //resets the measured turn angle to 0
    private void resetAngle(){
        angle = 0;
    }

    //calculate the angle turned by the robot based on portion of circumference turned
    private double getAngle() {
        double currentDistance = distance;
        timer.startTime();
        //compound the angle after .1 seconds
        while(timer.time() < angleInterval){

        }
        double ratio = (this.getDistance() - currentDistance) / circumference;
        angle += ratio * (2 * Math.PI);
        return angle;
    }
}

//http://swerverobotics.org/wp/index.php/resources/ftc-programming/swerve-ftc-library/
