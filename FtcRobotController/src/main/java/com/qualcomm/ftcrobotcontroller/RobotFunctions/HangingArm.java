package com.qualcomm.ftcrobotcontroller.RobotFunctions;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

public class HangingArm {
    DcMotor motor_arm, motor_winch_in, motor_winch_out;
    final double armSpeedCoefficient = .75;
    final double winchInCoefficient = 0;
    final double winchOutCoefficient = 0;

    // Speed of lower arm rotation is: V = .75r cm/s
    // r is the angular speed of the motor


    /**
     * Constructor for usage in OpModes/LinearOpModes
     * @param arm arm base pivot motor
     * @param winch_in motor to bring the end of the arm back to the robot
     * @param winch_out motor to rotate end of arm out
     */
    public HangingArm(DcMotor arm, DcMotor winch_in, DcMotor winch_out){
        motor_arm = arm;
        motor_winch_in = winch_in;
        motor_winch_out = winch_out;
    }

    public void extendArm(){
        motor_arm.setPower(75);
        motor_winch_in.setPower(90);
        motor_winch_out.setPower(50);
    }

    public void retractArm(){
        motor_arm.setPower(-75);
        motor_winch_in.setPower(-90);
        motor_winch_out.setPower(-50);
    }

    public void pullUp(){

        if(motor_arm.getMode() != DcMotorController.RunMode.RUN_USING_ENCODERS) {
            motor_arm.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        }
        motor_arm.setPower(1);
        motor_winch_in.setPower(-50);
        motor_winch_out.setPower(-90);

        motor_arm.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
    }

    public void pullUpReverse(){

        if(motor_arm.getMode() != DcMotorController.RunMode.RUN_USING_ENCODERS) {
            motor_arm.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        }

        motor_arm.setPower(1);
        motor_winch_in.setPower(50);
        motor_winch_out.setPower(90);

        motor_arm.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
    }
}
