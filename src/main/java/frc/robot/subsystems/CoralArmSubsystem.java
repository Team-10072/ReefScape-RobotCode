package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.*;
import com.revrobotics.spark.SparkBase.ControlType;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.MotorTempTooHigh;
import frc.robot.Constants.CoralSystemConstants;
import frc.robot.Constants.OIConstants;
//import frc.robot.Map;

// import frc.robot.MotorTempTooHigh;

public class CoralArmSubsystem extends SubsystemBase {

    private final SparkMax ArmMotor = new SparkMax(CoralSystemConstants.kArmRotationCanId, MotorType.kBrushless);
    private final SparkClosedLoopController arm_ClosedLoop = ArmMotor.getClosedLoopController();
    //private final SparkMax IntakeMotor = new SparkMax(CoralSystemConstants.k_L_IntakeMotorCanId, MotorType.kBrushless);
    // private final SparkClosedLoopController intakeloop = new SparkClosedLoopController(IntakeMotor);
    private final SparkMax IntakeMotor = new SparkMax(CoralSystemConstants.k_R_IntakeMotorCanId, MotorType.kBrushless);

    private double timeAtStartIntake = 0.0;
    private boolean isIntakeRunning = false;
    private double goalArmPosition = 0.0;

    public CoralArmSubsystem(){}

    /**
     * @deprecated
     */
    public int initilize_arm_angle(){
        return 0;
    }


    public void basicRotation(double setPoint, double modifier) {
        arm_ClosedLoop.setReference(setPoint + modifier, ControlType.kPosition);
    }

    public void basicRotation(double setPoint) {

        // double change = Map.map(controller.getRawAxis(3), -1.0, 1.0, -3.0, 3.0);
        // if (setPoint + change > 16) {
        //     arm_ClosedLoop.setReference(16, ControlType.kPosition);
        // }
        arm_ClosedLoop.setReference(setPoint/* + change*/, ControlType.kPosition);
    }

    public void basicRotation (double setPoint, GenericHID controller) {
        arm_ClosedLoop.setReference(setPoint + controller.getRawAxis(0), ControlType.kPosition);
    }


    



    public void basicSetPoints(int requestedPreset) {    
        switch (requestedPreset) {
            case '1':
                goalArmPosition = Constants.LevelConstantsCoral.kLevel1RotationCoral;
                break;
            case '2':
                goalArmPosition = Constants.LevelConstantsCoral.kLevel2RotationCoral;
                break;
            case '3':
                goalArmPosition = Constants.LevelConstantsCoral.kLevel3RotationCoral;
                break;    
            default:
                break;
        }
    }

    public void simple_roller(boolean button_Intake, boolean button_Eject){
        if (button_Intake){
            IntakeMotor.set(0.5);
        }else if(button_Eject){
             IntakeMotor.set(/*speed:*/-0.5);
        }
        else{
             IntakeMotor.set(0);
        }
    }



    public void rollIntake(double speed) {
        if (!isIntakeRunning) {
            timeAtStartIntake = System.currentTimeMillis();
            isIntakeRunning = true;
            IntakeMotor.set(-speed);
        } else {
            if (System.currentTimeMillis() - timeAtStartIntake > 2000) {
                IntakeMotor.set(0.0);
                isIntakeRunning = false;
            }
        }
    }

    public void rollOutput(double speed) {
        rollIntake(/*speed:*/-speed);
    }

    //Great Fore thought here!! I might would change the name to something like tempCheck or thermalSafety since check on motors can mean lots of things.
    public void tempCheck() {
    
        if (IntakeMotor.getMotorTemperature() > Constants.NeoMotorConstants.kAcceptableMotorTemp) {
            IntakeMotor.set(0.0);
            throw new MotorTempTooHigh("The Coral Intake Motor 2 is too hot!");
        }
        if (ArmMotor.getMotorTemperature() > Constants.NeoMotorConstants.kAcceptableMotorTemp) {
            ArmMotor.set(0.0);
            throw new MotorTempTooHigh("The Coral Arm Motor is too hot!");
        }
    }
    
    //The Code below seems to just set a desired speed for the arms motor, this could work, but i would recommend Changing this out for closed loop control
    //Closed Loop Control Will Offer more precision and error correction. 
    //This motor/Axis will hav an absolute encoder installed, so we can use that to Verify the positions
    public void changeArmPosition(double toWhere) {
        if (toWhere > CoralSystemConstants.kCoralArmMaxMotorAngle) {
            goalArmPosition = CoralSystemConstants.kCoralArmMaxMotorAngle;
        } else {
            goalArmPosition = toWhere;
        }
        arm_ClosedLoop.setReference(goalArmPosition, ControlType.kPosition);
    }
}
