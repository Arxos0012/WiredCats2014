/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates.commands.AutonomousCommand;

import edu.wpi.first.wpilibj.templates.commands.CommandBase;


/**
 *
 * @author WiredCats
 */
public class AutonomousIntake extends CommandBase{

    private double motor_speed = 0.75;
    
    public AutonomousIntake(double timeout){
        requires(ldisubsystem);
        this.setTimeout(timeout);
    }
    
    public AutonomousIntake(){
        requires(ldisubsystem);
    }
    
    public void autoInit(float[] vals){
        this.setTimeout(vals[0]);
        motor_speed = vals[1];
    }
    
    public int autoParameters() { return 2; }
    
    protected void initialize() {
        ldisubsystem.extend_arm();
        ldisubsystem.setIntakeMotors(motor_speed);
        ldisubsystem.extend_hood();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
        ldisubsystem.retract_arm();
        ldisubsystem.retract_hood();
        ldisubsystem.setIntakeMotors(0);
    }

    protected void interrupted() {
        ldisubsystem.retract_arm();
        ldisubsystem.retract_hood();
        ldisubsystem.setIntakeMotors(0);
    }
    
}
