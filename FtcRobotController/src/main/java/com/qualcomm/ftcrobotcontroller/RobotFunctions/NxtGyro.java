package com.qualcomm.ftcrobotcontroller.RobotFunctions;

import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

class NxtGyro {

    GyroSensor gyroSensor;

    public NxtGyro(GyroSensor gyro){
        gyroSensor = gyro;
    }

    public double getHeading(){

        return 2;
    }
}
