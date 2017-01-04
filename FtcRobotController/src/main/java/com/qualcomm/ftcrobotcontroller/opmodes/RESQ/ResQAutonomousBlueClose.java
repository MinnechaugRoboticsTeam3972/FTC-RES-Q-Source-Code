package com.qualcomm.ftcrobotcontroller.opmodes.RESQ;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.ftcrobotcontroller.RobotFunctions.*;
import com.qualcomm.ftcrobotcontroller.opmodes.*;

public class ResQAutonomousBlueClose extends TwoMotorDriveOpMode{
    public NxtUltrasonic nxtUltrasonic;
    public Servo servoRight, servoLeft;

    public void runOpMode() throws InterruptedException{

        initialize();
        telemetry.addData("Checkpoint", "Initialization complete");

        waitOneFullHardwareCycle();
        waitForStart();


        telemetry.addData("Checkpoint", "Starting process");
        setDrive(3, .5, DriveDirections.BACKWARDS);
        waitSeconds(2);
        driveMotors.stop();

        telemetry.addData("Checkpoint", "Angling");
        setDrive(driveMotors.angleToInch(57), .5, DriveDirections.RIGHT);
        waitSeconds(2);
        driveMotors.stop();


        servoLeft.setPosition(ServoConfig.LEFT_IN.position);
        servoRight.setPosition(ServoConfig.RIGHT_OUT.position);

        telemetry.addData("Checkpoint", "Drive straight");
        setDrive(65, .8, DriveDirections.BACKWARDS);
        waitSeconds(6);
        driveMotors.stop();

        telemetry.addData("Checkpoint", "Back up");
        setDrive(2, .5, DriveDirections.FORWARDS);
        waitSeconds(1);
        driveMotors.stop();

        telemetry.addData("Checkpoint", "Facing ramp");
        setDrive(driveMotors.angleToInch(100), .5, DriveDirections.LEFT);
        waitSeconds(2);
        driveMotors.stop();

        telemetry.addData("Checkpoint", "Backing up");
        setDrive(5, .5, DriveDirections.BACKWARDS);
        waitSeconds(1);
        driveMotors.stop();

        servoLeft.setPosition(ServoConfig.LEFT_UP.position);
        servoRight.setPosition(ServoConfig.RIGHT_UP.position);

        telemetry.addData("Checkpoint", "Getting traction on ramp");
        climbRamp();

        telemetry.addData("Checkpoint", "Climbing ramp");
        setDrive(20, .5, DriveDirections.FORWARDS);
        waitSeconds(2);
        driveMotors.stop();

        telemetry.addData("Checkpoint", "Parked on ramp");
        waitSeconds(2);

        telemetry.addData("Checkpoint", "Autonomous Complete");

    }

    public void climbRamp()throws InterruptedException{
        driveMotors.resetEncoders();
        Thread.sleep(100);
        driveMotors.runEncoders();
        Thread.sleep(100);

        driveMotors.driveForwards(.7);
        boolean climbingRamp = false;
        int count = 0;
        int count2 = 0;
        while(!climbingRamp && opModeIsActive()){
            if(count > 6){
                climbingRamp = true;
            }
            nxtUltrasonic.calculateNewRate();
            if(nxtUltrasonic.getNewRate() < 0){
                while(!climbingRamp && opModeIsActive()){
                    if(count2 > 6){
                        climbingRamp = true;
                    }
                    nxtUltrasonic.calculateNewRate();
                    if(nxtUltrasonic.getNewRate() > 0){
                        climbingRamp = true;
                    }
                    count2++;
                }
            }
            count++;
        }
        driveMotors.stop();
    }

    public void initialize() throws InterruptedException{
        nxtUltrasonic = new NxtUltrasonic(hardwareMap.ultrasonicSensor.get("backUltrasonic"));
        driveMotors = new DriveMotors(hardwareMap.dcMotor.get("driveLeft"), hardwareMap.dcMotor.get("driveRight"),
                hardwareMap.dcMotorController.get("driveMotors"));
        servoRight = hardwareMap.servo.get("servoRight");
        servoLeft = hardwareMap.servo.get("servoLeft");

        driveMotors.resetEncoders();
        Thread.sleep(500);
        driveMotors.runEncoders();

    }

}
