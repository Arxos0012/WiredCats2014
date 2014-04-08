/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates.commands.AutonomousCommand;

import Utilities.WiredVector;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;

/**
 *
 * @author WiredCats
 */
public class AutonomousTimedDrive extends CommandBase{

    public boolean forwards;
    
    public AutonomousTimedDrive(){
        requires(drivesubsystem);
    }
    
    public void autoInit(float[] vals){
        this.setTimeout(vals[0]);
        drivesubsystem.resetEncoders();
//        destination = vals[0];
        this.forwards = vals[1] == 1;
    }
    
    public int autoParameters(){
        return 2;
    }
    
    protected void initialize() {
        drivesubsystem.resetGyro();
        drivesubsystem.resetEncoders();
    }

    protected void execute() {
        if(forwards) drivesubsystem.setLeftRight(-1, -1);
        else drivesubsystem.setLeftRight(1,1);
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
        drivesubsystem.setLeftRight(0, 0);
    }

    protected void interrupted() {
        drivesubsystem.setLeftRight(0, 0);
    }
    
}
