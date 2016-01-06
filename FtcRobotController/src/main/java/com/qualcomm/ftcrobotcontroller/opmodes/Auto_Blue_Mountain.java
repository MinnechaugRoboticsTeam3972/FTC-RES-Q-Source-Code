package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.ftcrobotcontroller.RobotFunctions.DriveMotors;

public class Auto_Blue_Mountain extends LinearOpMode {

    DcMotor motor_arm, motor_winch_in, motor_winch_out, driveLeft, driveRight;
    LightSensor lightSensor;
    UltrasonicSensor frontDistance;
    UltrasonicSensor backDistance;
    UltrasonicSensor floorDistance;
    GyroSensor gyro;
    ElapsedTime timer;
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
        frontDistance = hardwareMap.ultrasonicSensor.get("frontDistance");
        backDistance = hardwareMap.ultrasonicSensor.get("backDistance");
        floorDistance = hardwareMap.ultrasonicSensor.get("floorDistance");
        gyro = hardwareMap.gyroSensor.get("gyro");

        //create object to keep track of time; will be sent to nxtGyro for finding heading
        timer = new ElapsedTime();

        //create motor cluster for drive motors;
        driveMotors = new DriveMotors(driveLeft, driveRight, timer);

        driveLeft.setDirection(DcMotor.Direction.REVERSE);

        //these motors will have to run at constant speeds
        motor_arm.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        driveRight.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        driveLeft.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

        waitOneFullHardwareCycle();

        waitForStart();

        //drive leg of triangle adjacent to start
        driveMotors.DriveForwards();
        while(driveMotors.getDistance() < 213.36){
            if()
        }

        //one encoder turn = 23.8125 centimeters (drive motors)

    }
}
