
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.I2C;

public class SensorSubsystem {
    private static final int Sensor_Address = 0x29;
    private static final int distance_register = 0x14; // Register address for distance data
    private I2C toFSensor;

    public SensorSubsystem() {
        // Initialize the I2C connection with the VL53L0X sensor
        toFSensor = new I2C(I2C.Port.kOnboard, Sensor_Address);
    }

    public int getDistance() {
        byte[] buffer = new byte[2];
        // Read the distance data from the sensor
        toFSensor.read(distance_register, 2, buffer);
        // Combine the high and low byte to get the full distance value
        return (buffer[0] << 8) | buffer[1];
    }

    public boolean isSensorReady() {
        byte[] buffer = new byte[1];
        // Check the sensor's status register
        toFSensor.read(0x01, 1, buffer);
        // Return true if the sensor is ready (0 meaning ready)
        return buffer[0] == 0;
    }
}
