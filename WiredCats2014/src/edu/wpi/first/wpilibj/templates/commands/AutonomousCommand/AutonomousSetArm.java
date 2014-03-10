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
public class AutonomousSetArm extends CommandBase {

    Timer t;
    
    public AutonomousSetArm(){
        requires(ldisubsystem);
        t = new Timer();
    }
    
    protected void initialize() {
        t.start();
        ldisubsystem.extend_arm();
    }

    protected void execute() {
        if (t.get() > 0.5) ldisubsystem.retract_arm();
    }

    protected boolean isFinished() {
        return t.get() > 0.75;
    }

    protected void end() {
        ldisubsystem.retract_arm();
    }

    protected void interrupted() {
        ldisubsystem.retract_arm();
    }
    
}
