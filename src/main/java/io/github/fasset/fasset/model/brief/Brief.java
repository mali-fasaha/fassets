package io.github.fasset.fasset.model.brief;

import org.javamoney.moneta.Money;

/**
 * Represents common functionality in objects that summarise the fixed assets in the
 * data frame
 *
 * @author edwin.njeru
 */
public interface Brief {

    /**
     * Adds the number of items in the brief
     */
    void addPoll();

    /**
     *
     * @return The id or name of the Brief item
     */
    String getDesignation();

    /**
     *
     * @return total purchase cost
     */
    Money getPurchaseCost();

    /**
     *
     * @return total net book value of items
     */
    Money getNetBookValue();

    /**
     *
     * @return Total AccruedDepreciation of items in the
     * brief
     */
    Money getAccruedDepreciation();

    /**
     *
     * @return # of items in the brief
     */
    int getPoll();

    /**
     *
     * @param designation name of the brief
     */
    void setDesignation(String designation);

    /**
     *
     * @param purchaseCost of all items in the brief
     */
    void setPurchaseCost(Money purchaseCost);

    /**
     *
     * @param netBookValue of all items in the brief
     */
    void setNetBookValue(Money netBookValue);

    /**
     *
     * @param accruedDepreciation of items in the brief
     */
    void setAccruedDepreciation(Money accruedDepreciation);

    /**
     *
     * @param poll # of items in the brief
     */
    void setPoll(int poll);
}
