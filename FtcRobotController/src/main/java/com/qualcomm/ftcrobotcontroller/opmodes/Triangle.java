package com.qualcomm.ftcrobotcontroller.opmodes;

public class Triangle {

    public static final double bottomLeg = 213.36;
    public static final double sideLeg = 152.4;
    public final double hypotenuse = 262.2;
    public static final double bottomAngle = 35.54 * Math.PI / 180.0;
    public static final double upperAngle = 54.46 * Math.PI / 180;
    public static final double rightAngle = Math.PI / 2.0;

    public static double getComplement(double angle){
        return rightAngle - angle;
    }
}
