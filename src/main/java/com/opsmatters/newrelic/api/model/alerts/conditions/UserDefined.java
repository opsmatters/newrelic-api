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

package com.opsmatters.newrelic.api.model.alerts.conditions;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a New Relic Infrastructure user defined.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class UserDefined
{
    private String metric;

    @SerializedName("value_function")
    private String valueFunction;

    /**
     * Represents a UserDefined value function.  
     */
    public enum ValueFunction
    {
        AVERAGE("average"),
        MIN("min"),
        MAX("max"),
        TOTAL("total"),
        SAMPLE_SIZE("sample_size");

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
    public UserDefined()
    {
    }
    
    /**
     * Constructor that takes a metric.
     * @param metric The metric of the user defined
     */
    public UserDefined(String metric)
    {
        setMetric(metric);
    }
    
    /**
     * Sets the metric of the user defined.
     * @param metric The metric of the user defined
     */
    public void setMetric(String metric)
    {
        this.metric = metric;
    }

    /**
     * Returns the metric of the user defined.
     * @return The metric of the user defined
     */
    public String getMetric()
    {
        return metric;
    }

    /**
     * Sets the value function of the user defined.
     * @param valueFunction The value function of the user defined
     */
    public void setValueFunction(String valueFunction)
    {
        this.valueFunction = valueFunction;
    }

    /**
     * Sets the value function of the user defined.
     * @param valueFunction The value function of the user defined
     */
    public void setValueFunction(ValueFunction valueFunction)
    {
        setValueFunction(valueFunction.value());
    }
    
    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "UserDefined ["
            +", metric="+metric
            +", valueFunction="+valueFunction
            +"]";

    }

    /**
     * Returns a builder for the user defined.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make user defined construction easier.
     */
    public static class Builder
    {
        private UserDefined userDefined = new UserDefined();

        /**
         * Sets the metric of the user defined.
         * @param metric The metric of the user defined
         * @return This object
         */
        public Builder metric(String metric)
        {
            userDefined.setMetric(metric);
            return this;
        }

        /**
         * Sets the value function of the user defined.
         * @param valueFunction The value function of the user defined
         * @return This object
         */
        public Builder valueFunction(String valueFunction)
        {
            userDefined.setValueFunction(valueFunction);
            return this;
        }

        /**
         * Sets the value function of the user defined to "average".
         * @return This object
         */
        public Builder averageValueFunction()
        {
            userDefined.setValueFunction(ValueFunction.AVERAGE);
            return this;
        }

        /**
         * Sets the value function of the user defined to "min".
         * @return This object
         */
        public Builder minValueFunction()
        {
            userDefined.setValueFunction(ValueFunction.MIN);
            return this;
        }

        /**
         * Sets the value function of the user defined to "max".
         * @return This object
         */
        public Builder maxValueFunction()
        {
            userDefined.setValueFunction(ValueFunction.MAX);
            return this;
        }

        /**
         * Sets the value function of the user defined to "total".
         * @return This object
         */
        public Builder totalValueFunction()
        {
            userDefined.setValueFunction(ValueFunction.TOTAL);
            return this;
        }

        /**
         * Sets the value function of the user defined to "sample_size".
         * @return This object
         */
        public Builder sampleSizeValueFunction()
        {
            userDefined.setValueFunction(ValueFunction.SAMPLE_SIZE);
            return this;
        }

        /**
         * Returns the configured user defined instance
         * @return The user defined instance
         */
        public UserDefined build()
        {
            return userDefined;
        }
    }
}