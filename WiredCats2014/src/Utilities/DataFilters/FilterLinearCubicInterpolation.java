/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities.DataFilters;

/**
 *
 * @author arsenelakpa
 */
public class FilterLinearCubicInterpolation implements IDataFilter{
    
    private double bias;
    
    public FilterLinearCubicInterpolation(float bias){
        this.bias = bias;
    }
    
    public double filter(double d){
        return (bias*d)+ (1-bias)*(d*d*d);
    }
    
}