/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;

/**
 *
 * @author WiredCats
 */
public class SubSystemLauncher extends Subsystem {
    
    
    Victor[] winch = new Victor[2];
    Solenoid launcherWinchEngaged = new Solenoid(RobotMap.LAUNCHER_WINCH_ENGAGED);
    Solenoid launcherWinchLaunch = new Solenoid(RobotMap.LAUNCHER_WINCH_LAUNCH);
    DigitalInput limitSwitch = new DigitalInput(RobotMap.LAUNCHER_LIMIT_SWITCH);
    
    AnalogChannel HESensor = new AnalogChannel(RobotMap.LAUNCHER_HALL_EFFECT);
    
    boolean fired;
    
    public SubSystemLauncher(){
        winch[0] = new Victor(RobotMap.LAUNCHER_WINCH_MOTOR_1);
        winch[1] = new Victor(RobotMap.LAUNCHER_WINCH_MOTOR_2);
        fired = false;
    }

    protected void initDefaultCommand() {

    }
    
    public void cock(){
        set(1.0);//different on practice bot.
    }
    
    public boolean isCocking(){
        return winch[0].get() != 0.0;
    }
    
    public void stopCocking(){
        set(0.0);
    }
    
    public void set(double d){
        winch[0].set(d);
        winch[1].set(-d);
    }
    
    public boolean hasHitLimit(){
        return !limitSwitch.get();
    }
    
    public double getHESensorVoltage(){
        return HESensor.getVoltage();
    }
    
    /**
     * Returns whether or not the Hall effect sensor
     * has been triggered and the arm is winched down.
     * @return 
     */
    public boolean hitHESensor(){
        return getHESensorVoltage() <  0.3;
    }
    
    public boolean isFired(){
        return fired;
    }
    
    public void setFired(boolean b){
        fired = b;
    }
    
    public void launch(){
        launcherWinchEngaged.set(false);
        launcherWinchLaunch.set(true);
        setFired(true);
    }
    
    /**
     * Ay Ay Ay, how you doin' baybe?
     */
    public void engageWench(){
        launcherWinchEngaged.set(true);
        launcherWinchLaunch.set(false);
    }
}
