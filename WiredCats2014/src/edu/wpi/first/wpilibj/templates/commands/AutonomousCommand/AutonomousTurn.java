/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates.commands.AutonomousCommand;

import edu.wpi.first.wpilibj.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;

/**
 *
 * @author WiredCats
 */
public class AutonomousTurn extends CommandBase {

    
    float currTheta;

    float goal;
    
    /**
     * Turns this many degrees from the current orientation.
     * @param goal the desired orientation. 
     */
    public AutonomousTurn(float theta){
        drivesubsystem.resetGyro();
        setTimeout(1.0);
        currTheta = 0;
        goal = theta;
    }
    
    protected void initialize() {

    }

    protected void execute() {
        currTheta = drivesubsystem.getAngle();
        double power = drivesubsystem.turnPID.pid(goal, currTheta);
        drivesubsystem.setLeftRight(power, -power);
    }

    protected boolean isFinished() {
        return isTimedOut() | Math.abs(currTheta - goal) < 0.4;
    }

    protected void end() {
        drivesubsystem.setLeftRight(0, 0);
    }

    protected void interrupted() {
        drivesubsystem.setLeftRight(0, 0);

    }
    
}
