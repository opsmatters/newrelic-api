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

import com.google.gson.annotations.SerializedName;
import com.opsmatters.newrelic.api.model.NamedResource;

/**
 * Represents a New Relic subscription product level.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class ProductLevel implements NamedResource
{
    @SerializedName("product_id")
    private Integer productId;

    private String name;

    private Integer units;

    private Float price;

    /**
     * Default constructor.
     */
    public ProductLevel()
    {
    }
    
    /**
     * Sets the id of the product.
     * @param productId The id of the product
     */
    public void setProductId(Integer productId)
    {
        this.productId = productId;
    }

    /**
     * Returns the id of the product.
     * @return The id of the product
     */
    public Integer getProductId()
    {
        return productId;
    }

    /**
     * Sets the name of the product.
     * @param name The name of the product
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Returns the name of the product.
     * @return The name of the product
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the units of the product.
     * @param units The units of the product
     */
    public void setUnits(Integer units)
    {
        this.units = units;
    }

    /**
     * Returns the units of the product.
     * @return The units of the product
     */
    public Integer getUnits()
    {
        return units;
    }

    /**
     * Sets the price of the product.
     * @param price The price of the product
     */
    public void setPrice(Float price)
    {
        this.price = price;
    }

    /**
     * Returns the price of the product.
     * @return The price of the product
     */
    public Float getPrice()
    {
        return price;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "ProductLevel [productId="+productId
            +", name="+name
            +", units="+units
            +", price="+price
            +"]";
    }
}