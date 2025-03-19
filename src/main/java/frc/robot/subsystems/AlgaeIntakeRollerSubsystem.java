package frc.robot.subsystems;

import frc.robot.Constants.*;
import com.revrobotics.spark.*;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.MotorTempTooHigh;

public class AlgaeIntakeRollerSubsystem extends SubsystemBase {
    
    private final SparkMax algaeIntakeMotor = new SparkMax(AlgaeSystemConstants.kAlgaeIntakeMotorCanId, MotorType.kBrushless);
    private final SparkMax algaeArmMotor = new SparkMax(AlgaeSystemConstants.kAlgaeArmMotorCanId, MotorType.kBrushless);
    
    private final SparkClosedLoopController arm_ClosedLoop = algaeArmMotor.getClosedLoopController();

    private double timeAtStartIntake = 0.0;
    private boolean isIntakeRunning = false;
    private boolean isArmUp = false;

    private double goalArmPosition = 0.0;
    

    public void algae_default_method (double angle, boolean intake_button, boolean output_button){
        set_angle(angle);
        algae_Rollers(0.5, intake_button, output_button);
    }



    public void set_angle(double angle){
        double target = (angle + 1)*-2;
        arm_ClosedLoop.setReference(target, ControlType.kPosition);
    }


//Stand in roll method
public void algae_Rollers(double speed, boolean intake, boolean output){

    if (intake){
        algaeIntakeMotor.set(speed);
    }else if (output){
        algaeIntakeMotor.set(speed);
    }else {
        algaeIntakeMotor.set(speed);
    }

    algaeIntakeMotor.set(speed);
}






//Elis Method - broke the command scheduler - need to investigate
    public void rollIntake(double speed) {
        if (!isIntakeRunning) {
            timeAtStartIntake = System.currentTimeMillis();
            isIntakeRunning = true;
            algaeIntakeMotor.set(speed);
        } else {
            if (System.currentTimeMillis() - timeAtStartIntake > 2000) {
                algaeIntakeMotor.set(0.0);
                isIntakeRunning = false;
            }
        }
    }
    public void rollOutput(double speed) {
        if (!isIntakeRunning) {
            timeAtStartIntake = System.currentTimeMillis();
            isIntakeRunning = true;
            algaeIntakeMotor.set(/*speed:*/-speed);
        } else {
            if (System.currentTimeMillis() - timeAtStartIntake > 2000) {
                algaeIntakeMotor.set(0);
                isIntakeRunning = false;
            }
        }
    }
    public void checkOnMotors() {
        if (algaeIntakeMotor.getMotorTemperature() > NeoMotorConstants.kAcceptableMotorTemp) {
            algaeIntakeMotor.set(0.0);
            throw new MotorTempTooHigh("The Algae Roller Intake Motor is too hot!");
        }
        if (algaeArmMotor.getMotorTemperature() > NeoMotorConstants.kAcceptableMotorTemp) {
            algaeArmMotor.set(0.0);
            throw new MotorTempTooHigh("The Algae Roller Arm Motor is too hot!");
        }
    }


    
    //Eli's Version
    public void changeArmPosition() {
        changeArmPosition(!isArmUp);
    }
    /**
     * @deprecated
     */
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

    //Addisons version of 'Arm Angle'

    public void a_Arm_angle(double angle){
        
    }

}
