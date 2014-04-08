/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates.subsystems;

import Utilities.PneumaticSystem;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.templates.commands.CommandIntakeAlpha;

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
    
    DigitalInput limitSwitch = new DigitalInput(RobotMap.BALL_LIMIT_SWITCH);
    
    
    public SubSystemLDI(){
    }
    
    
    protected void initDefaultCommand() {
        retract_arm();
        extend_hood();
    }
    
    public void extend_arm(){
        extend_arm.set(true);
        retract_arm.set(false);
        CommandBase.pneumaticsystem.actuated(PneumaticSystem.ARM_VOLUME_E, PneumaticSystem.ARM_WP, true);
    }
    
    public void retract_arm(){
        extend_arm.set(false);
        retract_arm.set(true);
        CommandBase.pneumaticsystem.actuated(PneumaticSystem.ARM_VOLUME_R, PneumaticSystem.ARM_WP, false);
    }
    
    public void extend_hood(){
        extend_hood.set(true);
        retract_hood.set(false);
        CommandBase.pneumaticsystem.actuated(PneumaticSystem.HOOD_VOLUME_E, PneumaticSystem.HOOD_WP, true);
    }
    
    public void retract_hood(){
        extend_hood.set(false);
        retract_hood.set(true);
        CommandBase.pneumaticsystem.actuated(PneumaticSystem.HOOD_VOLUME_R, PneumaticSystem.HOOD_WP, false);
    }
    
    /**
     * Is this ballin'?
     * @return 
     */
    public boolean isBallIn(){
        return !limitSwitch.get();
    }
    
    public boolean isExtended(){
        return extend_arm.get();
    }
    
    public void motors_intake(){
        setIntakeMotors(-1.0);
    }
    
    public void motors_outtake(){
        setIntakeMotors(1.0);
    }
    
    public void outtake(){
        setIntakeMotors(1.0);
    }
    
    public void setIntakeMotors(double d){ 
        motor_1.set(d); 
        motor_2.set(-d);
    }    
    
    
    
}
