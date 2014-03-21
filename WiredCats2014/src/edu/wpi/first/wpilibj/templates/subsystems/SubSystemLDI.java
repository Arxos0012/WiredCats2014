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
import edu.wpi.first.wpilibj.templates.commands.CommandLastMinuteShit;

/**
 *
 * @author WiredCats
 */
public class SubSystemLDI extends Subsystem{
    
    Solenoid extend_arm = new Solenoid(RobotMap.INTAKE_SOLENOID_EXTEND);
    Solenoid retract_arm = new Solenoid(RobotMap.INTAKE_SOLENOID_RETRACT);
    Victor motor_1 = new Victor(RobotMap.INTAKE_MOTOR_1);
    Victor motor_2 = new Victor(RobotMap.INTAKE_MOTOR_2);
    
    Solenoid extend_hood = new Solenoid(RobotMap.EXTEND_HOOD);
    Solenoid retract_hood = new Solenoid(RobotMap.RETRACT_HOOD);
    
    
    public SubSystemLDI(){
        retract_arm();
    }
    
    protected void initDefaultCommand() {
    }
    
    public void extend_arm(){
        extend_arm.set(true);
        retract_arm.set(false);
    }
    
    public void retract_arm(){
        extend_arm.set(false);
        retract_arm.set(true);
    }
    
    public void extend_hood(){
        extend_hood.set(true);
        retract_hood.set(false);
    }
    
    public void retract_hood(){
        extend_hood.set(false);
        retract_hood.set(true);
    }
    
    public boolean isExtended(){
        return extend_arm.get();
    }
    
    public void motors_intake(){
        setIntakeMotors(-1.0);
    }
    
    public void outtake(){
        setIntakeMotors(1.0);
    }
    
    public void setIntakeMotors(double d){ 
        motor_1.set(d); 
        motor_2.set(-d);
    }    
    
    
    
}
