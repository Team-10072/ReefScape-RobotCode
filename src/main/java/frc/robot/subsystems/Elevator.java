package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import frc.robot.Constants;

public class Elevator {
    private SparkMax theMotor = new SparkMax(Constants.DriveConstants.kElevatorMotorCanId, MotorType.kBrushless);

    public void moveTheMotor(double speed) {
        theMotor.set(speed);
    }
    public void checkElevatorMotor() {
        double position = theMotor.getAbsoluteEncoder().getPosition();
        System.out.println(position);
        if (position >= 0 || position <= 0) {
            theMotor.set(0);
        }
    }
    public void zeroElevator(boolean areYouSure, boolean areYouReallySure) {
        if (areYouSure && areYouReallySure) {
            
        }
    }
}
