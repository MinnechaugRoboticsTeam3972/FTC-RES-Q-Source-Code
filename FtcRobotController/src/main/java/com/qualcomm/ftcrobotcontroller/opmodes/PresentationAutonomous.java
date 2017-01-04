package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.RobotFunctions.*;

public class PresentationAutonomous extends TwoMotorDriveOpMode{

    public NxtUltrasonic nxtUltrasonic;

    public void runOpMode() throws InterruptedException{

        initialize();
        telemetry.addData("Status", "Initialization complete");

        waitOneFullHardwareCycle();
        waitForStart();

        telemetry.addData("Status", "Starting Drive");
        waitSeconds(1);

        //drive in random direction until obstacle is sensed in front of robot
        //choose new direction if obstacle is detected
        while(opModeIsActive()){
            double num = (Math.random() * 31) + 15;
            setDrive(num, .5, DriveDirections.RIGHT);
            waitSeconds(4);
            driveMotors.stop();
            driveMotors.resetEncoders();
            waitSeconds(.1);
            driveMotors.runEncoders();
            waitSeconds(.1);
            nxtUltrasonic.calculateDistance();
            driveMotors.driveBackwards(.5);
            while((nxtUltrasonic.getDistance() > 10) && opModeIsActive()){
                nxtUltrasonic.calculateDistance();
            }
            driveMotors.stop();
        }
    }

    public void initialize() throws InterruptedException {

        nxtUltrasonic = new NxtUltrasonic(hardwareMap.ultrasonicSensor.get("backUltrasonic"));
        driveMotors = new DriveMotors(hardwareMap.dcMotor.get("driveLeft"), hardwareMap.dcMotor.get("driveRight"),
                hardwareMap.dcMotorController.get("driveMotors"));
        driveMotors.resetEncoders();
        Thread.sleep(500);
        driveMotors.runEncoders();

    }

}
