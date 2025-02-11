# Key terms Cheat Sheet
This Is a place to store Key terms to refer back to later incase of any Confusion


## File Structure

All of our code goes into the Robot Folder
Inside of the robot folder, we have a few files, and two other folders

## Files not in Sub Folders

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

Where we break down the robot into its core "Parts" and "Mechanisms"
A subsystem would be a Collection of MotorControllers, and classes that help to describe the code needed to make the Mechanisms work

### Drive Subsystem

Basically describes the "Swerve Drive Base"
- We "Build" all four of the Swerve Modules
    - We Tell the module what each CAN ID it needs
    - The Angle it needs to Account for (Used while Turning)

The Gyro Sensor (If Installed) - Checks to see how fast the robot is moving

Swerve Odometry - Checks to see what the Swerve Modules are Currently; (Odometry means to estimate an objects position using data)
- 

### Commands Folder
<font size="4" color="rgb(60,153,0)"> *Sometimes this File is not used - in those instances commands are placed in the RobotContainer.java file instead* </font>

Where we put all of our instructions/Logic we want the robot to do.

Examples: 
- When I Press Button [A] - DO This -> |Raise elevator to Position 1|
- When I Press Button [B] - DO This -> |Raise elevator to Position 2|

- When I Move Joystick_A Axis[1] - DO This -> |Move Robot Forward/Backwards|


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


public class Rotation2D - Basically Helps the robot interpret the user inputs and donvert them into a 2D Direction
    - User Input is often restricted to just values such as an axis that is between 1 and -1 
    - This Takes two of those values and Does Calculus to tell the robot how many degrees it needs to turn
    - Example: 
    *please note - this Class handles the MATH portion of converting those two values into radians/Degrees - YOU still need to send those results to the robot*
    *For Example: In the Simplified motor Control exercise you could feed user input in directly*
    - exampleMotor.set(Axis[1])
    *OR you could manipulate the input, and send a different value to the motor*
    Modified_Value = Axis[1] / 2
    examplemotor.set(Modified_Value)

    For more Detailed info please see the Classitself





 