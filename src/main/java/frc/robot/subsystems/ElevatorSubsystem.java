package frc.robot.subsystems;

import com.revrobotics.AbsoluteEncoder;
// import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
// import edu.wpi.first.util.sendable.SendableRegistry;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.MotorTempTooHigh;
/**
 * This is the subsystem designed to operate the Elevator.
 * It is instanted by calling new ElevatorSubsystem();
 * 
 */

public class ElevatorSubsystem extends SubsystemBase {
    /**
     * The main elevator motor, as a SparkMax
     */
    private final SparkMax theMotor = new SparkMax(Constants.DriveConstants.kElevatorMotorCanId, MotorType.kBrushless);
    /**
     * The main elevator motor's encoder
     */
    private AbsoluteEncoder absoluteEncoder = theMotor.getAbsoluteEncoder();

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
     * Checks the main elevator motor to see if it is in unacceptable ranges or if it is too warm.
     */
    public void checkElevatorMotor() {
        double position = getElevatorPosition();
        // SendableRegistry.add(new Sendable(), "Elevator Motor");
        checkEndStop();
        double motorTemp = theMotor.getMotorTemperature();
        if (motorTemp >= Constants.NeoMotorConstants.kAcceptableMotorTemp) {
            theMotor.set(0);
            throw new MotorTempTooHigh("Elevator Motor Temperature is too High!");
        }
        if (position >= 0 || position <= 0) {
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