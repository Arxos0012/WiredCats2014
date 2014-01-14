/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import edu.wpi.first.wpilibj.Timer;
import java.util.Vector;

/**
 * This object is used to tune a PID loop.
 * It takes in the current position and 
 * desired position on each iteration.
 * 
 * @author arsenelakpa
 */
public class PIDAutotune {
    
    private static final int CLOSE_ENOUGH_TO_DESIRED = 5;
    
    boolean prop_tuned = false;
    
    private WiredVector errorData;
    private Timer timer = new Timer();
    
    
    
    public void init(){
        timer.start();
    }
    
    /**
     * Called repeatedly in order to update and tune the PID
     * @param value - Current value of the PID
     * @param desiredValue - Value that we want the PID to have
     */
    public void execute(int value, int desiredValue){
        errorData.addVal(desiredValue - value);
        if (timer.get() > 15000){
            //the interval is over.
            timer.stop();
            timer.reset();
            analyze();
        }
    }
    
    private void analyze(){
        WiredVector maxima = findLocalMaximaIndices();
        WiredVector minima = findLocalMinimaIndices();
//        int tuneMax;
//        int tuneMaxPos;
//        int tuneMin;
//        int tuneMinPos;
        
//        maxCount = 0
//        minCount = 0
//        for(int i = 1; i < maxima.size(); i++) {
//          if(maxima.getVal(i) == maxima.getVal(i-1)) {
//            maxCount++;
//          }
//          else {
//            maxCount = 0;
//          }
//          if(minima.getVal(i) == minima.getVal(i-1)) {
//            minCount++;
//          }
//          else {
//            minCount = 0;
//          }
//          if(maxCount > 4 && minCount > 4) {
//              tuneMax = maxima.getVal(i-4);
//              tuneMaxPos = maxima.getVal(i-4);
//              tuneMin = minima.getVal(i-4);
//              tuneMinPos = minima.getVal(i-4);
//              tuneMid = (tuneMax + tuneMin)/2
//              break;
//          }
//        }
    }
    
    
    public WiredVector findLocalMaximaIndices(){
        int sumL = 0;
        int sumR = 0;
        WiredVector maxima_indices = new WiredVector();
        
        for (int i = 0; i < errorData.size(); i++){
            for (int j = 1; j <= 5; j++){
                if (i-j >= 0) sumL += errorData.getVal(i-j);
                if (i+j <= errorData.size()) sumR += errorData.getVal(i+j);
            }
            if (sumL/5 < errorData.getVal(i) && sumR/5 < errorData.getVal(i)){
                maxima_indices.addVal(i);
            }
        }
        return maxima_indices;
    }
    
    public WiredVector findLocalMinimaIndices(){
        int sumL = 0;
        int sumR = 0;
        WiredVector minima_indices = new WiredVector();
        
        for (int i = 0; i < errorData.size(); i++){
            for (int j = 1; j <= 5; j++){
                if (i-j >= 0) sumL += errorData.getVal(i-j);
                if (i+j <= errorData.size()) sumR += errorData.getVal(i+j);
            }
            if (sumL/5 > errorData.getVal(i) && sumR/5 > errorData.getVal(i)){
                minima_indices.addVal(i);
            }
        }
        return minima_indices;
    }
    
    /**
     * 
     * @return 
     */
    public boolean isDone(){
        return false;
    }
    
}
