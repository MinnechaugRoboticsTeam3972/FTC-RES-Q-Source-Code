package com.qualcomm.ftcrobotcontroller.RobotFunctions;

import com.qualcomm.robotcore.hardware.UltrasonicSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class advUltrasonicSensor{
    UltrasonicSensor sensor;
    ElapsedTime timer;
    private double newDistance;
    private double lastDistance;
    private double newRate;
    private double lastRate;
    private double instantTime;
    private final double distanceInterval = 0.1;

    public advUltrasonicSensor(UltrasonicSensor sensor, ElapsedTime time){
        this.sensor = sensor;
        timer = time;
        newDistance = 0;
        lastDistance = 0;
        newRate = 0;
        lastRate = 0;
        instantTime = 0;
    }

    public double getNewRate(){
        return newRate;
    }

    public double getLastRate(){
        return lastRate;
    }

    public void calculateNewRate(){
        lastRate = newRate;
        timer.reset();
        double current = sensor.getUltrasonicLevel();
        timer.startTime();
        //get rate of distance change on a 1 seceond interval
        while(timer.time() < distanceInterval){
            instantTime = timer.time();
        }
        newRate = (sensor.getUltrasonicLevel() - current) / instantTime;
    }

    public double getRateDifference(){
        return this.getNewRate() - this.getLastRate();
    }

    public double getDistance(){
        return sensor.getUltrasonicLevel();
    }
}
