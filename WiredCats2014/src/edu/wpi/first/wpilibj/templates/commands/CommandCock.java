/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author WiredCats
 */
public class CommandCock extends CommandBase{
    
    double slowPower;
    double slowDelay;
    
    public CommandCock(){ 
        requires(launchersubsystem);
        requires(ldisubsystem);
    }
    
    protected void initialize() {
        System.out.println("Cocking");
        ldisubsystem.extend_hood();
        launchersubsystem.engageWench();
        if (launchersubsystem.isFired())launchersubsystem.cock();
        slowPower = resources.getValue("slowPower");
        slowDelay = resources.getValue("slowDelay");
    }

    protected void execute() {
        
    }

    protected boolean isFinished() {
        return !launchersubsystem.isFired() |
                launchersubsystem.hitHESensor();
    }

    protected void end() {
        launchersubsystem.stopCocking();
        launchersubsystem.setFired(false);
        ldisubsystem.retract_hood();
    }

    protected void interrupted() {
       launchersubsystem.stopCocking();
       launchersubsystem.setFired(false);
       ldisubsystem.retract_hood();
    }
    
}
