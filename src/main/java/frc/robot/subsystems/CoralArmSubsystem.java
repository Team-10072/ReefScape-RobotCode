package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;

import org.ejml.dense.row.decomposition.eig.SymmetricQRAlgorithmDecomposition_DDRM;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.CoralSystemConstants;

import frc.robot.MotorTempTooHigh;

public class CoralArmSubsystem extends SubsystemBase {
    private final SparkMax ArmMotor = new SparkMax(CoralSystemConstants.kArmRotationCanId, MotorType.kBrushless);
    private final RelativeEncoder ArmEncoder = ArmMotor.getAlternateEncoder();
    private final SparkMax IntakeMotor = new SparkMax(Constants.CoralSystemConstants.k_L_IntakeMotorCanId, MotorType.kBrushless);
    private final SparkMax IntakeMotor2 = new SparkMax(Constants.CoralSystemConstants.k_R_IntakeMotorCanId, MotorType.kBrushless);
    private final SparkMax TwistMotor = new SparkMax(Constants.CoralSystemConstants.kTwistMotorCanId, MotorType.kBrushless);

    private double timeAtStartIntake = 0.0;
    private boolean isIntakeRunning = false;
    private boolean isArmUp = false;
    

    public CoralArmSubsystem() {
        
    }


    public void basicRotation(double setPoint) {

        arm_ClosedLoop.setReference(setPoint, ControlType.kPosition);

    }

    public void basicSetPoints(char requestedPreset){
        

        double preset_A = 10;
        double preset_B = 20;
        double preset_C = 30;


        switch (requestedPreset) {
            case 'A':
                
                break;

            case 'B':

                break;

            case 'C':

                break;    

            default:
                break;
        }

    }



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


    //Great Fore thought here!! I might would change the name to something like tempCheck or thermalSafety since check on motors can mean lots of things.
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
            if (ArmEncoder.getPosition() < CoralSystemConstants.kCoralArmMaxMotorAngle) {
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
        }*/
    }
    public void twistIntake(double speed) {
        TwistMotor.set(speed);
    }

}
