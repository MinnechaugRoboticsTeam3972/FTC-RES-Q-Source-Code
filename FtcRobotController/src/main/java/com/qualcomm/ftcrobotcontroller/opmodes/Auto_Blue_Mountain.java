package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.ftcrobotcontroller.RobotFunctions.DriveMotors;
import com.qualcomm.ftcrobotcontroller.RobotFunctions.NxtGyro;

public class Auto_Blue_Mountain extends LinearOpMode {

    DcMotor motor_arm, motor_winch_in, motor_winch_out, driveLeft, driveRight;
    LightSensor lightSensor;
    UltrasonicSensor distanceSensor;
    ColorSensor colorSensor;
    GyroSensor gyro;
    ElapsedTime timer;
    NxtGyro nxtGyro;
    DriveMotors driveMotors;
    final boolean isAutonomous = true;
    double distance = 0;


    public void runOpMode()throws InterruptedException {

        //create sensor and motor objects for robot parts
        motor_arm = hardwareMap.dcMotor.get("motor_arm");
        motor_winch_in = hardwareMap.dcMotor.get("motor_winch_in");
        motor_winch_out = hardwareMap.dcMotor.get("motor_winch_out");
        driveLeft = hardwareMap.dcMotor.get("driveLeft");
        driveRight = hardwareMap.dcMotor.get("driveRight");
        lightSensor = hardwareMap.lightSensor.get("lightSensor");
        distanceSensor = hardwareMap.ultrasonicSensor.get("distanceSensor");
        colorSensor = hardwareMap.colorSensor.get("colorSensor");
        gyro = hardwareMap.gyroSensor.get("gyro");

        //create object to keep track of time; will be sent to nxtGyro for finding heading
        timer = new ElapsedTime();
        nxtGyro = new NxtGyro(gyro, timer);

        //create motor cluster for drive motors;
        driveMotors = new DriveMotors(driveLeft, driveRight, nxtGyro, timer);

        driveLeft.setDirection(DcMotor.Direction.REVERSE);

        //these motors will have to run at constant speeds
        motor_arm.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        driveRight.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        driveLeft.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);


        waitForStart();

        //one encoder turn = 23.8125 centimeters (drive motors)

    }
}