/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;
import edu.wpi.first.wpilibj.command.Scheduler;
import java.lang.Math;
/**
 *
 * @author benbarber
 */
public class CommandArcadeDrive extends CommandBase{
    
    public static float PRIMARY_TURN_COEFFICIENT = 0.8f;
    public static float SECONDARY_TURN_COEFFICIENT = 1.0f;
    
    double DEADBAND = 0.06;
    
    public CommandArcadeDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(drivesubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        
        double y = jsdriver.leftY();
        double x = jsdriver.rightX();
        
        if(x <= -DEADBAND && x >= DEADBAND) x = 0;
        if(y <= -DEADBAND && y >= DEADBAND) y = 0;
        
        double left;
        double right;
        
        if(Math.abs(y) <= DEADBAND){
            left = y - SECONDARY_TURN_COEFFICIENT*x;
            right = y + SECONDARY_TURN_COEFFICIENT*x;
        }else{
            left = y - PRIMARY_TURN_COEFFICIENT*x;
            right = y + PRIMARY_TURN_COEFFICIENT*x;
        }
        
        drivesubsystem.setLeft(left);
        drivesubsystem.setRight(right);
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
