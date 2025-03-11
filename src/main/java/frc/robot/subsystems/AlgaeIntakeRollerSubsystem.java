package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.Constants.AlgaeSystemConstants;
import frc.robot.Constants.CoralSystemConstants;

import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class AlgaeIntakeRollerSubsystem extends SubsystemBase {
    
    private final SparkMax algaeIntakeMotor = new SparkMax(AlgaeSystemConstants.kAlgaeIntakeMotorCanId, MotorType.kBrushless);
    private final SparkMax algaeArmMotor = new SparkMax(AlgaeSystemConstants.kAlgaeArmMotorCanId, MotorType.kBrushless);
    
    // private final RelativeEncoder algaeArmEncoder = algaeArmMotor.getAlternateEncoder();
    private final SparkClosedLoopController arm_ClosedLoop = algaeArmMotor.getClosedLoopController();

    private double timeAtStartIntake = 0.0;
    private boolean isIntakeRunning = false;
    private boolean isArmUp = false;

    private double goalArmPosition = 0.0;
    

    public void set_angle(int angle){
        arm_ClosedLoop.setReference(angle, ControlType.kPosition);
    }

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
          //  throw new MotorTempTooHigh("The Algae Roller Intake Motor is too hot!");
        }
        if (algaeArmMotor.getMotorTemperature() > Constants.NeoMotorConstants.kAcceptableMotorTemp) {
            algaeArmMotor.set(0.0);
          //  throw new MotorTempTooHigh("The Algae Roller Arm Motor is too hot!");
        }
    }

    public void set_intake_angle(int angle){

    }

    public void changeArmPosition() {
        changeArmPosition(!isArmUp);
    }
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
    }
}
