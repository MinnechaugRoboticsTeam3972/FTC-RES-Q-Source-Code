package com.qualcomm.ftcrobotcontroller.RobotFunctions;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

public class HangingArm {
    DcMotor motor_arm, motor_winch_1, motor_winch_2;
    DcMotorController arm;
    final double armSpeedCoefficient = .75;
    final double winchInCoefficient = 0;
    final double winchOutCoefficient = 0;

    // Speed of lower arm rotation is: V = .75r cm/s
    // r is the angular speed of the motor

    /**
     * Constructor for usage in OpModes/LinearOpModes
     * @param arm arm base pivot motor
     * @param winch_1 motor to bring the end of the arm back to the robot
     * @param winch_2 motor to rotate end of arm out
     */
    public HangingArm(DcMotor arm, DcMotor winch_1, DcMotor winch_2, DcMotorController armController){
        motor_arm = arm;
        motor_winch_1 = winch_1;
        motor_winch_1 = winch_2;
        this.arm = armController;
    }

    public void extendArm(){
        motor_arm.setPower(-1);
        motor_winch_1.setPower(1);
        motor_winch_2.setPower(1);
    }

    public void retractArm(){
        motor_arm.setPower(1);
        motor_winch_1.setPower(-1);
        motor_winch_2.setPower(-1);
    }

    public void pullUp(){

        motor_arm.setPower(0);
        //motor_arm.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motor_winch_1.setPower(-1);
        motor_winch_2.setPower(-1);
    }

    public void pullUpReverse(){

        motor_arm.setPower(0);
        motor_winch_1.setPower(1);
        motor_winch_2.setPower(1);
    }

    private void setWrite(){
        arm.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
    }

    private void setRead(){
        arm.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_ONLY);
    }
}
