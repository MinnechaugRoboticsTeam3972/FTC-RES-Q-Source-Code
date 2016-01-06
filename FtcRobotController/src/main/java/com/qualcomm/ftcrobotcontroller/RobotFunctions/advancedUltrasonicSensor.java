package com.qualcomm.ftrrobotcontroller.RobotFunctions;

import com.qualcomm.robotcore.hardware.UltrasonicSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class advancedUltrasonicSensor{
  UltrasonicSensor sensor;
  ElapsedTime timer;
  private double newDistance;
  private double lastDistance;
  private double newRate;
  private double lastRate;
  
  public advancedUltrasonicSensor(UltrasonicSensor sensor, ElapsedTime time){
    this.sensor = sensor;
    timer = time;
    newDistance = 0;
    lastDistance = 0;
    newRate = 0;
    lastRate = 0;
  }
  
  public double getNewRate(){
  
  }
  
  public double getLastRate(){
  }
  
  public void calculateNewRate(){
    
  }
  
  public double getDistance(){
    return sensor.getUltrasonicLevel();
  }
}
