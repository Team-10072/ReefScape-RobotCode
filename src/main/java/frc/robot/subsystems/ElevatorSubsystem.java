package frc.robot.subsystems;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.RelativeEncoder;
import frc.robot.Constants;

public class ElevatorSubsystem {
    private SparkMax theMotor = new SparkMax(Constants.DriveConstants.kElevatorMotorCanId, MotorType.kBrushless);
    private AbsoluteEncoder absoluteEncoder = theMotor.getAbsoluteEncoder();
    private RelativeEncoder relativeEncoder = theMotor.getEncoder();

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
    // NEXT METHOD IS UNFNISHED - do not use until fixed 
    public void zeroElevator(boolean areYouSure, boolean areYouReallySure) {
        if (areYouSure && areYouReallySure) {

        }
    }
}
