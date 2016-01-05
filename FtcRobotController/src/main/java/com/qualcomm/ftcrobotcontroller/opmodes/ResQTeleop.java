package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.ftcrobotcontroller.RobotFunctions.HangingArm;

public class ResQTeleop extends OpMode {

    DcMotor motor_arm, motor_winch_in, motor_winch_out, driveLeft, driveRight;
    HangingArm hangingArm;

    public void init() {
        //create sensor and motor objects for robot parts
        motor_arm = hardwareMap.dcMotor.get("motor_arm");
        motor_winch_in = hardwareMap.dcMotor.get("motor_winch_in");
        motor_winch_out = hardwareMap.dcMotor.get("motor_winch_out");
        driveLeft = hardwareMap.dcMotor.get("driveLeft");
        driveRight = hardwareMap.dcMotor.get("driveRight");

        driveLeft.setDirection(DcMotor.Direction.REVERSE);

        //this motor will have to run at a constant velocity
        motor_arm.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

        //create hanging arm motor cluster for whole arm motions
        hangingArm = new HangingArm(motor_arm, motor_winch_in, motor_winch_out);
    }

    public void loop() {

        //controller 1 controls drive with joystick
        double rightDrivePower = -gamepad1.right_stick_y;
        double leftDrivePower = -gamepad1.left_stick_y;

        rightDrivePower = Range.clip(rightDrivePower, -1, 1);
        leftDrivePower = Range.clip(leftDrivePower, -1, 1);

        driveLeft.setPower(leftDrivePower);
        driveRight.setPower(rightDrivePower);

        //controller 2 controls lifting arm

        if(gamepad2.right_bumper){
            hangingArm.extendArm();
        }
        if(gamepad2.left_bumper){
            hangingArm.retractArm();
        }

        if(gamepad2.right_trigger > 0){
            hangingArm.pullUp();
        }
        if(gamepad2.left_trigger > 0 ){
            hangingArm.pullUpReverse();
        }

    }
}
