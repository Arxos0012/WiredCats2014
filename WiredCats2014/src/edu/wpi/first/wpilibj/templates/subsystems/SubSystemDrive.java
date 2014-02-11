
package edu.wpi.first.wpilibj.templates.subsystems;

import Utilities.ChezyGyro;
import Utilities.PID;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.CommandArcadeDrive;

/**
 *
 */
public class SubSystemDrive extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public static final int TICKS_PER_REVOLUTION = 120;
    public static final int WHEEL_RADIUS = 2;
    public static final float WHEEL_CIRCUMFERENCE_FEET = (float)((WHEEL_RADIUS*2*Math.PI)/12);
    public static final float TICKS_TO_FEET = WHEEL_CIRCUMFERENCE_FEET / TICKS_PER_REVOLUTION;
    
    private Talon left = new Talon(RobotMap.DRIVE_LEFT_MOTOR_1);
    private Talon left2 = new Talon(RobotMap.DRIVE_LEFT_MOTOR_2);
    private Talon right = new Talon(RobotMap.DRIVE_RIGHT_MOTOR_1);
    private Talon right2 = new Talon(RobotMap.DRIVE_RIGHT_MOTOR_2);
    private ChezyGyro gyro = new ChezyGyro(RobotMap.DRIVE_GYRO);
    //Accelerometer accel = new Accelerometer(RobotMap.DRIVE_ACCEL);
    private Encoder leftEncoder = new Encoder(RobotMap.DRIVE_LEFT_ENCODER_A,
                                      RobotMap.DRIVE_LEFT_ENCODER_B);
    private Encoder rightEncoder = new Encoder(RobotMap.DRIVE_RIGHT_ENCODER_A,
                                      RobotMap.DRIVE_RIGHT_ENCODER_B);
    private Solenoid lowSpeedSolenoid = new Solenoid(RobotMap.DRIVE_LOW_SPEED_SOLENOID);
    private Solenoid highSpeedSolenoid = new Solenoid(RobotMap.DRIVE_HIGH_SPEED_SOLENOID);
    
    public PID lowStraightPID = new PID(0.5f,0,0);
    public PID highStraightPID = new PID(0.5f,0,0);
    public PID lowTurnPID = new PID(0.5f,0,0);
    public PID highTurnPID = new PID(0.5f,0,0);

    public PID straightPID;
    public PID turnPID;
    
    public float MAX_VELOCITY = 16;
    
    public void init(){
        gyro.initGyro();
        leftEncoder.start();
        rightEncoder.start();
        setLowSpeed();
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new CommandArcadeDrive());
    }
    
    /**
     * Sets the left and right side of the drivetrain.
     * @param l
     * @param r 
     */
    public void setLeftRight(double l, double r){
        left.set(l);
        left2.set(l);
        right.set(-r);
        right2.set(-r);
    }
    
    public void setHighSpeed(){
        highSpeedSolenoid.set(true);
        lowSpeedSolenoid.set(false);
    }   
    
    public void setLowSpeed(){
        lowSpeedSolenoid.set(true);
        highSpeedSolenoid.set(false);
    }
    
    public float getEncoderValue(){
        int ticks = (leftEncoder.getRaw() + -rightEncoder.getRaw());
        ticks /= 2;
        return (TICKS_TO_FEET * ticks);
    }
    
    /**
     * Returns the speed of the robot in feet per second.
     * @return 
     */
    public float getSpeed(){
        float ticks = (float)(leftEncoder.getRate() + -rightEncoder.getRate());
        ticks /= 2;
        return TICKS_TO_FEET * ticks;
    }
    
    public float getAngle(){
        return (float)gyro.getAngle();
    }

    public boolean isHighSpeed(){
        return highSpeedSolenoid.get();
    }
    
    public void resetGyro(){
        gyro.reset();
    }
}

