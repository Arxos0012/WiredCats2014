/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

/**
 *
 * @author benbarber
 */
public class CommandArcadeDrive extends CommandBase{
    
    double xDB = 0, yDB = 0;
    
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
        
        double x = jsdriver.getX();
        double y = jsdriver.getY();
        
        if(x <= -xDB && x >= xDB) x = 0;
        if(y <= -yDB && y >= yDB) y = 0;
        
        double left = y + x;
        double right = y - x;
        
        
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
    }
}