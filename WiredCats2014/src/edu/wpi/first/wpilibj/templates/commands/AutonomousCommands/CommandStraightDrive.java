/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates.commands.AutonomousCommands;

import Utilities.WiredVector;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;

/**
 * Drives in a straight line from whatever orientation
 * the robot is in upon execution of the command.
 * @author WiredCats
 */
public class CommandStraightDrive extends CommandBase {
    
    //sinusoidal motion profiling.
    
    private WiredVector speeds;
    
    //In polar coordinates
    private float destination;
    private float currPosition;
    private float startingPosition;
    
    public CommandStraightDrive(float distance){
        
        speeds = new WiredVector();
        currPosition = 0;
        destination = distance;
        startingPosition = drivesubsystem.getEncoderValue();
        
    }

    protected void initialize() {
        
    }

    protected void execute() {
       
       
       currPosition = drivesubsystem.getEncoderValue();
       float currVelocity = drivesubsystem.getSpeed();
        
       float power = drivesubsystem.turnPID.pid(getDesiredVelocity(), currVelocity);
       
       //STRAIGHT DRIVE CODE YET TO BE CODED.
       //TODO
       drivesubsystem.setLeftRight(power, power);
    }
    
    public float getDesiredVelocity(){
        if ( currPosition < .2*destination){
            return (float)(drivesubsystem.MAX_VELOCITY/(.2*destination))*currPosition;
        } else if ( currPosition > .8*destination){
            return -(float)((drivesubsystem.MAX_VELOCITY/(.2*destination))*(currPosition-(.8f*destination)));
        } else {
            return drivesubsystem.MAX_VELOCITY;
        }
    }
    
    /**
     * Returns the average current speed of the drivetrain in feet/second.
    */
    public float getAverageSpeed(){
        //TODO
        float sum = 0;
        speeds.addVal(drivesubsystem.getSpeed());
        for (int i = 0; i < speeds.size(); i++){
            sum+= speeds.getVal(i);
        }
        if (speeds.size() > 10) speeds.removeFirst();
        return sum / speeds.size();
    }

    protected boolean isFinished() {
        return destination - currPosition < 0;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
