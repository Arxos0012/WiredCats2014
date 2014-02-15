/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;

/**
 *
 * @author WiredCats
 */
public class SubSystemLDI extends Subsystem{
    
    Solenoid extend = new Solenoid(RobotMap.INTAKE_SOLENOID_EXTEND);
    Solenoid retract = new Solenoid(RobotMap.INTAKE_SOLENOID_RETRACT);
    Victor motor = new Victor(RobotMap.INTAKE_MOTOR);
    
    
    public SubSystemLDI(){
        
    }
    
    protected void initDefaultCommand() {
        
    }
    
    public void extend(){
        extend.set(true);
        retract.set(false);
    }
    
    public void retract(){
        extend.set(false);
        retract.set(true);
    }
    
    public boolean isExtended(){
        return extend.get();
    }
    
    public void intake(){
        setMotor(-1.0);
    }
    
    public void outtake(){
        setMotor(1.0);
    }
    
    public void setMotor(double d){ motor.set(d); }
    
    
    
}
