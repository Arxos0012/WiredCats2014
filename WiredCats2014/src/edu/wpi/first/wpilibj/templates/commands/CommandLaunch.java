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
public class CommandLaunch extends CommandBase {

    private static final int LDI_MOVEMENT_DELAY = 1000;
    
    public CommandLaunch(){
        requires(launchersubsystem);
        requires(ldisubsystem);
    }
    
    protected void initialize() {
      launchersubsystem.launch();
    }

    protected void execute() { }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
        
    }

    protected void interrupted() {
        
    }
    
}
