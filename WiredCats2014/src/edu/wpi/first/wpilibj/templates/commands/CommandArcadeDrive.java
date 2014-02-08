/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;
import Utilities.WiredVector;
import edu.wpi.first.wpilibj.command.Scheduler;
import java.lang.Math;
/**
 *
 * @author benbarber
 */
public class CommandArcadeDrive extends CommandBase{
    
    private float primaryTurnCoefficient = 0.8f;
    private float SECONDARY_TURN_COEFFICIENT = 1.0f; 
    private float jsDeadband = 0.06f;
    private float interpolationBias = 0.7f;
    private float LOW_SPEED_MAX = 12; //ft/s
    
    private float UPPER_SHIFT_LIMIT = .8f * LOW_SPEED_MAX;
    private float LOWER_SHIFT_LIMIT = 4; 
    
    private WiredVector speeds;
    
    public CommandArcadeDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(drivesubsystem);
        speeds = new WiredVector();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        primaryTurnCoefficient = (float)resources.getValue("primaryTurnCoefficient");
        jsDeadband = (float)resources.getValue("jsDeadband");
        interpolationBias = (float)resources.getValue("interpolationBias");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        
        double y = jsdriver.leftY();
        double x = jsdriver.rightX();
        
        System.out.println(drivesubsystem.getSpeed());
        
        if(Math.abs(x) < jsDeadband) x = 0;
        if(Math.abs(y) < jsDeadband) y = 0;
        
        y = interpolationBias*y + (1-interpolationBias)*y*y*y;
        
        double left;
        double right;
        
        if(Math.abs(y) <= jsDeadband){
            left = y + SECONDARY_TURN_COEFFICIENT*x;
            right = y - SECONDARY_TURN_COEFFICIENT*x;
        }else{
            left = y + primaryTurnCoefficient*x;
            right = y - primaryTurnCoefficient*x;
        }
        
        //shifting code.
        float avgSpd = getAverageSpeed();
        if (avgSpd > UPPER_SHIFT_LIMIT && !drivesubsystem.isHighSpeed()){
            drivesubsystem.setHighSpeed();
        } else if (avgSpd < LOWER_SHIFT_LIMIT && drivesubsystem.isHighSpeed()){
            drivesubsystem.setLowSpeed();
        }
            
        //System.out.println("[WiredCats] Gyroscope: " + drivesubsystem.getAngle());
        
        drivesubsystem.setLeft(left);
        drivesubsystem.setRight(right);
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

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        System.out.println("Arcade Drive interrupted.");
    }
}
