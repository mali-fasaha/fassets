package io.github.fasset.fasset;

/**
 * This class represents the main method which is to be abstracted by other layers that would
 * allow for flexibility in application of business rules
 *
 * @author edwin.njeru
 */
public class DepreciationCalculation {

    /**
     * This calculates the depreciation rate
     *
     * @param deprecant the amount of the asset (cost or NBV) on which depreciation is calculated
     * @param depreciationRate the depreciation rate to use
     * @return amount of depreciation
     */
    public double calculate(double deprecant, double depreciationRate){

        return deprecant * depreciationRate;
    }
}
