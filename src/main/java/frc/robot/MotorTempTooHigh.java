package frc.robot;

// import frc.robot.Constants;
// import 
/**
 * A custom runtime exception thrown whenever a motor's temperature gets too high
 */
public class MotorTempTooHigh extends RuntimeException {
    public MotorTempTooHigh(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
    public MotorTempTooHigh(String errorMessage) {
        super(errorMessage);
    }
}