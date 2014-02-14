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
public class CommandIntake extends CommandBase{

    public CommandIntake(){
        requires(ldisubsystem);
    }
    
    protected void initialize() {
        if(!ldisubsystem.isExtended()) ldisubsystem.extend();
        ldisubsystem.setMotor(1.0);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
    
}
