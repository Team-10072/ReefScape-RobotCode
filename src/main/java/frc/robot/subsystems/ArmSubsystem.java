package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ArmSubsystem extends SubsystemBase {
    private final SparkMax ArmMotor = new SparkMax(Constants.DriveConstants.kArmMotorCanId, MotorType.kBrushless);
    
}
