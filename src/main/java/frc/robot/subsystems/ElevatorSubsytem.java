package frc.robot.subsystems;

import java.io.ObjectInputFilter.Config;
import java.lang.module.Configuration;

import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkMaxAlternateEncoder;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.ElevatorConstants;





public class ElevatorSubsytem extends SubsystemBase{


    public int[] elevator_PID_Values = {1, 0, 0};

    private SparkMax Elevator_Z = new SparkMax(ElevatorConstants.elevator_z_can, MotorType.kBrushed);
    private SparkMaxConfig config = new SparkMaxConfig();
        
        

    private SparkClosedLoopController Elevator_PID_Controller;
    private SparkMax RelativeEncoder;
    private SparkMaxAlternateEncoder Elevator_Height;






    public ElevatorSubsytem() {

        //This will Set the Base Configuration for the Spark max
        //This occurs everytime the ElevatorSubsystem is called!
        //If you modify any of the values during runtime then cal the constructor again or just re run the .configure method, you will reset the Motorcontrollers Config
        //It is not Recommended to do that unless for a Very Good Reason such as error Correction.

        config
            .inverted(true)
            .idleMode(IdleMode.kBrake);
        config.encoder
            .positionConversionFactor(1000)
            .velocityConversionFactor(1000);
        config.closedLoop
            .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
            .pid(1.0, 0.0, 0.0);

        Elevator_Z.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        
    
        

    };
    
}
