package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.ftcrobotcontroller.RobotFunctions.DriveMotors;
import com.qualcomm.ftcrobotcontroller.RobotFunctions.advUltrasonicSensor;

public class Auto_Red_Floor extends LinearOpMode {

    DcMotor motor_arm, motor_winch_1, motor_winch_2, driveLeft, driveRight;
    advUltrasonicSensor frontDistance, backDistance, floorDistance;
    ElapsedTime timer;
    DriveMotors driveMotors;
    DcMotorController drive;

    public void runOpMode()throws InterruptedException {

        //create object to keep track of time; will be sent to nxtGyro for finding heading
        timer = new ElapsedTime();

        //create sensor and motor objects for robot parts
        drive = hardwareMap.dcMotorController.get("drive motors");
        driveLeft = hardwareMap.dcMotor.get("driveLeft");
        driveRight = hardwareMap.dcMotor.get("driveRight");
        frontDistance = new advUltrasonicSensor(hardwareMap.ultrasonicSensor.get("frontDistance"), timer);
        backDistance = new advUltrasonicSensor(hardwareMap.ultrasonicSensor.get("backDistance"), timer);
        floorDistance = new advUltrasonicSensor(hardwareMap.ultrasonicSensor.get("floorDistance"),timer);

        //create motor cluster for drive motors;
        driveMotors = new DriveMotors(driveLeft, driveRight, drive);

        driveLeft.setDirection(DcMotor.Direction.REVERSE);

        waitOneFullHardwareCycle();

        waitForStart();

        //drive forwards for length of leg of triangle; check for inconsistencies in movement
        driveMotors.driveForwards();
        while(driveMotors.getDistance() < Triangle.bottomLeg){
            checkDrive(backDistance, driveMotors);
        }
        driveMotors.stop();
        driveMotors.resetDistance();

        //turn Pi/2 radians to the right
        driveMotors.turnRight(Math.PI / 2.0);
    }

    static void checkDrive(advUltrasonicSensor sensor, DriveMotors motors)throws InterruptedException{
        sensor.calculateNewRate();
        Thread.sleep(500);
        sensor.calculateNewRate();
        if(sensor.getRateDifference() > 1 || sensor.getRateDifference() < -1 ){
            motors.stop();
            Thread.sleep(1000);
            motors.driveForwards();
        }
    }
}
