/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates.commands.AutonomousCommand;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;

/**
 *
 * @author WiredCats
 */
public class AutonomousLaunch extends CommandBase {

    private static final double POST_LAUNCH_DELAY = 1.0;
    
    Timer t;
    
    public AutonomousLaunch(){
        requires(launchersubsystem);
        requires(ldisubsystem);
        setTimeout(POST_LAUNCH_DELAY);
        t = new Timer();
        setInterruptible(false);
    }
    
    protected void initialize() {
      ldisubsystem.retract_hood();
      ldisubsystem.extend_arm();
      ldisubsystem.setIntakeMotors(-0.5f);
      t.start();
    }

    protected void execute() { 
        if (t.get() > 0.35){
            ldisubsystem.extend_hood();
            ldisubsystem.setIntakeMotors(0);
        }
        if (t.get() > 0.5){
            launchersubsystem.launch();
            t.stop();
            t.reset();
        }
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
        ldisubsystem.setIntakeMotors(0);
    }

    protected void interrupted() {
        ldisubsystem.setIntakeMotors(0);
    }
    
}
