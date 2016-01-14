package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.ftcrobotcontroller.RobotFunctions.DriveMotors;

public class ResQAutonomous extends LinearOpMode {

    DcMotor driveLeft, driveRight;
    DriveMotors driveMotors;
    DcMotorController drive;
    ElapsedTime timer;

    public void runOpMode()throws InterruptedException {

        timer = new ElapsedTime();

        //create sensor and motor objects for robot parts
        drive = hardwareMap.dcMotorController.get("drive motors");
        driveLeft = hardwareMap.dcMotor.get("driveLeft");
        driveRight = hardwareMap.dcMotor.get("driveRight");

        //create motor cluster for drive motors;
        driveMotors = new DriveMotors(driveLeft, driveRight, drive);

        driveLeft.setDirection(DcMotor.Direction.REVERSE);

        waitOneFullHardwareCycle();

        waitForStart();

        //drive forwards for length of leg of triangle; check for inconsistencies in movement
        driveMotors.driveForwards();
        timer.startTime();
        while(driveMotors.getDistance() < Triangle.bottomLeg){
            if(timer.time() > 600000000){
                driveMotors.stop();
                timer.reset();
            }
        }
        driveMotors.stop();
        driveMotors.resetDistance();

        //turn Pi/2 radians to the right
        driveMotors.turnLeft(Math.PI / 4.0);

        driveMotors.driveForwards();
        timer.startTime();
        while(driveMotors.getDistance() < 200){
            if(timer.time() > 600000000){
                driveMotors.stop();
                timer.reset();
            }
        }
    }

}
