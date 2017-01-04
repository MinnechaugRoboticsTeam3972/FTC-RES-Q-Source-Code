package com.qualcomm.ftcrobotcontroller.RobotFunctions;

public enum ServoConfig {
    LEFT_IN(0), LEFT_OUT(.85), LEFT_UP(.3), RIGHT_IN(1), RIGHT_OUT(.15), RIGHT_UP(.7);
    public double position;

    ServoConfig(double pos){
        position = pos;
    }
}
