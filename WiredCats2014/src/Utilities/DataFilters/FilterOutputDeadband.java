/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities.DataFilters;

/**
 *
 * @author arsenelakpa
 */
public class FilterOutputDeadband implements IDataFilter{
    
    private double deadband;
    
    public FilterOutputDeadband(double deadband){
        this.deadband = deadband;
    }
    
    public double filter(double d){
        if (d < 0){
            return (1 - deadband)*(d+1)-1;
        } else if (d == 0) {
            return 0;
        } else{
            return (1 - deadband)*(d-1)+1;
        }
    }
    
}
