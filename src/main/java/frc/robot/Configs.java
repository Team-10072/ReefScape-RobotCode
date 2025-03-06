//What is this page for? You know those neat little motor controllers that....control the motors? We can set our configs through the REV Hardware client.
// BUT that takes time, and we have to change one setting at a time, and cant be done remotely or on the field in an emergency. SO instead, we can do it in our code!
//This file IS the configs for the spark maxes IE. Telling the spark how to behave and what its inputs and outputs are!


//How to use?
// 1. *If applicable* Create a relevent class to stick everything in IE Group configs together IF it makes sense
// 2. Declare a Spark Max Config Object (Looks similar to how you declare the motor controller in you subystems, right?)
// 3. place your configs in the brackets of static{ }

// What do i even configure them to do??
// Well, that depends on what you want them to do and why
// You may want to configure the absolute encoder,PID Settings, Closed loop settings, Safety Cut offs and a bajillion other things.

//When in doubt ask you self "What does this controller need to do" and start there. Search for examples below, and check the java docs!

package frc.robot;

// import com.revrobotics.sim.SparkLimitSwitchSim;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

// import frc.robot.Constants;


public final class Configs {
    public static final class MAXSwerveModule {
        public static final SparkMaxConfig drivingConfig = new SparkMaxConfig();
        public static final SparkMaxConfig turningConfig = new SparkMaxConfig();

        static {
            // Use module constants to calculate conversion factors and feed forward gain.
            double drivingFactor = Constants.ModuleConstants.kWheelDiameterMeters * Math.PI
                    / Constants.ModuleConstants.kDrivingMotorReduction;
            double turningFactor = 2 * Math.PI;
            double drivingVelocityFeedForward = 1 / Constants.ModuleConstants.kDriveWheelFreeSpeedRps;

            drivingConfig
                    .idleMode(IdleMode.kBrake)
                    .smartCurrentLimit(50);
            drivingConfig.encoder
                    .positionConversionFactor(drivingFactor) // meters
                    .velocityConversionFactor(drivingFactor / 60.0); // meters per second
            drivingConfig.closedLoop
                    .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
                    // These are example gains you may need to them for your own robot!
                    .pid(0.04, 0, 0)
                    .velocityFF(drivingVelocityFeedForward)
                    .outputRange(-1, 1);

            turningConfig
                    .idleMode(IdleMode.kBrake)
                    .smartCurrentLimit(20);
            turningConfig.absoluteEncoder
                    // Invert the turning encoder, since the output shaft rotates in the opposite
                    // direction of the steering motor in the MAXSwerve Module.
                    .inverted(true)
                    .positionConversionFactor(turningFactor) // radians
                    .velocityConversionFactor(turningFactor / 60.0); // radians per second
            turningConfig.closedLoop
                    .feedbackSensor(FeedbackSensor.kAbsoluteEncoder)
                    // These are example gains you may need to them for your own robot!
                    .pid(1, 0, 0)
                    .outputRange(-1, 1)
                    // Enable PID wrap around for the turning motor. This will allow the PID
                    // controller to go through 0 to get to the setpoint i.e. going from 350 degrees
                    // to 10 degrees will go through 0 rather than the other direction which is a
                    // longer route.
                    .positionWrappingEnabled(true)
                    .positionWrappingInputRange(0, turningFactor);
        }
    }


    public static final class armConfigs {
        
        public static final SparkMaxConfig armRotationConfig = new SparkMaxConfig();
        public static final SparkMaxConfig L_coralMotorConfig = new SparkMaxConfig();
        public static final SparkMaxConfig R_coralMotorConfig = new SparkMaxConfig();
        
        static {
        armRotationConfig
                .idleMode(IdleMode.kBrake)
                .smartCurrentLimit(80);

        armRotationConfig.absoluteEncoder
                .positionConversionFactor(Math.PI);

        armRotationConfig.closedLoop
                .feedbackSensor(FeedbackSensor.kAbsoluteEncoder)
                .pid(1,0,0)
                .outputRange(-1,1)
                .positionWrappingEnabled(false);

        L_coralMotorConfig
                .idleMode(IdleMode.kBrake)
                .smartCurrentLimit(80);

        R_coralMotorConfig
                .idleMode(IdleMode.kBrake)
                .follow(Constants.AlgaeSystemConstants.kAlgaeArmMotorCanId);
                //Ensure that this Motor is inverted during the setup phase as it is just going to follow the other one. 
        }

        //The Algae Configs go here :)


    }
}
