package com.qualcomm.ftcrobotcontroller.RobotFunctions;

import com.qualcomm.robotcore.hardware.UltrasonicSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class UltrasonicGroup {

    UltrasonicSensor frontDistance, backDistance, floorDistance;
    ElapsedTime timer;
    private final double distanceInterval = 0.1;
    private double newRate;
    private double lastRate;

    public UltrasonicGroup(UltrasonicSensor front, UltrasonicSensor back, UltrasonicSensor floor, ElapsedTime time){
        frontDistance = front;
        backDistance = back;
        floorDistance = floor;
        timer = time;
        newRate = 0;
        lastRate = 0;
    }

    //get distance to object in front in centimeters
    public double getFrontDistance(){
        return backDistance.getUltrasonicLevel();
    }

    //get distance to object in back in centimeters
    public double getBackDistance(){
        return backDistance.getUltrasonicLevel();
    }

    //get distance to object on floor in centimeters
    public double getFloorDistance(){
        return floorDistance.getUltrasonicLevel();
    }

    //get current rate at which distance is changing in cm/s

    public double getLastRate(){

        return lastRate;
    }

    //get rate at which distance is changing in cm/s
    //WARNING!! This method must be called before getLastRate can be used
    public double getNewRateofChange(UltrasonicSensor sensor){
        lastRate = newRate;
        timer.reset();
        double current = sensor.getUltrasonicLevel();
        while(timer.time() < distanceInterval){

        }
        newRate = (sensor.getUltrasonicLevel() - current) / timer.time();
        return newRate;
    }
}
