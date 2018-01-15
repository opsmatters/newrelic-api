/*
 * Copyright 2018 Gerald Curley
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.opsmatters.newrelic.api.model.products;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

/**
 * Represents a set of usage values for a New Relic product.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class UsageData
{
    private String product;
    private Date from;
    private Date to;
    private String unit;

    private List<Usage> usages = new ArrayList<Usage>();

    /**
     * Default constructor.
     */
    public UsageData()
    {
    }

    /**
     * Returns the product for the usage values.
     * @return The product for the usage values
     */
    public String getProduct()
    {
        return product;
    }

    /**
     * Returns the from date for the usage values.
     * @return The from date for the usage values
     */
    public Date getFrom()
    {
        return from;
    }

    /**
     * Returns the to date for the usage values.
     * @return The to date for the usage values
     */
    public Date getTo()
    {
        return to;
    }

    /**
     * Returns the unit for the usage values.
     * @return The unit for the usage values
     */
    public String getUnit()
    {
        return unit;
    }

    /**
     * Returns the list of usages.
     * @return The list of usages
     */
    public List<Usage> getUsages()
    {
        return usages;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "UsageData [product="+product
            +", from="+from
            +", to="+to
            +", unit="+unit
            +", usages="+usages
            +"]";
    }
}