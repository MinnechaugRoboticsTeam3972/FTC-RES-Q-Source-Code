package com.qualcomm.ftcrobotcontroller.Autonomous.RobotFunctions;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

public class HangingArm {
    DcMotor motor_arm, motor_winch_in, motor_winch_out;
    Gamepad controller;


    /**
     * Constructor for Autonomous mode / constructor for when no remote control is being used
     * @param arm arm base pivot motor
     * @param winch_in motor to bring the end of the arm back to the robot
     * @param winch_out motor to rotate end of arm out
     */
    public HangingArm(DcMotor arm, DcMotor winch_in, DcMotor winch_out){
        motor_arm = arm;
        motor_winch_in = winch_in;
        motor_winch_out = winch_out;
    }

    /**
     * Constructor for Teleop Mode
     * @param arm arm base pivot motor
     * @param winch_in motor to bring the end of the arm back to the robot
     * @param winch_out motor to rotate end of arm out
     * @param c gamepad controller reference to gamepad object that controls the hanging arm
     */
    public HangingArm (DcMotor arm, DcMotor winch_in, DcMotor winch_out, Gamepad c){
        motor_arm = arm;
        motor_winch_in = winch_in;
        motor_winch_out = winch_out;
        controller = c;
    }
    public void extendArm(int time){

    }
    public void retractArm(int time){

    }
    public void pullUp(int time){
        
    }
}
