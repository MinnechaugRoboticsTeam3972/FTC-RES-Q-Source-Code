package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.ftcrobotcontroller.RobotFunctions.DriveMotors;
import com.qualcomm.ftcrobotcontroller.RobotFunctions.DriveDirections;

public abstract class TwoMotorDriveOpMode extends LinearOpMode {

    protected DriveMotors driveMotors;

    //wait (seconds) amount of time before next command is executed
    public void waitSeconds(double seconds){
        double start = getRuntime();
        while((getRuntime() - start < seconds) && opModeIsActive()){}
    }

    //convert inches to encoder ticks for motor programming
    public int inchToEncoder(double distance){
        return (int) ((distance + (distance * .1)) * 150.943396226415);
    }

    //scale right distance in order to make robot drive straight
    public double scaleRight(double distance){
        return distance - (.75/20 * distance);
    }

    //set drive pattern based on distance, power, and direction
    public void setDrive (double inches, double power, DriveDirections direction)throws InterruptedException{
        driveMotors.resetEncoders();
        Thread.sleep(100);
        driveMotors.runPosition();
        Thread.sleep(100);

        int RDistance = inchToEncoder(scaleRight(inches));
        int LDistance = inchToEncoder(inches);
        switch (direction){
            case FORWARDS:
                driveMotors.setDistance(LDistance, RDistance);
                break;
            case BACKWARDS:
                driveMotors.setDistance(-LDistance, -RDistance);
                break;
            case RIGHT:
                driveMotors.setDistance(LDistance, -RDistance);
                break;
            case LEFT:
                driveMotors.setDistance(-LDistance, RDistance);
                break;
        }

        driveMotors.driveForwards(power);
    }

    //to be implemented by inheriting classes
    public abstract void initialize() throws InterruptedException;

}
