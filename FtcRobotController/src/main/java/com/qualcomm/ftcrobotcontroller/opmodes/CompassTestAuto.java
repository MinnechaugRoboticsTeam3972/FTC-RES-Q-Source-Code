package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.RobotFunctions.I2cNxtCompass;

public class CompassTestAuto extends TwoMotorDriveOpMode{

    I2cNxtCompass compass;

    public void runOpMode() throws InterruptedException{
        initialize();
        waitOneFullHardwareCycle();
        waitForStart();

        //calibrate compass
        compass.setCalibrationMode();
        telemetry.addData("Compass", "Turn Clockwise");
        waitSeconds(20);
        telemetry.addData("Compass", "Turn Counterclockwise");
        waitSeconds(20);
        telemetry.addData("Compass", "Stop Turning");
        waitSeconds(1);

        compass.setMeasurementMode();

        //check if calibration is successful
        if(compass.calibrationFailed()){
            telemetry.addData("Compass", "Calibration Failed");
        }
        else{
            telemetry.addData("Compass", "Calibration Successful!");
        }
        waitSeconds(3);

        //display angle every second
        while(opModeIsActive()){

            telemetry.addData("Angle", compass.getAngle());
            waitSeconds(1);

        }

    }

    public void initialize(){
        compass = new I2cNxtCompass(hardwareMap.legacyModule.get("Hardware"), 5);
    }
}
