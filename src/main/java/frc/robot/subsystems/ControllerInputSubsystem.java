package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.GenericHID;
import frc.robot.Constants;
import frc.robot.Map;

public class ControllerInputSubsystem extends SubsystemBase {
    private final GenericHID controller = new GenericHID(Constants.OIConstants.kDriverControllerPort);
    private final AlgaeIntakeRollerSubsystem algaeIntakeRollerSubsystem = new AlgaeIntakeRollerSubsystem();
    private final ElevatorSubsystem elevatorSubsystem = new ElevatorSubsystem();
    private final CoralArmSubsystem coralArmSubsystem = new CoralArmSubsystem();
    private final DriveSubsystem driveSubsystem = new DriveSubsystem();
    // private final SensorSubsystem sensorSubsystem = new SensorSubsystem();
    public ControllerInputSubsystem() {
        CommandScheduler.getInstance().registerSubsystem(this);
    }

    
    public void periodic() {
        // This method will be called once per scheduler run
        algaeIntakeRollerSubsystem.checkOnMotors();
        elevatorSubsystem.checkElevatorMotor();
coralArmSubsystem.tempCheck();
        if (controller.getRawButton(11)) {
            double mappedInput = Map.map(controller.getRawAxis(3), -1, 1, 0, 100);
            if (mappedInput < 33) {
                coralArmSubsystem.basicSetPoints('A');
            } else if ((mappedInput <= 66) && (mappedInput >= 33)) {
                coralArmSubsystem.basicSetPoints('B');
            } else if (mappedInput > 66) {
                coralArmSubsystem.basicSetPoints('C');
            }
        }
        if (controller.getRawButton(0)) {
            algaeIntakeRollerSubsystem.rollIntake();
        }
        if (controller.getRawButton(1)) {
            algaeIntakeRollerSubsystem.changeArmPosition();
        }
        if (controller.getPOV() == 0) {
            elevatorSubsystem.moveTheMotor(0.25);
        } else if (controller.getPOV() == 180) {
            elevatorSubsystem.moveTheMotor(-0.25);
        } else {
            elevatorSubsystem.moveTheMotor(0.0);
        }
        if (controller.getRawButton(2)) {
            coralArmSubsystem.rollIntake();
        }
        driveSubsystem.drive(controller.getRawAxis(0), controller.getRawAxis(1), controller.getRawAxis(2), Constants.DriveConstants.kDriveRelativeToField);

    }
    public void disable() {
        driveSubsystem.setX();
    }
}
