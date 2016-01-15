package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.ftcrobotcontroller.RobotFunctions.DriveMotors;
import com.qualcomm.ftcrobotcontroller.RobotFunctions.NxtGyro;
import com.qualcomm.ftcrobotcontroller.RobotFunctions.Triangle;
import com.qualcomm.ftcrobotcontroller.RobotFunctions.advUltrasonicSensor;

public class ResQAutonomous extends LinearOpMode {

    DcMotor driveLeft, driveRight;
    DriveMotors driveMotors;
    DcMotorController drive;
    ElapsedTime timer;
    NxtGyro nxtGyro;
    advUltrasonicSensor ultrasonicSensor;

    public void runOpMode()throws InterruptedException {

        timer = new ElapsedTime();

        //create sensor and motor objects for robot parts
        drive= hardwareMap.dcMotorController.get("drive");
        driveLeft = hardwareMap.dcMotor.get("driveLeft");
        driveRight = hardwareMap.dcMotor.get("driveRight");
        nxtGyro = new NxtGyro(hardwareMap.gyroSensor.get("nxtGyro"));
        ultrasonicSensor = new advUltrasonicSensor(hardwareMap.ultrasonicSensor.get("ultrasonicSensor"));

        //create motor cluster for drive motors;
        driveMotors = new DriveMotors(driveLeft, driveRight, drive);

        driveLeft.setDirection(DcMotor.Direction.REVERSE);

        waitOneFullHardwareCycle();

        waitForStart();

        //drive forwards for length of leg of triangle; check for inconsistencies in movement

    }

}
