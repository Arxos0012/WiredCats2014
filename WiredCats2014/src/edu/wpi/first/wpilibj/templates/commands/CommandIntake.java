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
        ldisubsystem.intake();
        try { Thread.sleep(1500); } catch (InterruptedException ie) {}
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return !jsdriver.leftTrigger();
    }

    protected void end() {
        ldisubsystem.setMotor(0.0);
    }

    protected void interrupted() {
        ldisubsystem.setMotor(0);
    }
    
    
}
