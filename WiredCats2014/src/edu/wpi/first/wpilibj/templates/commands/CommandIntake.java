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
        ldisubsystem.setMotor(0.0);
        ldisubsystem.retract_arm();
        ldisubsystem.retract_hood();
    }

    protected void interrupted() {
        ldisubsystem.setMotor(0);
        ldisubsystem.retract_arm();
        ldisubsystem.retract_hood();
    }
    
    
}
