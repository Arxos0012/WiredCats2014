package edu.wpi.first.wpilibj.templates;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    
    public static final int AUTONOMOUS_SWITCH = 10;
    
    //Joystick index values.
    public static final int JS_DRIVER = 1;
    public static final int JS_SUPPORT = 2;
    
    //PWM
    public static final int DRIVE_LEFT_MOTOR = 1;
    public static final int DRIVE_RIGHT_MOTOR = 2;
    //analog
    public static final int DRIVE_GYRO = 2;
//    public static final int DRIVE_ACCEL = 10;
    public static final int DRIVE_LOW_SPEED_SOLENOID = 5;
    public static final int DRIVE_HIGH_SPEED_SOLENOID = 6;
    //digital
    public static final int DRIVE_LEFT_ENCODER_A = 3;
    public static final int DRIVE_LEFT_ENCODER_B = 4;
    public static final int DRIVE_RIGHT_ENCODER_A = 1;
    public static final int DRIVE_RIGHT_ENCODER_B = 2;
    
    public static final int LAUNCHER_LIMIT_SWITCH = 14;
    public static final int LAUNCHER_WINCH_ENGAGED = 4;
    public static final int LAUNCHER_WINCH_LAUNCH = 3; 
    
    //winch 3,4
    //shifting 5,6
    //intake 7,8
    //hood 1,2
    public static final int LAUNCHER_WINCH_MOTOR_1 = 3; // competition: 6 practice: 3
    public static final int LAUNCHER_WINCH_MOTOR_2 = 4;
    public static final int LAUNCHER_HALL_EFFECT = 1;
    
    public static final int INTAKE_SOLENOID_EXTEND = 7;
    public static final int INTAKE_SOLENOID_RETRACT = 8;
    public static final int INTAKE_MOTOR_1 = 5;
    public static final int INTAKE_MOTOR_2 = 6;
    public static final int EXTEND_HOOD = 1;
    public static final int RETRACT_HOOD = 2;
    public static final int BALL_LIMIT_SWITCH = 12;
    
    public static final int COMPRESSOR_PRESSURE_SWITCH = 13;
    public static final int COMPRESSOR_RELAY_CHANNEL = 7; // competition 1!!!! practice: 8
}
