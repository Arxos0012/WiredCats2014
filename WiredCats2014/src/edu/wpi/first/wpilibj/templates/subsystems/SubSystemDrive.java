
package edu.wpi.first.wpilibj.templates.subsystems;

import Utilities.ChezyGyro;
import Utilities.PID;
import Utilities.PneumaticSystem;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.CommandArcadeDrive;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;

/**
 *
 */
public class SubSystemDrive extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public static final int TICKS_PER_REVOLUTION = 120;
    public static final int WHEEL_RADIUS = 2;
    public static final float WHEEL_CIRCUMFERENCE_FEET = (float)((WHEEL_RADIUS*2*Math.PI)/12);
    public static final float LINEAR_FEET_PER_TICKS = WHEEL_CIRCUMFERENCE_FEET / TICKS_PER_REVOLUTION;
    
    public static float maxVelocity = 14; //m/s
    public float motor_dead_band = 0.25f;
    public float decceleration_dist = 1.5f;
    
    private Talon left = new Talon(RobotMap.DRIVE_LEFT_MOTOR);
    private Talon right = new Talon(RobotMap.DRIVE_RIGHT_MOTOR);
    private Gyro gyro = new Gyro(RobotMap.DRIVE_GYRO);
    //Accelerometer accel = new Accelerometer(RobotMap.DRIVE_ACCEL);
    private Encoder leftEncoder = new Encoder(RobotMap.DRIVE_LEFT_ENCODER_A,
                                      RobotMap.DRIVE_LEFT_ENCODER_B);
    private Encoder rightEncoder = new Encoder(RobotMap.DRIVE_RIGHT_ENCODER_A,
                                      RobotMap.DRIVE_RIGHT_ENCODER_B);
    private Solenoid lowSpeedSolenoid = new Solenoid(RobotMap.DRIVE_LOW_SPEED_SOLENOID);
    private Solenoid highSpeedSolenoid = new Solenoid(RobotMap.DRIVE_HIGH_SPEED_SOLENOID);
    
    public PID straightPID = new PID(0.01f,0,0);
    
    public PID velPID = new PID(0.25f,0,0);
    
    public void init(){
//        gyro.initGyro();
        leftEncoder.start();
        rightEncoder.start();
        setLowSpeed();
        gyro.reset();
        
        straightPID.setP((float)CommandBase.resources.getValue("straightPropK"));
        velPID.setP((float)CommandBase.resources.getValue("velPropK"));
        maxVelocity = ((float)CommandBase.resources.getValue("maxVelocity"));
        motor_dead_band = ((float) CommandBase.resources.getValue("motor_dead_band"));
        decceleration_dist = ((float) CommandBase.resources.getValue("decceleration_dist"));
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
        CommandBase.pneumaticsystem.actuated(PneumaticSystem.SHIFTING_VOLUME_R, 
                                             PneumaticSystem.SHIFTING_WP, false);
    }   
    
    public void setLowSpeed(){
        lowSpeedSolenoid.set(true);
        highSpeedSolenoid.set(false);
        CommandBase.pneumaticsystem.actuated(PneumaticSystem.SHIFTING_VOLUME_E, 
                                             PneumaticSystem.SHIFTING_WP, true);
    }
    
    /**
     * Returns the speed of the robot in feet per second.
     * @return 
     */
    public float getSpeed(){
        float ticks_per_second = Math.max((float)Math.abs(leftEncoder.getRate()), (float)Math.abs(rightEncoder.getRate()));
        return (LINEAR_FEET_PER_TICKS * ticks_per_second)/3.86f;
    }
    
    public float getDistance(){
        float ticks = Math.max((float)Math.abs(leftEncoder.getRaw()), (float)Math.abs(rightEncoder.getRaw()));
        return (LINEAR_FEET_PER_TICKS * ticks)/3.86f;
    }
    
    /**
     * Returns the difference in ticks of the left
     * and right encoder.
     * @return 
     */
    public int getTickDifference(){
        return leftEncoder.get() - rightEncoder.get();
    }
    
    public double getAngle(){
        return gyro.getAngle();
    }
    
    public double getRate(){
        return gyro.getRate();
    }

    public boolean isHighSpeed(){
        return highSpeedSolenoid.get();
    }
    
    public void resetGyro(){
        gyro.reset();
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

