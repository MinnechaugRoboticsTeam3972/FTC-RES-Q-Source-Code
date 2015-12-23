package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.ftcrobotcontroller.RobotFunctions.NxtGyro;
import com.qualcomm.ftcrobotcontroller.RobotFunctions.DriveMotors;

public class Auto12_14 extends LinearOpMode{

    DcMotor motor_arm, motor_winch_in, motor_winch_out, driveLeft, driveRight;
    UltrasonicSensor distanceSensor;
    ColorSensor colorSensor;
    GyroSensor gyro;
    ElapsedTime timer;
    NxtGyro nxtGyro;
    DriveMotors driveMotors;


    public void runOpMode()throws InterruptedException {

        //create sensor and motor objects for robot parts
        motor_arm = hardwareMap.dcMotor.get("motor_arm");
        motor_winch_in = hardwareMap.dcMotor.get("motor_winch_in");
        motor_winch_out = hardwareMap.dcMotor.get("motor_winch_out");
        driveLeft = hardwareMap.dcMotor.get("driveLeft");
        driveRight = hardwareMap.dcMotor.get("driveRight");
        distanceSensor = hardwareMap.ultrasonicSensor.get("distanceSensor");
        colorSensor = hardwareMap.colorSensor.get("colorSensor");
        gyro = hardwareMap.gyroSensor.get("gyro");

        //create object to keep track of time; will be sent to nxtGyro for finding heading
        timer = new ElapsedTime();
        nxtGyro = new NxtGyro(gyro, timer);

        //create motor cluster for drive motors;
        driveMotors = new DriveMotors(driveLeft, driveRight, nxtGyro);

        driveLeft.setDirection(DcMotor.Direction.REVERSE);

        //these motors will have to run at constant speeds
        motor_arm.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        driveRight.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        driveLeft.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);


        waitForStart();

    }

}
