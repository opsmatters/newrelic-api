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

/**
 * Represents a New Relic product subscription.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class ProductSubscription
{
    @SerializedName("product_id")
    private Integer productId;

    private Integer quantity;

    @SerializedName("promo_code")
    private String promoCode;

    @SerializedName("data_retention")
    private int dataRetention;

    /**
     * Default constructor.
     */
    public ProductSubscription()
    {
    }
    
    /**
     * Sets the product id of the subscription.
     * @param productId The product id of the subscription
     */
    public void setProductId(int productId)
    {
        this.productId = productId;
    }

    /**
     * Returns the product id of the subscription.
     * @return The product id of the subscription
     */
    public int getProductId()
    {
        return productId;
    }

    /**
     * Sets the quantity of the subscription.
     * @param quantity The quantity of the subscription
     */
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    /**
     * Returns the quantity of the subscription.
     * @return The quantity of the subscription
     */
    public int getQuantity()
    {
        return quantity;
    }

    /**
     * Sets the promo code of the subscription.
     * @param promoCode The promo code of the subscription
     */
    public void setPromoCode(String promoCode)
    {
        this.promoCode = promoCode;
    }

    /**
     * Returns the promo code of the subscription.
     * @return The promo code of the subscription
     */
    public String getPromoCode()
    {
        return promoCode;
    }

    /**
     * Sets the data retention of the subscription (in weeks).
     * @param dataRetention The data retention of the subscription
     */
    public void setDataRetention(int dataRetention)
    {
        this.dataRetention = dataRetention;
    }

    /**
     * Returns the data retention of the subscription (in weeks).
     * @return The data retention of the subscription
     */
    public int getDataRetention()
    {
        return dataRetention;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "ProductSubscription [productId="+productId
            +", quantity="+quantity
            +", promoCode="+promoCode
            +", dataRetention="+dataRetention
            +"]";
    }

    /**
     * Returns a builder for the subscription.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make subscription construction easier.
     */
    public static class Builder
    {
        private ProductSubscription subscription = new ProductSubscription();

        /**
         * Sets the product id of the subscription.
         * @param productId The product id of the subscription
         * @return This object
         */
        public Builder productId(int productId)
        {
            subscription.setProductId(productId);
            return this;
        }

        /**
         * Sets the quantity of the subscription.
         * @param quantity The quantity of the subscription
         * @return This object
         */
        public Builder quantity(int quantity)
        {
            subscription.setQuantity(quantity);
            return this;
        }

        /**
         * Sets the promo code of the subscription.
         * @param promoCode The promo code of the subscription
         * @return This object
         */
        public Builder promoCode(String promoCode)
        {
            subscription.setPromoCode(promoCode);
            return this;
        }

        /**
         * Sets the data retention of the subscription (in weeks).
         * @param dataRetention The data retention of the subscription
         * @return This object
         */
        public Builder dataRetention(int dataRetention)
        {
            subscription.setDataRetention(dataRetention);
            return this;
        }

        /**
         * Returns the configured subscription instance
         * @return The subscription instance
         */
        public ProductSubscription build()
        {
            return subscription;
        }
    }
}