package com.qualcomm.ftcrobotcontroller.Autonomous.RobotFunctions;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

public class HangingArm {
    DcMotor motor_arm, motor_winch_in, motor_winch_out;
    Gamepad controller;

    // Speed of lower arm rotation is: V = .75r cm/s
    // r is the angular speed of the motor


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

    /**
     * Extend both parts of the arm. Motor_winch_out and motor_winch_in need to work inversely
     *
     * @param time
     */
    public void extendArm(int time){

        //rotate motor_arm
        //rotate motor_winch_out
        //-rotate motor_winch_in
    }

    /**
     * Pull in both parts of the arm. Motor_winch_out and motor_winch_in need to operate inversely
     * @param time
     */

    public void retractArm(int time){

        //-rotate motor_arm
        // rotate motor_winch_in
        //-rotate motor_winch_out
    }

    /**
     * Outer part of arm needs to be pulled  back in. Motor_winch_out and motor_winch_in need to operate inversely
     * @param time
     */
    public void pullUp(int time){
        //rotate motor _winch_in
        //run motor_arm at speed 0
        
    }
}
