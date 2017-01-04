package com.qualcomm.ftcrobotcontroller.RobotFunctions;

import com.qualcomm.robotcore.hardware.UltrasonicSensor;

public class NxtUltrasonic extends UltrasonicSensor{

    public UltrasonicSensor sensor;
    private double distance;
    private double lastDistance;
    private double newRate;
    private double lastRate;

    public NxtUltrasonic(UltrasonicSensor sensor){
        this.sensor = sensor;
        distance = 0;
        lastDistance = 0;
        newRate = 0;
        lastRate = 0;
    }

    //return most current calculated rate
    public double getNewRate(){
        return newRate;
    }

    //return previous calculated rate
    public double getLastRate(){
        return lastRate;
    }

    //return last calculated distance
    public double getDistance(){
        return distance;
    }

    //return previous calculated distance
    public double getLastDistance(){
        return lastDistance;
    }

    //get difference between last two calculated rates
    public double getRateDifference(){
        return getNewRate() - getLastRate();
    }

    //calculate current distance
    public void calculateDistance(){
        lastDistance = distance;
        distance = (getUltrasonicLevel() - 3) / 2.54;
    }

    //calculate current rate in inches/second
    public void calculateNewRate() throws InterruptedException{
        lastRate = newRate;
        calculateDistance();
        Thread.sleep(400);
        calculateDistance();
        newRate = (distance - lastDistance) / .4;
    }

    //DO NOT USE THESE METHODS
    //THESE METHODS ARE ONLY NEEDED BECAUSE OF INHERITANCE OF UltrasonicSensor
    public String getDeviceName(){
        return sensor.getDeviceName();
    }
    public double getUltrasonicLevel(){
        return sensor.getUltrasonicLevel();
    }
    public int getVersion(){
        return sensor.getVersion();
    }
    public String status(){
        return sensor.status();
    }
    public String getConnectionInfo(){
        return sensor.getConnectionInfo();
    }
    public void close(){
        sensor.close();
    }
}
