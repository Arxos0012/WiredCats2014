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
public class CommandIntakeDelay extends CommandBase{

    public CommandIntakeDelay(){
        this.setTimeout(0.5);
        requires(ldisubsystem);
    }
    
    protected void initialize() {

    }

    protected void execute() {

    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
        ldisubsystem.setIntakeMotors(0.0);
        ldisubsystem.retract_hood();
    }

    protected void interrupted() {
        ldisubsystem.setIntakeMotors(0.0);
        ldisubsystem.retract_hood();
    }
    
}
