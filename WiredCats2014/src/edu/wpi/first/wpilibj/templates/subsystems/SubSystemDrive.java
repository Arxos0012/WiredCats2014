
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
    public static final float TICKS_TO_FEET_PER_SECOND = WHEEL_CIRCUMFERENCE_FEET / TICKS_PER_REVOLUTION;
    
    public static final float MAX_VELOCITY = 14; //m/s
    
    private Talon left = new Talon(RobotMap.DRIVE_LEFT_MOTOR);
    private Talon right = new Talon(RobotMap.DRIVE_RIGHT_MOTOR);
//    private ChezyGyro gyro = new ChezyGyro(RobotMap.DRIVE_GYRO);
    //Accelerometer accel = new Accelerometer(RobotMap.DRIVE_ACCEL);
    private Encoder leftEncoder = new Encoder(RobotMap.DRIVE_LEFT_ENCODER_A,
                                      RobotMap.DRIVE_LEFT_ENCODER_B);
    private Encoder rightEncoder = new Encoder(RobotMap.DRIVE_RIGHT_ENCODER_A,
                                      RobotMap.DRIVE_RIGHT_ENCODER_B);
    private Solenoid lowSpeedSolenoid = new Solenoid(RobotMap.DRIVE_LOW_SPEED_SOLENOID);
    private Solenoid highSpeedSolenoid = new Solenoid(RobotMap.DRIVE_HIGH_SPEED_SOLENOID);
    
    public PID lowStraightPID = new PID(0.5f,0,0);
    public PID highStraightPID = new PID(0.5f,0,0);
    public PID lowTurnPID = new PID(0.4f,0,0);
    public PID highTurnPID = new PID(0.4f,0,0);

    public PID straightPID = lowStraightPID;
    public PID turnPID = lowTurnPID;
    
    public void init(){
//        gyro.initGyro();
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
        right.set(-r);
    }
    
    public void setHighSpeed(){
        highSpeedSolenoid.set(true);
        lowSpeedSolenoid.set(false);
        straightPID = highStraightPID;
        turnPID = highTurnPID;
    }   
    
    public void setLowSpeed(){
        lowSpeedSolenoid.set(true);
        highSpeedSolenoid.set(false);
        straightPID = lowStraightPID;
        turnPID = lowTurnPID;
    }
    
    /**
     * Returns the speed of the robot in feet per second.
     * @return 
     */
    public float getSpeed(){
        float ticks = (float)(leftEncoder.getRate() + -rightEncoder.getRate());
        ticks /= 2;
        return TICKS_TO_FEET_PER_SECOND * ticks;
    }
    
    public float getDistance(){
        float ticks = Math.max((float)Math.abs(leftEncoder.getRate()), (float)Math.abs(rightEncoder.getRate()));
        return TICKS_TO_FEET_PER_SECOND * ticks;
    }
    
    /**
     * Returns the difference in ticks of the left
     * and right encoder.
     * @return 
     */
    public int getTickDifference(){
        return leftEncoder.get() - rightEncoder.get();
    }
    
    public float getAngle(){
//        return (float)gyro.getAngle();
        return -1.0f;
    }

    public boolean isHighSpeed(){
        return highSpeedSolenoid.get();
    }
    
    public void resetGyro(){
//        gyro.reset();
    }
    
    public void resetEncoders(){
        leftEncoder.stop();
        rightEncoder.stop();
        leftEncoder.reset();
        rightEncoder.reset();
        leftEncoder.start();
        rightEncoder.start();
    }
}

