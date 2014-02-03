/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities.DataFilters;

/**
 *
 * @author arsenelakpa
 */
public class FilterInputDeadband implements IDataFilter{
    
    private double deadband;
    
    public FilterInputDeadband(double deadband){
        this.deadband = deadband;
    }
    
    public double filter(double d){
        if(d < deadband)return ((1)/(1 - deadband))*(d + 1)-1;
        else if (d > deadband) return ((1 - deadband))*(d - 1)+1;
        else return 0;
    }
    
}
