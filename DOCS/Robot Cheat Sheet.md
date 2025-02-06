# Robot Cheat Sheet

This is A document to reflect information regarding the robot that may be relevent or needed as you program. If you Get stumped, and cannot find the answer here, pleaswe update this doc with the info once you find it/figure it out!





## Overview

- Drive Train
    - REV ion - 3 Inch Swerve Module
        - Turning Motor - NEO 550
        - Drive Motor - NEO
        - Communication: CAN Bus

Swerve Module Naming Scheme

Each Swerve Module is labled either A, B, C, or D.
Each Module Has 2 Motors - therefore it also has 2 Motor Controllers
- Motor Controllers with a 1 are: Drive Motors
- Motor Controllers with a 2 are: Turning Motors
Graph Below to Explain

![alt text](Diagrams/Swerve%20Module%20Overview.png?raw=true "Swerve Communications and wiring")


## CAN Bus IDS

| Name in Code | CAN ID | Motor Type | Gear Ratio|
|--------------|--------|------------|-----------|
|FL_Drive | 1 | Brushless
|FL_Turn | 2 | Brushless | 
|||
| FR_Drive |3| Brushless|
| FR_Turn |4| Brushless||
|||
| BL_Drive|5| Brushless|
| BL_Turn |6| Brushless|
|||
| BR_Drive|| Brushless|
| BR_Turn|| Brushless|
|||
|||
|Elevator_Z|| Brushless|
|Arm_Angle| | Brushless|
|Intake_Wheel| Brushless|


Intake_Wheel - 

## Controllers 
This is the Button Mapping For The Controllers

### Flight Stick - Model 

- X_Axis - 
- Y_Axis - 

- Stick_Rotation - 

- Push_Bar - 

- Stick_Button - 