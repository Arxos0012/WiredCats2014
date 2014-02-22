/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates.commands;

/**
 *
 * @author WiredCats
 */
public class CommandCock extends CommandBase{

    public CommandCock(){ 
        requires(launchersubsystem);
        requires(ldisubsystem);
    }
    
    protected void initialize() {
        System.out.println("Cocking");
        ldisubsystem.extend();
        launchersubsystem.engageWench();
        if (!launchersubsystem.hasHitLimit()) launchersubsystem.cock();
        System.out.println("limitswitch: " + launchersubsystem.hasHitLimit());
        launchersubsystem.cock();
    }

    protected void execute() {
        
    }

    protected boolean isFinished() {
        return launchersubsystem.hasHitLimit();
    }

    protected void end() {
        launchersubsystem.stopCocking();
    }

    protected void interrupted() {
       launchersubsystem.stopCocking();
    }
    
}
