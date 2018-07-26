/*
 * fassets - Project for light-weight tracking of fixed assets
 * Copyright © 2018 Edwin Njeru (mailnjeru@gmail.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.fasset.fasset.model.brief;

import org.javamoney.moneta.Money;

/**
 * Represents common functionality in objects that summarise the fixed assets in the data frame
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
public interface Brief {

    /**
     * Adds the number of items in the brief
     */
    void addPoll();

    /**
     * <p>getDesignation.</p>
     *
     * @return The nomenclature or name of the Brief item
     */
    String getDesignation();

    /**
     * <p>setDesignation.</p>
     *
     * @param designation name of the brief
     */
    void setDesignation(String designation);

    /**
     * <p>getPurchaseCost.</p>
     *
     * @return total purchase cost
     */
    Money getPurchaseCost();

    /**
     * <p>setPurchaseCost.</p>
     *
     * @param purchaseCost of all items in the brief
     */
    void setPurchaseCost(Money purchaseCost);

    /**
     * <p>getNetBookValue.</p>
     *
     * @return total net book value of items
     */
    Money getNetBookValue();

    /**
     * <p>setNetBookValue.</p>
     *
     * @param netBookValue of all items in the brief
     */
    void setNetBookValue(Money netBookValue);

    /**
     * <p>getAccruedDepreciation.</p>
     *
     * @return Total AccruedDepreciation of items in the brief
     */
    Money getAccruedDepreciation();

    /**
     * <p>setAccruedDepreciation.</p>
     *
     * @param accruedDepreciation of items in the brief
     */
    void setAccruedDepreciation(Money accruedDepreciation);

    /**
     * <p>getPoll.</p>
     *
     * @return # of items in the brief
     */
    int getPoll();

    /**
     * <p>setPoll.</p>
     *
     * @param poll # of items in the brief
     */
    void setPoll(int poll);
}
