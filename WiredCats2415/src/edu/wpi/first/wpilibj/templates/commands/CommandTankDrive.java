
package edu.wpi.first.wpilibj.templates.commands;

import com.sun.squawk.util.MathUtils;

/**
 *
 * @author bradmiller
 */
public class CommandTankDrive extends CommandBase {

    public static final float JS_DEADBAND = 0.06f;
    public static final float INTERPOLATION_BIAS = 0.7f;
    public static final float MOTOR_DEADBAND = .04f;
    
    public CommandTankDrive() {
        // Use requires() here to declare subsystem dependencies
        requires(drivesubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        float actualLeftJS = (float)jsdriver.leftY();
        float actualRightJS = (float)jsdriver.rightY();
        
        float actualLeftMotor = (float)actualMotor(theorMotor(theorJS(actualLeftJS))); 
        float actualRightMotor = (float)actualMotor(theorMotor(theorJS(actualRightJS))); 
        
        drivesubsystem.setLeft(actualLeftMotor);
        drivesubsystem.setRight(actualRightMotor);
    }

    
    public float theorJS(float actualJoystick){
        if(actualJoystick < -JS_DEADBAND)return ((1)/(1 - JS_DEADBAND))*(actualJoystick + 1)-1;
        else if (actualJoystick > JS_DEADBAND) return ((1 - JS_DEADBAND))*(actualJoystick - 1)+1;
        else return 0;
    }
    
    /**
     * The input to this function is the theoretical joystick value.
     * The output to this function is the theoretical motor value.
     * @param theorJS The theoretical joystick value.
     * @return 
     */
    private float theorMotor(float theorJS){
        return (INTERPOLATION_BIAS*theorJS)+ (1-INTERPOLATION_BIAS)*(theorJS*theorJS*theorJS);
    }
    
    private float actualMotor(float theorMotor){
        if (theorMotor < 0){
            return (1 - MOTOR_DEADBAND)*(theorMotor+1)-1;
        } else if (theorMotor == 0) {
            return 0;
        } else{
            return (1 - MOTOR_DEADBAND)*(theorMotor-1)+1;
        }
       
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