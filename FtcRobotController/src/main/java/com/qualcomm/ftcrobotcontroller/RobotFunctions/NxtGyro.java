package com.qualcomm.ftcrobotcontroller.RobotFunctions;

import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class NxtGyro {
    private GyroSensor gyro;
    private double heading;
    ElapsedTime timer;


    public NxtGyro(GyroSensor gyro){
        this.gyro = gyro;
        timer = new ElapsedTime();
    }

    public void calculateHeading(){
        timer.startTime();
        double rate = this.getRotation();
        double time = this.getSeconds();
        heading += (rate * time);
        timer.reset();
    }

    public double getHeading(){
        return heading;
    }

    public double getRotation(){
        return gyro.getRotation() * (Math.PI / 180);
    }

    public void resetHeading(){
        heading = 0;
    }

    private double getSeconds(){
        return timer.time() * Math.pow(10, 9);
    }

}
