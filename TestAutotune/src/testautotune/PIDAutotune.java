/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testautotune;

import java.util.LinkedList;
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
    
    boolean prop_tuned;
    public LinkedList<Integer> errorData = LinkedList<Integer>();
    
    
    private void analyze(){
        LinkedList<Integer> maxima = findLocalMaximaIndices();
        LinkedList<Integer> minima = findLocalMinimaIndices();
        
        
//        int tuneMax;
//        int tuneMaxPos;
//        int tuneMin;
//        int tuneMinPos;
//        
//        int maxCount = 0;
//        int minCount = 0;
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
    
    
    public LinkedList<Integer> findLocalMaximaIndices(){
        int sumL = 0;
        int sumR = 0;
        LinkedList<Integer> maxima_indices = new LinkedList<Integer>();
        
        for (int i = 0; i < errorData.size(); i++){
            for (int j = 1; j <= 5; j++){
                if (i-j >= 0) sumL += errorData.getVal(i-j);
                if (i+j <= errorData.size()) sumR += errorData.getVal(i+j);
            }
            if (sumL/5 < errorData.getVal(i) && sumR/5 < errorData.getVal(i)){
                maxima_indices.add(i);
            }
        }
        return maxima_indices;
    }
    
    public LinkedList<Integer> findLocalMinimaIndices(){
        int sumL = 0;
        int sumR = 0;
        LinkedList<Integer> minima_indices = new LinkedList<Integer>();
        
        for (int i = 0; i < errorData.size(); i++){
            for (int j = 1; j <= 5; j++){
                if (i-j >= 0) sumL += errorData.getVal(i-j);
                if (i+j <= errorData.size()) sumR += errorData.getVal(i+j);
            }
            if (sumL/5 > errorData.getVal(i) && sumR/5 > errorData.getVal(i)){
                minima_indices.add(i);
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
