package frc.robot.subsystems;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkClosedLoopController;
// import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
// import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import edu.wpi.first.util.sendable.SendableRegistry;
//import frc.robot.MotorTempTooHigh;

import frc.robot.Constants;
import frc.robot.Constants.CoralSystemConstants;





/**
 * This is the subsystem designed to operate the Elevator.
 * It is instanted by calling new ElevatorSubsystem();
 */

public class ElevatorSubsystem extends SubsystemBase {
 
    private final SparkMax theMotor = new SparkMax(CoralSystemConstants.kElevatorMotorCanId, MotorType.kBrushless);

    private AbsoluteEncoder absoluteEncoder = theMotor.getAbsoluteEncoder();
    /**
     * The main elevator motor's closed loop controller
     */
    private final SparkClosedLoopController theMotorClosedLoopController = theMotor.getClosedLoopController();
    // private final SparkMaxConfig theMotorConfig = new SparkMaxConfig();
    /**
     * Used in zeroing the elevator motor
     */
    private double zerodPos = 0.0;
    /**
     * Says if the endstop of the elevator motor has been triggered (hardware or software)
     */
    private boolean endstopTriggered = false;

    /**
     * Makes the main elevator motor move (with some common-sense speed limits)
     * @param speed speed you want to set the motor to
     */


    public void basicRaise(double setPoint){
        theMotorClosedLoopController.setReference(setPoint, ControlType.kPosition);
    }
    public void moveTheMotor(double speed) {
        double sp = speed;
        if (speed < -0.5) {
            sp = -0.5;
        } else if (speed > 0.5) {
            sp = 0.5;
        }
        theMotor.set(sp);
    }
    /**
     * Sets the motor to go to a specific position (must be called in Periodic)
     */
    public void setTheMotorTo(double position) {
        theMotorClosedLoopController.setReference(position, ControlType.kPosition);
    }
    /**
     * Checks the main elevator motor to see if it is in unacceptable ranges or if it is too warm.
     */
    public void checkElevatorMotor() {
        double position = getElevatorPosition();
        // SendableRegistry.add(new Sendable(), "Elevator Motor");
        checkEndStop();
        double motorTemp = theMotor.getMotorTemperature();
        if (motorTemp >= Constants.NeoMotorConstants.kAcceptableMotorTemp) {
            theMotor.set(0);
           // throw new MotorTempTooHigh("Elevator Motor Temperature is too High!");
        }
        if (position > 0 || position < 580) {
            theMotor.set(0);
            endstopTriggered = true;
        }
    }
    /**
     * Gets the elevator motor's position via its absolute encoder
     * @return the motor's position as a double
     */
    public double getElevatorPosition() {
        double position = absoluteEncoder.getPosition();
        position += zerodPos;
        return position;
    }
    /**
     * Zeroes the elevator, which makes the elevator motor's current position as "0"
     * @param areYouSure makes sure you are sure
     * @param areYouReallySure makes really sure you are really sure
     */
    public void zeroElevator(boolean areYouSure, boolean areYouReallySure) {
        if (areYouSure && areYouReallySure) {
            zerodPos = absoluteEncoder.getPosition();
        }
    }
    /**
     * Checks the hardware end stop of the elevator motor
     */
    public void checkEndStop() {
        if (!endstopTriggered) {
            theMotor.set(0);
            
        } else {
            // Kalm.
        }
    }
}