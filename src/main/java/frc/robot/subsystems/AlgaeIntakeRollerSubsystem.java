package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class AlgaeIntakeRollerSubsystem extends SubsystemBase {
    private final SparkMax algaeIntakeMotor = new SparkMax(Constants.DriveConstants.kAlgaeIntakeMotorCanId, MotorType.kBrushless);
    private final SparkMax algaeArmMotor = new SparkMax(Constants.DriveConstants.kAlgaeArmMotorCanId, MotorType.kBrushless);
    private double timeAtStartIntake = 0.0;
    private boolean isIntakeRunning = false;
    public AlgaeIntakeRollerSubsystem() {
        
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
}
