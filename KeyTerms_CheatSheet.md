# Key terms Cheat Sheet
---

This Is a place to store Key terms to refer back to later incase of any Confusion


## File Structure
---

All of our code goes into the Robot Folder
Inside of the robot folder, we have a few files, and two other folders- 

## Files not in Sub Folders
---
Core robot Functions are handled by 
- Main - Starts Everything Up
- Robot - Handles each of the Match "Phases" IE; Prep, teleop, auto
    - It also starts up the [Command Scheduler]
- RobotContainer 
    - Button Bindings - What buttons do what
    - Where we put our auto commands
- Constants - where we Store our Values that we call Multiple places, adn we dont intend to change often
- Configs - Where we Create our motor Controller Configs (Details explained later under REV Swerve)

### Subsystems Folder
---
Where we break down the robot into its core "Parts" and "Mechanisms"
A subsystem would be a Collection of MotorControllers, and classes that help to describe the code needed to make the Mechanisms work


### Commands Folder
---
Where we put all of our instructions/Logic we want the robot to do.

Examples: 
- When I Press Button [A] - DO This -> |Raise elevator to Position 1|
- When I Press Button [B] - DO This -> |Raise elevator to Position 2|

- When I Move Joystick_A Axis[1] - DO This -> |Move Robot Forward/Backwards|

-------------------------------------

### REV Swerve Drive Module
---

- [Name]Config - Settings that we can push to or change on the MotorController
    - This is a REV Library Class called SparkMaxConfig
    - It has SEVERAL Attributes that we can push such as
        - Max output
        - Output Range
        - Encoder Settings
        - Idlemode
        - PID
        - Position Wrapping 

public class SwerveDriveKinematics - Helps usby taking the robots Velocity and doing math to help send desired Commands to the Swerve Modules individually
- It is a WPI Lib Class (Not REV Specific)
- It takes in Information about ALL FOUR Swerve modules such as:
    - WheelBase / 2
    - Track Width / 2







 