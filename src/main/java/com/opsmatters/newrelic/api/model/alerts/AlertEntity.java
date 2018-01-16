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

package com.opsmatters.newrelic.api.model.alerts;

import com.google.gson.annotations.SerializedName;
import com.opsmatters.newrelic.api.model.Entity;
import com.opsmatters.newrelic.api.model.accounts.Product;

/**
 * Represents a New Relic alert entity.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class AlertEntity extends Entity
{
    private String type;

    private String product;

    @SerializedName("group_id")
    private Long groupId;

    /**
     * Default constructor.
     */
    public AlertEntity()
    {
        super(null);
    }

    /**
     * Sets the entity type.
     * @param type The entity type
     */
    @Override
    public void setType(String type)
    {
        this.type = type;
    }

    /**
     * Returns the entity type.
     * @return The entity type
     */
    @Override
    public String getType()
    {
        return type;
    }

    /**
     * Sets the product of the entity.
     * @param product The product of the entity
     */
    public void setProduct(String product)
    {
        this.product = product;
    }

    /**
     * Sets the product of the entity.
     * @param product The product of the entity
     */
    public void setProduct(Product product)
    {
        setProduct(product.name());
    }

    /**
     * Returns the product of the entity.
     * @return The product of the entity
     */
    public String getProduct()
    {
        return product;
    }

    /**
     * Sets the group id of the entity.
     * @param groupId The group id of the entity
     */
    public void setGroupId(long groupId)
    {
        this.groupId = groupId;
    }

    /**
     * Returns the group id of the entity.
     * @return The group id of the entity
     */
    public long getGroupId()
    {
        return groupId;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "AlertEntity ["+super.toString()
            +", product="+product
            +", groupId="+groupId
            +"]";
    }
}