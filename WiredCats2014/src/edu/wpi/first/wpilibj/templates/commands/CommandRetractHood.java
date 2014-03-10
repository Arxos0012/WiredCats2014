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
public class CommandRetractHood extends CommandBase{

    public CommandRetractHood(){
        requires(ldisubsystem);
    }
    
    protected void initialize() {
        ldisubsystem.retract_hood();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {

    }

    protected void interrupted() {

    }
    
}
