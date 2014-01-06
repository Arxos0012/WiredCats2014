/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

/**
 * This object is used to tune a PID loop.
 * It takes in the current position and 
 * desired position on each iteration.
 * 
 * @author arsenelakpa
 */
public class PIDAutotune {
    
    
    boolean prop_tuned = false;
    
    /**
     * Called repeatedly in order to update and tune the PID
     * @param value - Current value of the PID
     * @param desiredValue - Value that we want the PID to have
     */
    public void execute(int value, int desiredValue){
        
    }
    
    /**
     * 
     * @return 
     */
    public boolean isDone(){
        return false;
    }
    
}
