package com.qualcomm.ftcrobotcontroller.RobotFunctions;

import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class NxtGyro {

    GyroSensor gyroSensor;
    ElapsedTime timer;

    public NxtGyro(GyroSensor gyro, ElapsedTime time){
        gyroSensor = gyro;
        timer = time;
    }

    public double getHeading(){

        return 1;
    }
}
