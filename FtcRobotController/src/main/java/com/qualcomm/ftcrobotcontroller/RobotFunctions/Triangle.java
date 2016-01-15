package com.qualcomm.ftcrobotcontroller.RobotFunctions;

public class Triangle {

    public static final double rightAngle = Math.PI / 2.0;
    public static final double straightAngle = Math.PI;

    public static double getComplement(double angle){
        return rightAngle - angle;
    }

    public static double getSupplement(double angle){
        return straightAngle - angle;
    }

    public static double lawOfCosinesSide(double angle, double side1, double side2){
        return Math.sqrt(Math.pow(side1, 2) + Math.pow(side2, 2) - (2 * side1 * side2 * Math.cos(angle)));
    }

    public static double lawOfCosinesAngle(double side1, double side2, double oppSide){
        return Math.acos((Math.pow(side1, 2) + Math.pow(side2, 2) - Math.pow(oppSide, 2)) / (2 * side1 * side2));
    }
}
