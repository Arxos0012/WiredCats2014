
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Accelerometer;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.CommandTankDrive;

/**
 *
 */
public class SubSystemDrive extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    Talon left = new Talon(RobotMap.LEFT_DRIVE);
    Talon right = new Talon(RobotMap.RIGHT_DRIVE);
    Gyro gyro = new Gyro(RobotMap.GYRO);
    Accelerometer accel = new Accelerometer(RobotMap.ACCELEREOMETER);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new CommandTankDrive());
    }
    
    public void setLeft(double power){
        this.left.set(power);
    }
    
    public void setRight(double power){
        this.right.set(-power);
    }
    
    public float getGyroAngle(){
        return (float)gyro.getAngle();
    }
    
    public void resetGyro(){
        gyro.reset();
    }
    
}

