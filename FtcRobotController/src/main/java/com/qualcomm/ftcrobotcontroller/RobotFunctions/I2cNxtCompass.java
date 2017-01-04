package com.qualcomm.ftcrobotcontroller.RobotFunctions;

import com.qualcomm.robotcore.hardware.LegacyModule;
import com.qualcomm.robotcore.hardware.I2cDevice;

public class I2cNxtCompass extends I2cDevice{

    private int zero;

    private final byte DEVICE_ADDRESS = 0x02;
    private final byte MEASUREMENT_MODE = 0x00;
    private final byte MODE_CONTROL = 0x41;
    private final byte TWO_DEGREE_HEADING = 0x42;
    private final byte CALIBRATION_MODE = 0x43;
    private final byte ONE_DEGREE_ADDER = 0x43;
    private final byte LOW_BYTE = 0x44;
    private final byte HIGH_BYTE = 0x45;

    public I2cNxtCompass(LegacyModule module, int portNumber){
        super(module, portNumber);
    }

    //Set compass to be in calibration mode
    public void setCalibrationMode(){
        setMode(CompassReg.CALIBRATION_MODE);
    }

    //Set compass to be in measurement mode
    public void setMeasurementMode(){
        setMode(CompassReg.MEASUREMENT_MODE);
    }

    /*
    return angle mesasured by compass
    returns absolute value of difference between cardinal heading and zero point
     */
    public int getAngle(){
        return Math.abs(getHeading(CompassReg.HIGH_BYTE) - zero);
    }

    //returns if calibration failed
    public boolean calibrationFailed(){
        setMode(CompassReg.MODE_CONTROL);

        if(readBuffer()[1] == 2){
            return true;
        }
        else{
            return false;
        }
    }

    //set compass "zero" point to be at current position
    public void setZero(){
        zero = getHeading(CompassReg.BYTES);
    }

    //private method to caluclate heading based on different registers
    private int getHeading(CompassReg type) {
        setMeasurementMode();
        switch (type) {
            case BYTES:
                return getHeading(CompassReg.HIGH_BYTE) + getHeading(CompassReg.LOW_BYTE);
            case HIGH_BYTE:
                setMode(CompassReg.HIGH_BYTE);
                byte[] highByte = readBuffer();
                return (int)highByte[0];
            case LOW_BYTE:
                setMode(CompassReg.LOW_BYTE);
                byte[] lowByte = readBuffer();
                return (int)lowByte[0];
            case TWO_DEGREE_HEADING:
                setMode(CompassReg.TWO_DEGREE_HEADING);
                byte[] twoDegree = readBuffer();
                setMode(CompassReg.ONE_DEGREE_ADDER);
                byte[] oneDegree = readBuffer();
                return (twoDegree[0] * 2) + oneDegree[0];
        }
        return 0;
    }

    //I2c Interaction Methods
    private void setMode(CompassReg reg){
        switch(reg){
            case MEASUREMENT_MODE:
                writeMode(MEASUREMENT_MODE);
                break;
            case CALIBRATION_MODE:
                writeMode(CALIBRATION_MODE);
                break;
            case TWO_DEGREE_HEADING:
                enableRead(TWO_DEGREE_HEADING);
                break;
            case ONE_DEGREE_ADDER:
                enableRead(ONE_DEGREE_ADDER);
                break;
            case LOW_BYTE:
                enableRead(LOW_BYTE);
                break;
            case HIGH_BYTE:
                enableRead(HIGH_BYTE);
                break;
            case MODE_CONTROL:
                enableRead(MODE_CONTROL);
                break;
        }

    }


    //enable device to be in read mode
    private void enableRead(int memAddress){
        enableI2cReadMode(DEVICE_ADDRESS, memAddress, 3);
        setI2cPortActionFlag();
        writeI2cCacheToController();
        readI2cCacheFromController();
    }

    //get information from I2c bus
    private byte[] readBuffer(){
        getI2cReadCacheLock();
        return getCopyOfReadBuffer();
    }

    //used only to set calibration or measurement mode
    private void writeMode(byte memAddress) {
        byte[] data = {3, DEVICE_ADDRESS, MODE_CONTROL, memAddress};
        enableI2cWriteMode(DEVICE_ADDRESS, MODE_CONTROL, 3);
        setI2cPortActionFlag();
        copyBufferIntoWriteBuffer(data);
        writeI2cCacheToController();
    }

}
