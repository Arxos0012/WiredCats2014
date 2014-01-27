/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Utilities.DataFilters;

/**
 * Interface that defines data filtering. For example:
 * DeadbandFilter would implement this interface, and implement
 * a filter that would remove any noise under a deadband
 * threshold.
 * @author WiredCats
 */
public interface IDataFilter {
    /**
     * A data piece is entered into the filter, and the 
     * filter must return some edited version of the data.
     * This is the most generic sense possible. To give
     * an example using the deadband idea, this is the function
     * that would remove the noise under the deadband value.
     * @param d
     * @return 
     */
    public double filter(double d);
}
