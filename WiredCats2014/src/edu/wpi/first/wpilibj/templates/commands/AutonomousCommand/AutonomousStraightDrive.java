/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.AutonomousCommand;
import Utilities.WiredVector;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;
/**
 * Drives in a straight line from whatever orientation
 * the robot is in upon execution of the command.
 * @author WiredCats
 */
public class AutonomousStraightDrive extends CommandBase {
    
    //sinusoidal motion profiling.
    
    public static final float K_TIMEOUT = 1.0f; // linear relationship between distance and timeout.
    
    private WiredVector speeds;
    
    //In polar coordinates
    private float destination;
    private float currPosition;
    private float currVelocity;
    
    public AutonomousStraightDrive(float time){
        super.setTimeout( (double)(time));
        speeds = new WiredVector();
        drivesubsystem.resetEncoders();
        destination = time;
    }
    protected void initialize() {
        //zero at our current angle.
        drivesubsystem.resetGyro();
        drivesubsystem.resetEncoders();
    }
    protected void execute() {
//       
//       
       drivesubsystem.setLeftRight(.5, .5);
       
//       currVelocity = drivesubsystem.getSpeed();
//        
//       float power = drivesubsystem.straightPID.pid(getDesiredVelocity(), currVelocity);
//       if (currPosition < destination && power < 0) power = 0;
//       
//       float angle = drivesubsystem.getAngle();
//       
//       float powDif = drivesubsystem.turnPID.pid(0, angle);
//       
//       drivesubsystem.setLeftRight(power+powDif, power-powDif);
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
        return isTimedOut();
    }
    protected void end() {
        drivesubsystem.setLeftRight(0, 0);
    }
    protected void interrupted() {
        drivesubsystem.setLeftRight(0, 0);
    }
}