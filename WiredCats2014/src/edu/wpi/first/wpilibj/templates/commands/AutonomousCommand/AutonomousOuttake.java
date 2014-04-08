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
public class AutonomousOuttake extends CommandBase{

    public AutonomousOuttake(){
        requires(ldisubsystem);
    }
    
    protected void initialize() {
        ldisubsystem.motors_outtake();
    }

    public int autoParameters() {
        return 1; //To change body of generated methods, choose Tools | Templates.
    }
    
    public void autoInit(float[] vals) {
        this.setTimeout(vals[0]);
    }

    protected void execute() {

    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
        ldisubsystem.setIntakeMotors(0);
    }

    protected void interrupted() {
        ldisubsystem.setIntakeMotors(0);
    }
    
}
