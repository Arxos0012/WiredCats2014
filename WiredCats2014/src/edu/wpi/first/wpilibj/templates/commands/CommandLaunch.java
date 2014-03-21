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
public class CommandLaunch extends CommandBase {

    private static final int POST_LAUNCH_DELAY = 1;
    
    Timer t;
    
    public CommandLaunch(){
        requires(launchersubsystem);
        requires(ldisubsystem);
        setTimeout(POST_LAUNCH_DELAY);
        t = new Timer();
        setInterruptible(false);
    }
    
    protected void initialize() {
      ldisubsystem.extend_hood();
      t.start();
    }

    protected void execute() { 
        if (t.get() > 0.25){
            launchersubsystem.launch();
            t.stop();
            t.reset();
        }
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
        
    }

    protected void interrupted() {
        
    }
    
}
