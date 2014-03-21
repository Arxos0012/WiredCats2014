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
public class CommandLastMinuteShit extends CommandBase{

    public CommandLastMinuteShit(){
        requires(ldisubsystem);
    }
    
    protected void initialize() {
        ldisubsystem.extend_arm();
        ldisubsystem.motors_intake();
        ldisubsystem.extend_hood();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        ldisubsystem.retract_arm();
    }

    protected void interrupted() {
        ldisubsystem.retract_arm();
    }
    
    
}
