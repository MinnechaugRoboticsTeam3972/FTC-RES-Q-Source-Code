package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

public class Teleop12_14 extends OpMode {

    DcMotor driveLeft;
    DcMotor driveRight;

    public void init() {
        driveLeft = hardwareMap.dcMotor.get("driveLeft");
        driveRight = hardwareMap.dcMotor.get("driveRight");

        driveLeft.setDirection(DcMotor.Direction.REVERSE);
    }

    public void loop() {
        double rightDrivePower = -gamepad1.right_stick_y;
        double leftDrivePower = -gamepad1.left_stick_y;

        rightDrivePower = Range.clip(rightDrivePower, -1, 1);
        leftDrivePower = Range.clip(leftDrivePower, -1, 1);

        driveLeft.setPower(leftDrivePower);
        driveRight.setPower(rightDrivePower);
    }
}
