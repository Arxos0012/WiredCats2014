package edu.wpi.first.wpilibj.templates;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static final int leftMotor = 1;
    // public static final int rightMotor = 2;
    
    public static final int DRIVER = 1;
    
    public static final int LEFT_DRIVE = 2;
    public static final int RIGHT_DRIVE = 1;
    
    public static final int ARM_MOTOR = 3;
    public static final int ARM_ENCODER_A = 9;
    public static final int ARM_ENCODER_B = 8;

    public static final int GYRO = 2;
    
    public static final int ACCELEREOMETER = 10;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static final int rangefinderPort = 1;
    // public static final int rangefinderModule = 1;
}
