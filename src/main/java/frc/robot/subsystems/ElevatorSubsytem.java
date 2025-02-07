package frc.robot.subsystems;

import java.io.ObjectInputFilter.Config;
import java.lang.module.Configuration;

import com.revrobotics.spark.SparkAbsoluteEncoder;
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

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.ElevatorConstants;





public class ElevatorSubsytem extends SubsystemBase{


    //  private int[] PID_Values = {1, 0, 0};

    private SparkMax Elevator_Z = new SparkMax(ElevatorConstants.elevator_z_can, MotorType.kBrushed);
    private SparkAbsoluteEncoder Height_Encoder = Elevator_Z.getAbsoluteEncoder();
    private SparkClosedLoopController Height_CL_Controller = Elevator_Z.getClosedLoopController();

    private SparkMaxConfig config = new SparkMaxConfig();


    public double current_Height;



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

        config.signals
            .primaryEncoderPositionPeriodMs(5)
            .absoluteEncoderPositionAlwaysOn(true)
            .externalOrAltEncoderVelocityAlwaysOn(true);


        config.closedLoop
            .feedbackSensor(FeedbackSensor.kAlternateOrExternalEncoder)
            .pid(1.0, 0.0, 0.0);

        Elevator_Z.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);


        //Gets Current robot parameters/condition for calibration purposes
        current_Height = Height_Encoder.getPosition();

    }; 



    public void ElevatorSetPostion(Boolean Button1, Boolean Button2, Boolean Button3, Boolean Button4){

        current_Height = Height_Encoder.getPosition();
        double desired_position;


        //These Values still Need to Be implemented
        double position_A;
        double position_B;
        double position_C;

        // First Check Current position to make sure the position is valid
        // Then, Before running Each Command, each code block should check the End position, to make sure that the end result will not exceed the Max height limit
        // We also want to check the intended position VS Where we actually wind up after executing the move
        if (current_Height > elevator_max_height || current_Height < 0) {
            
            //Call and Error handling function here to report back current position, desired positon, Difference in numbers, Pull E-Stop
        }
        else if ((current_Height + desired_position) > elevator_max_height) {
            //call error handler
        }
        else if (Button1) {

        }
        else if (Button2) {

        }
        else if (Button3) {

        }
        else if (Button4) {
            
        }
    
    
    };




}
