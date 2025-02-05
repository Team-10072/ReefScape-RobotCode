# To Do list

This is the place that we will keep track of all the things we need to accomplish to get a working robot
 This is "Living Document" - IE. We will all be adding and removing items as they are Completed so we can all stay on the same page



## General To Do

- Configure Default Controller Configuration
- Swerve Drive Code
- Elevator Code
- Intake Code
- Vision/ April tags
- Autonomous


## Swerve Drive Code

- Update CAN ID's in CONSTANTS
- Implement Alignment/Calibration - Swerve Modules Should 
    - Zero out Absolute Encoders
    - Zero out relative Encoders
    - Report Current Status --> Driver Station
        - Not Calibrated
        - Calibrating
        - Completed



## Elevator Code

- Subsystem
    - Elevator Motor & Encoder
        - Translate Motor Rotations --> Height
    - End Stop Switches

- Movement Commands
    - Desired Pre set positions
        - *Need to Update with input from Build team & Driver team*
    - User Fine Tune Adjustments
    - Position Tracking
        - Get Endoder Readings --> Convert to Relevent Values --> Driver Station


## Intake

- Arm Telescoping/Extension
  
    - Subsystem
        - Rotation Motor & Encoder
        - Extension Motor & Encoder

    - Commands
        - Extension
        - Retraction
        - Return Current Position --> Driver Station

