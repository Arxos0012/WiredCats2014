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
    
    //PWM
    public static final int DRIVE_LEFT_MOTOR = 1;
    public static final int DRIVE_RIGHT_MOTOR = 2;
    //analog
    public static final int DRIVE_GYRO = 1;
//    public static final int DRIVE_ACCEL = 10;
    public static final int DRIVE_LOW_SPEED_SOLENOID = 5;
    public static final int DRIVE_HIGH_SPEED_SOLENOID = 6;
    //digital
    public static final int DRIVE_LEFT_ENCODER_A = 1;
    public static final int DRIVE_LEFT_ENCODER_B = 2;
    public static final int DRIVE_RIGHT_ENCODER_A = 3;
    public static final int DRIVE_RIGHT_ENCODER_B = 4;
    
    public static final int LAUNCHER_LIMIT_SWITCH = 14;
    public static final int LAUNCHER_WINCH_ENGAGED = 8;
    public static final int LAUNCHER_WINCH_LAUNCH = 7;
    public static final int LAUNCHER_WINCH_MOTOR_1 = 6;
    public static final int LAUNCHER_WINCH_MOTOR_2 = 4;
    
    public static final int INTAKE_SOLENOID_EXTEND = 3;
    public static final int INTAKE_SOLENOID_RETRACT = 4;
    public static final int INTAKE_MOTOR = 5;
}
