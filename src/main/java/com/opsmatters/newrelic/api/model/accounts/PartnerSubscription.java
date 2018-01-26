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

package com.opsmatters.newrelic.api.model.accounts;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import com.opsmatters.newrelic.api.model.IdResource;

/**
 * Represents a New Relic partner account subscription.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class PartnerSubscription extends IdResource
{
    @SerializedName("starts_on")
    private String startsOn;

    @SerializedName("expires_on")
    private String expiresOn;

    @SerializedName("annual_renewal_on")
    private String annualRenewalOn;

    private List<ProductLevel> products;

    private String status;

    /**
     * Default constructor.
     */
    public PartnerSubscription()
    {
    }
    
    /**
     * Sets the start date of the subscription.
     * @param startsOn The start date of the subscription
     */
    public void setStartsOn(String startsOn)
    {
        this.startsOn = startsOn;
    }

    /**
     * Returns the start date of the subscription.
     * @return The start date of the subscription
     */
    public String getStartsOn()
    {
        return startsOn;
    }

    /**
     * Sets the expiry date of the subscription.
     * @param expiresOn The expiry date of the subscription
     */
    public void setExpiresOn(String expiresOn)
    {
        this.expiresOn = expiresOn;
    }

    /**
     * Returns the expiry date of the subscription.
     * @return The expiry date of the subscription
     */
    public String getExpiresOn()
    {
        return expiresOn;
    }

    /**
     * Sets the annual renewal date of the subscription.
     * @param annualRenewalOn The annual renewal date of the subscription
     */
    public void setAnnualRenewalOn(String annualRenewalOn)
    {
        this.annualRenewalOn = annualRenewalOn;
    }

    /**
     * Returns the annual renewal date of the subscription.
     * @return The annual renewal date of the subscription
     */
    public String getAnnualRenewalOn()
    {
        return annualRenewalOn;
    }

    /**
     * Sets the list of product levels for the subscription.
     * @param products The list of product levels for the subscription
     */
    public void setProducts(List<ProductLevel> products)
    {
        this.products = products;
    }

    /**
     * Returns the list of product levels for the subscription.
     * @return The list of product levels for the subscription
     */
    public List<ProductLevel> getProducts()
    {
        return products;
    }

    /**
     * Returns the status of the subscription.
     * @return The status of the subscription
     */
    public String getStatus()
    {
        return status;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "PartnerSubscription ["+super.toString()
            +", startsOn="+startsOn
            +", expiresOn="+expiresOn
            +", annualRenewalOn="+annualRenewalOn
            +", products="+products
            +", status="+status
            +"]";
    }
}