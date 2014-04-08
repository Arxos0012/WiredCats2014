/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;
import Utilities.DataFilters.FilterInputDeadband;
import Utilities.PID;
import Utilities.WiredVector;
/**
 *
 * @author benbarber
 */
public class CommandArcadeDrive extends CommandBase{
    
    private float primaryTurnCoefficient = 0.8f;
    private float jsDeadband = 0.06f;
    private float interpolationBias = 0.7f;
    
    private float upperShiftLimit = 7.0f; // ft/s
    private float lowerShiftLimit = 4.0f; // ft/s
    
    private WiredVector speeds;
    
    private boolean straightDrive;
    
    private FilterInputDeadband filter_db;
    
    public CommandArcadeDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(drivesubsystem);
        speeds = new WiredVector();
        straightDrive = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        drivesubsystem.setLowSpeed();
        updateValues();
    }
    
    public void updateValues(){
        primaryTurnCoefficient = (float)resources.getValue("primaryTurnCoefficient");
        jsDeadband = (float)resources.getValue("jsDeadband");
        interpolationBias = (float)resources.getValue("interpolationBias");
        upperShiftLimit = (float)resources.getValue("upperShiftLimit");
        lowerShiftLimit = (float)resources.getValue("lowerShiftLimit");
        filter_db = new FilterInputDeadband(jsDeadband);
    }

    protected void execute() {
        
        double y = jsdriver.leftY();
        double x = jsdriver.rightX();
        
        if (Math.abs(x) < jsDeadband) x = 0;
        if (Math.abs(y) < jsDeadband) y = 0;
        
//        System.out.println("X: " + x + ", Y: " + y);
        
        y = interpolationBias*y + (1-interpolationBias)*y*y*y;
        
        double left;
        double right;
        
        if(y == 0){
            left = y + x;
            right = y - x;
        }else{
            left = y + primaryTurnCoefficient*x;
            right = y - primaryTurnCoefficient*x;
        }
        if (Math.abs(x) < 0.20){
            //shift
            float avgSpd = Math.abs(getAverageSpeed());
            if (avgSpd > upperShiftLimit && !drivesubsystem.isHighSpeed()){
                drivesubsystem.setHighSpeed();
            } else if (avgSpd < lowerShiftLimit && drivesubsystem.isHighSpeed()){
                drivesubsystem.setLowSpeed();
            }
        }
//        if (jsdriver.leftTrigger()){
//            drivesubsystem.setLowSpeed();
//        } else { drivesubsystem.setHighSpeed(); } 
        
        drivesubsystem.setLeftRight(left,right);
    }
    
    
    public float getAverageSpeed(){
        //TODO
        float sum = 0;
        speeds.addVal(drivesubsystem.getSpeed());
        for (int i = 0; i < speeds.size(); i++){
            sum+= speeds.getVal(i);
        }
        if (speeds.size() > 5) speeds.removeFirst();
        return sum / speeds.size();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    /**
     * Called when arcade drive is interrupted by
     * another command. Make sure to power down any
     * motors to avoid any nasty loss of control.
     */
    protected void interrupted() {
        System.out.println("Arcade Drive interrupted.");
        drivesubsystem.setLeftRight(0,0);
    }
}
