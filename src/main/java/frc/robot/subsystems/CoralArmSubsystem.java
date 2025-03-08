package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;

// import org.ejml.dense.row.decomposition.eig.SymmetricQRAlgorithmDecomposition_DDRM;

// import com.revrobotics.AbsoluteEncoder;
// import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.MotorTempTooHigh;
import frc.robot.Constants.CoralSystemConstants;

public class CoralArmSubsystem extends SubsystemBase {

    private final SparkMax ArmMotor = new SparkMax(CoralSystemConstants.kArmRotationCanId, MotorType.kBrushless);
    private final SparkClosedLoopController arm_ClosedLoop = ArmMotor.getClosedLoopController();


    private final SparkMax IntakeMotor = new SparkMax(CoralSystemConstants.k_L_IntakeMotorCanId, MotorType.kBrushless);
    private final SparkMax IntakeMotor2 = new SparkMax(CoralSystemConstants.k_R_IntakeMotorCanId, MotorType.kBrushless);

    private double timeAtStartIntake = 0.0; // is there a reason were using the a timer in this instance?

    private boolean isIntakeRunning = false;
    private boolean isArmUp = false;
    
    private double goalArmPosition = 0.0;

    public CoralArmSubsystem() {
        
    }


    public void basicRotation(double setPoint) {
        arm_ClosedLoop.setReference(setPoint, ControlType.kPosition);
    }

    public void basicSetPoints(char requestedPreset){
        

        double preset_A = 10.0;
        double preset_B = 20.0;
        double preset_C = 30.0;


        switch (requestedPreset) {
            case 'A':
                goalArmPosition = preset_A;
                break;
            case 'B':
                goalArmPosition = preset_B;
                break;
            case 'C':
                goalArmPosition = preset_C;
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
    public void tempCheck() {
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

    //The Code below seems to just set a desired speed for the arms motor, this could work, but i would recommend Changing this out for closed loop control
    //Closed Loop Control Will Offer more precision and error correction. 
    //This motor/Axis will hav an absolute encoder installed, so we can use that to Verify the positions
    public void changeArmPosition(boolean armShouldGoUp) {
        if (armShouldGoUp == isArmUp) {
            return;
        }
        if (!armShouldGoUp) {
            goalArmPosition = 0.0;
        } else {
            goalArmPosition = CoralSystemConstants.kCoralArmMaxMotorAngle;
        }
        arm_ClosedLoop.setReference(goalArmPosition, ControlType.kPosition);
        
        /*if (!armShouldGoUp) {
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
 
}
