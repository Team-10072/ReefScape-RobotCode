package frc.robot.subsystems;

import com.revrobotics.AbsoluteEncoder;
// import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.util.sendable.SendableRegistry;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * This is the subsystem designed to operate the Elevator.
 * It is instanted by calling new ElevatorSubsystem();
 * 
 */

public class ElevatorSubsystem extends SubsystemBase {
    private final SparkMax theMotor = new SparkMax(Constants.DriveConstants.kElevatorMotorCanId, MotorType.kBrushless);
    private AbsoluteEncoder absoluteEncoder = theMotor.getAbsoluteEncoder();
    // private RelativeEncoder relativeEncoder = theMotor.getEncoder();

    private double zerodPos = 0.0;

    private boolean endstopTriggered = false;

    public void moveTheMotor(double speed) {
        theMotor.set(speed);
    }
    public void checkElevatorMotor() {
        double position = getElevatorPosition();
        // SendableRegistry.add(new Sendable(), "Elevator Motor");
        checkEndStop();
        if (position >= 0 || position <= 0) {
            theMotor.set(0);
        }
    }
    public double getElevatorPosition() {
        double position = absoluteEncoder.getPosition();
        position += zerodPos;
        return position;
    }
    public void zeroElevator(boolean areYouSure, boolean areYouReallySure) {
        if (areYouSure && areYouReallySure) {
            zerodPos = absoluteEncoder.getPosition();
        }
    }
    public void checkEndStop() {
        if (!endstopTriggered) {
            theMotor.set(0);
            // theMotor.
        } else {
            // Kalm.
        }
    }
}