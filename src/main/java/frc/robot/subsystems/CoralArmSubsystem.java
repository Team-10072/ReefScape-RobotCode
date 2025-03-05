package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.MotorTempTooHigh;

public class CoralArmSubsystem extends SubsystemBase {
    private final SparkMax ArmMotor = new SparkMax(Constants.DriveConstants.kArmMotorCanId, MotorType.kBrushless);
    private final RelativeEncoder ArmEncoder = ArmMotor.getAlternateEncoder();
    private final SparkMax IntakeMotor = new SparkMax(Constants.DriveConstants.kIntakeMotorCanId, MotorType.kBrushless);
    private final SparkMax IntakeMotor2 = new SparkMax(Constants.DriveConstants.kIntakeMotor2CanId, MotorType.kBrushless);
    private final SparkMax TwistMotor = new SparkMax(Constants.DriveConstants.kTwistMotorCanId, MotorType.kBrushless);

    private double timeAtStartIntake = 0.0;
    private boolean isIntakeRunning = false;
    private boolean isArmUp = false;
    
    public void rollIntake() {
        if (!isIntakeRunning) {
            timeAtStartIntake = System.currentTimeMillis();
            isIntakeRunning = true;
            IntakeMotor.set(0.5);
            IntakeMotor2.set(-0.5);
        } else {
            if (System.currentTimeMillis() - timeAtStartIntake > 2000) {
                IntakeMotor.set(0.0);
                IntakeMotor2.set(0.0);
                isIntakeRunning = false;
            }
        }
    }
    public void checkOnMotors() {
        if (IntakeMotor.getMotorTemperature() > Constants.NeoMotorConstants.kAcceptableMotorTemp) {
            IntakeMotor.set(0.0);
            throw new MotorTempTooHigh("The Coral Intake Motor is too hot!");
        }
        if (IntakeMotor2.getMotorTemperature() > Constants.NeoMotorConstants.kAcceptableMotorTemp) {
            IntakeMotor2.set(0.0);
            throw new MotorTempTooHigh("The Coral Intake Motor 2 is too hot!");
        }
        if (ArmMotor.getMotorTemperature() > Constants.NeoMotorConstants.kAcceptableMotorTemp) {
            ArmMotor.set(0.0);
            throw new MotorTempTooHigh("The Coral Arm Motor is too hot!");
        }
    }
    public void changeArmPosition() {
        changeArmPosition(!isArmUp);
    }
    public void changeArmPosition(boolean armShouldGoUp) {
        if (armShouldGoUp == isArmUp) {
            return;
        }
        if (!armShouldGoUp) {
            if (ArmEncoder.getPosition() < Constants.DriveConstants.kCoralArmMaxMotorAngle) {
                ArmMotor.set(0.25);
            } else {
                ArmMotor.set(0.0);
                isArmUp = false;
            }
        } else {
            if (ArmEncoder.getPosition() > 0.0) {
                ArmMotor.set(-0.25);
            } else {
                ArmMotor.set(0.0);
                isArmUp = true;
            }
        }
    }
    public void twistIntake(double speed) {
        TwistMotor.set(speed);
    }

}
