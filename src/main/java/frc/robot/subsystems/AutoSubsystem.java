package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class AutoSubsystem extends SubsystemBase {
    private final Timer timer = new Timer();
    private boolean isTimerRunning = false;
    private final DriveSubsystem robotDrive = new DriveSubsystem();

    public AutoSubsystem() {
        timer.start();
    }

    public void startTimer() {
        if (!isTimerRunning) {
            timer.reset();
            isTimerRunning = true;
        }
    }

    public void stopTimer() {
        if (isTimerRunning) {
            timer.stop();
            isTimerRunning = false;
        }
    }

    public double getTime() {
        return timer.get();
    }
    
    public void resetTimer() {
        timer.reset();
    }

    public void goForward() {
        if (getTime() < 2) {
            robotDrive.drive(0.5, 0, 0, false);
        } else {
            robotDrive.drive(0, 0, 0, false);
        }
    }
    public void startAuto() {
        startTimer();
        goForward();
    }
}
