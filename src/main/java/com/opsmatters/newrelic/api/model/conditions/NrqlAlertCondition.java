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

package com.opsmatters.newrelic.api.model.conditions;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a New Relic NRQL alert condition.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class NrqlAlertCondition extends TermsCondition
{
    @SerializedName("value_function")
    private String valueFunction;

    private Nrql nrql;

    /**
     * Represents an NRQL alert condition value function.  
     */
    public enum ValueFunction
    {
        SINGLE_VALUE("single_value"),
        SUM("sum");

        ValueFunction(String value)
        {
            this.value = value;
        }

        public String value()
        {
            return value;
        }

        private String value;
    }

    /**
     * Default constructor.
     */
    public NrqlAlertCondition()
    {
    }
    
    /**
     * Sets the value function of the alert condition.
     * @param valueFunction The value function of the alert condition
     */
    public void setValueFunction(String valueFunction)
    {
        this.valueFunction = valueFunction;
    }

    /**
     * Sets the value function of the alert condition.
     * @param valueFunction The value function of the alert condition
     */
    public void setValueFunction(ValueFunction valueFunction)
    {
        setValueFunction(valueFunction.value());
    }

    /**
     * Returns the value function of the alert condition.
     * @return The value function of the alert condition
     */
    public String getValueFunction()
    {
        return valueFunction;
    }

    /**
     * Sets the nrql of the alert condition.
     * @param nrql The nrql of the alert condition
     */
    public void setNrql(Nrql nrql)
    {
        this.nrql = nrql;
    }

    /**
     * Returns the nrql of the alert condition.
     * @return The nrql of the alert condition
     */
    public Nrql getNrql()
    {
        return nrql;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "NrqlAlertCondition ["+super.toString()
            +", valueFunction="+valueFunction
            +", nrql="+nrql
            +"]";
    }

    /**
     * Returns a builder for the NRQL alert condition.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make NRQL alert condition construction easier.
     */
    public static class Builder extends TermsCondition.Builder<NrqlAlertCondition, Builder>
    {
        private NrqlAlertCondition condition = new NrqlAlertCondition();

        /**
         * Default constructor.
         */
        public Builder()
        {
            condition(condition);
        }

        /**
         * Sets the value function of the alert condition.
         * @param valueFunction The value function of the alert condition
         * @return This object
         */
        public Builder valueFunction(String valueFunction)
        {
            condition.setValueFunction(valueFunction);
            return this;
        }

        /**
         * Sets the value function of the alert condition to "single_value".
         * @return This object
         */
        public Builder singleValueFunction()
        {
            condition.setValueFunction(ValueFunction.SINGLE_VALUE);
            return this;
        }

        /**
         * Sets the value function of the alert condition to "sum".
         * @return This object
         */
        public Builder sumValueFunction()
        {
            condition.setValueFunction(ValueFunction.SUM);
            return this;
        }

        /**
         * Sets the nrql of the alert condition.
         * @param nrql The nrql of the alert condition
         * @return This object
         */
        public Builder nrql(Nrql nrql)
        {
            condition.setNrql(nrql);
            return this;
        }

        /**
         * Returns this object.
         * @return This object
         */
        @Override
        protected Builder self()
        {
            return this;
        }

        /**
         * Returns the configured alert condition instance
         * @return The alert condition instance
         */
        @Override
        public NrqlAlertCondition build()
        {
            return condition;
        }
    }
}