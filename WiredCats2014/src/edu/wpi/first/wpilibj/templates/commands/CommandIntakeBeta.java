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
public class CommandIntakeBeta extends CommandBase{

    public CommandIntakeBeta(){
        this.setTimeout(1.25);
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
    }

    protected void interrupted() {
        ldisubsystem.setIntakeMotors(0.0);
    }
    
}
