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
    private boolean forwards;
    
    public AutonomousStraightDrive(float distance, boolean forwards){
        requires(drivesubsystem);
        speeds = new WiredVector();
        drivesubsystem.resetEncoders();
        destination = distance;
        this.forwards = forwards;
    }
    
    public AutonomousStraightDrive(){
        requires(drivesubsystem);
    }
    
    public int autoParameters() { return 2; } 
    
    public void autoInit(float[] vals){
        destination = vals[0];
        speeds = new WiredVector();
        drivesubsystem.resetEncoders();
//        destination = vals[0];
        this.forwards = vals[1] == 1;
    }
    
    protected void initialize() {
        //zero at our current angle.
        drivesubsystem.resetGyro();
        drivesubsystem.resetEncoders();
    }
    protected void execute() {
        
        float dist = Math.abs(drivesubsystem.getDistance())+.25f;
        
        float desiredVel = getDesiredVelocity(dist);
        float actualVel = drivesubsystem.getSpeed();
        
        //trapezoidal motion profiling.
        float vel_power = drivesubsystem.velPID.pid(desiredVel, actualVel);
            
        float str_power = (float)drivesubsystem.straightPID.pid(0, (float)drivesubsystem.getAngle());
        
        if (vel_power < 0) vel_power = 0;
        
        float lpow;
        float rpow;
        if (forwards){
            lpow = vel_power + str_power;
            rpow = vel_power - str_power;
        } else {
            lpow = vel_power - str_power;
            rpow = vel_power + str_power;
        }
        
        System.out.println("dist: " + dist);
        System.out.println("vel_power: " + vel_power);
        System.out.println("str_power: " + str_power);
        
        if(forwards) drivesubsystem.setLeftRight(-lpow, -rpow);
        else drivesubsystem.setLeftRight(lpow,rpow);
        
    }
    
    
    
    public float getDesiredVelocity(float pos){
        if ( pos < 1f){
            return (float)((drivesubsystem.maxVelocity-drivesubsystem.motor_dead_band))*pos+drivesubsystem.motor_dead_band;
        } else if ( pos > destination - drivesubsystem.decceleration_dist){
            float m = -drivesubsystem.maxVelocity/drivesubsystem.decceleration_dist;
            return m * (pos - (destination - drivesubsystem.decceleration_dist)) + drivesubsystem.maxVelocity;
        } else {
            return drivesubsystem.maxVelocity;
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
        float dist = drivesubsystem.getDistance();
        return Math.abs(dist) + 0.5f > destination; // within 0.5f of desired.
    }
    protected void end() {
        drivesubsystem.setLeftRight(0, 0);
    }
    protected void interrupted() {
        drivesubsystem.setLeftRight(0, 0);
    }
}