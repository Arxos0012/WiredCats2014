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
    
    Solenoid positionActuator = new Solenoid(RobotMap.INTAKE_POSITION_ACTUATOR);
    Victor motor = new Victor(RobotMap.INTAKE_MOTOR);
    
    
    public SubSystemLDI(){
        
    }
    
    protected void initDefaultCommand() {
        
    }
    
    public void extend(){
        positionActuator.set(true);
    }
    
    public void retract(){
        positionActuator.set(false);
    }
    
    public boolean isExtended(){
        return positionActuator.get();
    }
    
    public void setMotor(double d){ motor.set(d); }
    
    
    
}
