package frc.robot.Commands;

import frc.robot.subsystems.CoralArmSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.AlgaeIntakeRollerSubsystem;


// import javax.lang.model.util.ElementScanner14;

import edu.wpi.first.wpilibj2.command.Command;
// import edu.wpi.first.wpilibj2.command.ScheduleCommand;


public class IntakeCommands extends Command{

   private final CoralArmSubsystem m_intakeSystem;
    private final ElevatorSubsystem m_ElevatorSubsystem;
    private final AlgaeIntakeRollerSubsystem m_AlgaeIntakeRollerSubsystem;

    

    public IntakeCommands(CoralArmSubsystem intakeSubsystem, ElevatorSubsystem ElevatorSubsystem, AlgaeIntakeRollerSubsystem AlgaeIntakeRollerSubsystem){

       m_intakeSystem = intakeSubsystem;
       m_ElevatorSubsystem = ElevatorSubsystem;
       m_AlgaeIntakeRollerSubsystem = AlgaeIntakeRollerSubsystem;

       addRequirements(intakeSubsystem, ElevatorSubsystem, AlgaeIntakeRollerSubsystem);
    }


    public void coral_intake_up(){

       m_AlgaeIntakeRollerSubsystem.set_angle(1);
       m_ElevatorSubsystem.basicRaise(1); 
       m_intakeSystem.basicRotation(1);
      }
      
      public void coral_intake_Close(){

       m_ElevatorSubsystem.basicRaise(0); 
       m_intakeSystem.basicRotation(0);
       m_AlgaeIntakeRollerSubsystem.set_angle(1);
      }

      public void coral_position_Presets(int arm_angle, int elevator_height){

       m_ElevatorSubsystem.basicRaise(elevator_height);
       m_intakeSystem.basicRotation(arm_angle);

      }
     public void coral_RestState(){

       m_ElevatorSubsystem.basicRaise(0);
       m_intakeSystem.basicRotation(0);
    }

     public void coral_eject(){
       m_intakeSystem.rollIntake();
    }



// Called when the command is initially scheduled.
  @Override
  public void initialize() {
   m_intakeSystem.initilize_arm_angle();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {


  }    
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }



// This command will intake coral. It will handle each of the small changes that need to be made before, during and after the intake process



}
