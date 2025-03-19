package frc.robot;

// import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import java.util.List;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
//import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
// import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
//import edu.wpi.first.wpilibj2.command.button.Trigger;
//import frc.robot.Commands.IntakeCommands;
import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.subsystems.AlgaeIntakeRollerSubsystem;
import frc.robot.subsystems.CoralArmSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;

/*
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */

public class RobotContainer {
  // The robot's subsystems
  private final DriveSubsystem m_robotDrive = new DriveSubsystem();
  private final CoralArmSubsystem m_armMovement = new CoralArmSubsystem();
  private final ElevatorSubsystem m_ElevatorSubsystem = new ElevatorSubsystem();
  private final AlgaeIntakeRollerSubsystem m_algaeIntake = new AlgaeIntakeRollerSubsystem();

  

 // The driver's controller
    GenericHID m_FlightStick = new GenericHID(OIConstants.kDriverControllerPort);
    GenericHID m_secondaryController = new GenericHID(OIConstants.kSecondControllerPort);


    //Button mappings

    //Deprecate\/
    JoystickButton Coral_Trigger = new JoystickButton(m_FlightStick,1);
    

    //Preset coral arm angle
    JoystickButton Button_Preset_A = new JoystickButton(m_FlightStick,7);
    JoystickButton Button_Preset_B = new JoystickButton(m_FlightStick,9);
    JoystickButton Button_Preset_C = new JoystickButton(m_FlightStick,11);

    //Elevator
    JoystickButton Button_Elevator_A = new JoystickButton(m_FlightStick, 8);
    JoystickButton Button_Elevator_B = new JoystickButton(m_FlightStick, 10);
    JoystickButton Button_Elevator_C = new JoystickButton(m_FlightStick,12);


    //Algae intake
    JoystickButton Algae_Preset_Intake = new JoystickButton(m_FlightStick, 5);
    JoystickButton Algae_Preset_Output = new JoystickButton(m_FlightStick,6);
    JoystickButton Algae_Preset_Off = new JoystickButton(m_FlightStick,12);


    //Deprecate
    JoystickButton Coral_intake_Off = new JoystickButton(m_FlightStick, 2);



  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */

  public RobotContainer() {
    // Configure the button bindings
    configureBindings();
  }

 public void configureBindings() {

    //Consider Adding Modifier Value in case the Driver need to tweak the height on the fly - Use the Slider Bar 
  
    //Preset elevator positions -> Need to move into the subsystem later
    int el_position_A = 18;
    int el_position_B = 15;
    int el_position_C = 0;

    //Preset arm postions
    int arm_angle_A = -21;
    int arm_angle_B = -14;
    int arm_angle_C = 0;

    //Preset algae position
    int algae_angle_A = 1;
    int algae_angle_B = 5;

    // int algae_angle_B = 1;


    
    //these are basic buton mappings for the driver. many of these operations should have some level of logic applied 
    //through a proper command method, but for now and for testing, this simplified code will have to work

    //Arm Angle preset Commands temporary for Comp!
    Button_Preset_A.onTrue(new InstantCommand(() -> m_armMovement.basicRotation(arm_angle_A), m_armMovement));
    Button_Preset_B.onTrue(new InstantCommand(() -> m_armMovement.basicRotation(arm_angle_B), m_armMovement));
    Button_Preset_C.onTrue(new InstantCommand(() -> m_armMovement.basicRotation(arm_angle_C), m_armMovement));


    //Elevator preset height Commands
    Button_Elevator_A.onTrue(new InstantCommand(() -> m_ElevatorSubsystem.basicRaise(el_position_A)));
    Button_Elevator_B.onTrue(new InstantCommand(() -> m_ElevatorSubsystem.basicRaise(el_position_B)));
    Button_Elevator_C.onTrue(new InstantCommand(() -> m_ElevatorSubsystem.basicRaise(el_position_C)));



    m_armMovement.setDefaultCommand(new RunCommand(() -> m_armMovement.simple_roller(m_FlightStick.getRawButton(4), 
              m_FlightStick.getRawButton(6)),
            m_armMovement));

    m_algaeIntake.setDefaultCommand(new RunCommand(() -> m_algaeIntake.set_angle(m_FlightStick.getRawAxis(3), 
            m_FlightStick.getRawButton(1), m_FlightStick.getRawButton(2)),
          m_algaeIntake));
          
      // Configure default commands
  m_robotDrive.setDefaultCommand(
    // The left stick controls translation of the robot.
    // Turning is controlled by the X axis of the right stick.
    new RunCommand(
        () -> m_robotDrive.drive(
            -MathUtil.applyDeadband(m_FlightStick.getRawAxis(1), OIConstants.kDriveDeadband),
            -MathUtil.applyDeadband(m_FlightStick.getRawAxis(0), OIConstants.kDriveDeadband),
            -MathUtil.applyDeadband(m_FlightStick.getRawAxis(2), OIConstants.kDriveDeadband),
            true),
        m_robotDrive));

  m_armMovement.setDefaultCommand(
      new RunCommand(() -> 
        m_armMovement.basicRotation(arm_angle_C + m_secondaryController.getRawAxis(0)))
      , m_armMovement);

 }

 


  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link edu.wpi.first.wpilibj.GenericHID} or one of its
   * subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then calling
   * passing it to a
   * {@link JoystickButton}.
   */

   /** 
  private void configureButtonBindings() {
    new JoystickButton(m_driverController, Button.kR1.value)
        .whileTrue(new RunCommand(
            () -> m_robotDrive.setX(),
            m_robotDrive));
  }
*/
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // Create config for trajectory
    TrajectoryConfig config = new TrajectoryConfig(
        AutoConstants.kMaxSpeedMetersPerSecond,
        AutoConstants.kMaxAccelerationMetersPerSecondSquared)
        // Add kinematics to ensure max speed is actually obeyed
        .setKinematics(DriveConstants.kDriveKinematics);

    // An example trajectory to follow. All units in meters.
    Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(
        // Start at the origin facing the +X direction
        new Pose2d(0, 0, new Rotation2d(0)),
        // Pass through these two interior waypoints, making an 's' curve path
        List.of(new Translation2d(1, 1), new Translation2d(2, -1)),
        // End 3 meters straight ahead of where we started, facing forward
        new Pose2d(3, 0, new Rotation2d(0)),
        config);

    var thetaController = new ProfiledPIDController(
        AutoConstants.kPThetaController, 0, 0, AutoConstants.kThetaControllerConstraints);
    thetaController.enableContinuousInput(-Math.PI, Math.PI);

    SwerveControllerCommand swerveControllerCommand = new SwerveControllerCommand(
        exampleTrajectory,
        m_robotDrive::getPose, // Functional interface to feed supplier
        DriveConstants.kDriveKinematics,

        // Position controllers
        new PIDController(AutoConstants.kPXController, 0, 0),
        new PIDController(AutoConstants.kPYController, 0, 0),
        thetaController,
        m_robotDrive::setModuleStates,
        m_robotDrive);

    // Reset odometry to the starting pose of the trajectory.
    m_robotDrive.resetOdometry(exampleTrajectory.getInitialPose());

    // Run path following command, then stop at the end.
    return swerveControllerCommand.andThen(() -> m_robotDrive.drive(0, 0, 0, false));
  }
}
