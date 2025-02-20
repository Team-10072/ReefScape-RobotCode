package frc.robot;

// import frc.robot.Constants;
// import 

public class MotorTempTooHigh extends RuntimeException {
    public MotorTempTooHigh(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
    public MotorTempTooHigh(String errorMessage) {
        super(errorMessage);
    }
}