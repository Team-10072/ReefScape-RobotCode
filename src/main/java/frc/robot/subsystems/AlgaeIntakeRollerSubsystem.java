package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.Constants.algaeSystemConstants;


// import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.MotorTempTooHigh;

public class AlgaeIntakeRollerSubsystem extends SubsystemBase {
    
    private final SparkMax algaeIntakeMotor = new SparkMax(algaeSystemConstants.kAlgaeIntakeMotorCanId, MotorType.kBrushless);
    // private final AbsoluteEncoder algaeIntakeEncoder = algaeIntakeMotor.getAbsoluteEncoder();
    private final SparkMax algaeArmMotor = new SparkMax(algaeSystemConstants.kAlgaeArmMotorCanId, MotorType.kBrushless);
    
    private final RelativeEncoder algaeArmEncoder = algaeArmMotor.getAlternateEncoder();
    
    private double timeAtStartIntake = 0.0;
    private boolean isIntakeRunning = false;
    private boolean isArmUp = false;
    
    public void rollIntake() {
        if (!isIntakeRunning) {
            timeAtStartIntake = System.currentTimeMillis();
            isIntakeRunning = true;
            algaeIntakeMotor.set(0.5);
        } else {
            if (System.currentTimeMillis() - timeAtStartIntake > 2000) {
                algaeIntakeMotor.set(0.0);
                isIntakeRunning = false;
            }
        }
    }
    public void checkOnMotors() {
        if (algaeIntakeMotor.getMotorTemperature() > Constants.NeoMotorConstants.kAcceptableMotorTemp) {
            algaeIntakeMotor.set(0.0);
            throw new MotorTempTooHigh("The Algae Roller Intake Motor is too hot!");
        }
        if (algaeArmMotor.getMotorTemperature() > Constants.NeoMotorConstants.kAcceptableMotorTemp) {
            algaeArmMotor.set(0.0);
            throw new MotorTempTooHigh("The Algae Roller Arm Motor is too hot!");
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
            if (algaeArmEncoder.getPosition() < algaeSystemConstants.kAlgaeArmMaxMotorAngle) {
                algaeArmMotor.set(0.25);
            } else {
                algaeArmMotor.set(0.0);
                isArmUp = false;
            }
        } else {
            if (algaeArmEncoder.getPosition() > 0.0) {
                algaeArmMotor.set(-0.25);
            } else {
                algaeArmMotor.set(0.0);
                isArmUp = true;
            }
        }
    }
}
