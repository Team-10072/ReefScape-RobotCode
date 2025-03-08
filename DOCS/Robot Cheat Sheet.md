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



## CAN Bus IDS

FL_Drive - 2
FL_Turn - 1

FR_Drive - 4
FR_Turn - 3

BL_Drive - 6
BL_Turn - 5

BR_Drive - 8
BR_Turn - 7

Elevator_Z - 16

Arm_Angle - 12

Arm_Extension - 

Intake_Wheel - 

## Controllers 
This is the Button Mapping For The Controllers

### Flight Stick - Model 

- X_Axis - Moves side to side
- Y_Axis - Moves forward and backwards

- Stick_Rotation - Turning the robot

- Push_Bar - None (so far)

- Stick_Button - 