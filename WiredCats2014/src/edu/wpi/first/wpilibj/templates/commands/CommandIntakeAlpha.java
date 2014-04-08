/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *
 * @author WiredCats
 */
public class CommandIntakeAlpha extends CommandBase{

    public CommandIntakeAlpha(){
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
        return ldisubsystem.isBallIn();
    }

    protected void end() {
        ldisubsystem.retract_arm();
        Scheduler.getInstance().add(new CommandIntakeBeta());
    }

    protected void interrupted() {
        ldisubsystem.retract_arm();
    }
    
    
}
