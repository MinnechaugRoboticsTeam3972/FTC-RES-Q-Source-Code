package com.qualcomm.ftcrobotcontroller.RobotFunctions;

import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class NxtGyro{

    GyroSensor gyroSensor;
    ElapsedTime timer;
    double heading = 0;

    public NxtGyro(GyroSensor gyro, ElapsedTime time){
        gyroSensor = gyro;
        timer = time;
    }

    //returns heading in radians
    public double getHeading(){
        double temp = gyroSensor.getRotation() * (180.0 / Math.PI);
        temp *= timer.time();
        timer.reset();
        heading += temp;
        return heading;
    }

    public void resetHeading(){
        heading = 0;
    }
}
