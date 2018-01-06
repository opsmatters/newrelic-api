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

package com.opsmatters.newrelic.api.model.condition;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a New Relic Infrastructure threshold.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class Threshold
{
    private Integer value;

    @SerializedName("duration_minutes")
    private Integer durationMinutes;

    @SerializedName("time_function")
    private String timeFunction;
   
    /**
     * Default constructor.
     */
    public Threshold()
    {
    }
    
    /**
     * Constructor that takes a durationMinutes.
     * @param durationMinutes The durationMinutes of the threshold
     */
    public Threshold(int durationMinutes)
    {
        setDurationMinutes(durationMinutes);
    }

    /**
     * Sets the value of the threshold.
     * @param value The value of the threshold
     */
    public void setValue(int value)
    {
        this.value = value;
    }

    /**
     * Returns the value of the threshold.
     * @return The value of the threshold
     */
    public int getValue()
    {
        return value;
    }
    
    /**
     * Sets the durationMinutes of the threshold.
     * @param durationMinutes The duration minutes of the threshold
     */
    public void setDurationMinutes(int durationMinutes)
    {
        this.durationMinutes = durationMinutes;
    }

    /**
     * Returns the duration minutes of the threshold.
     * @return The duration minutes of the threshold
     */
    public int getDurationMinutes()
    {
        return durationMinutes;
    }

    /**
     * Sets the time function of the threshold.
     * @param timeFunction The time function of the threshold
     */
    public void setTimeFunction(String timeFunction)
    {
        this.timeFunction = timeFunction;
    }

    /**
     * Sets the time function of the threshold.
     * @param timeFunction The time function of the threshold
     */
    public void setTimeFunction(TimeFunction timeFunction)
    {
        setTimeFunction(timeFunction.value());
    }

    /**
     * Returns the time function of the threshold.
     * @return The time function of the threshold
     */
    public String getTimeFunction()
    {
        return timeFunction;
    }
    
    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "Threshold ["
            +", value="+value
            +", durationMinutes="+durationMinutes
            +", timeFunction="+timeFunction
            +"]";
    }

    /**
     * Returns a builder for the threshold.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make threshold construction easier.
     */
    public static class Builder
    {
        private Threshold threshold = new Threshold();

        /**
         * Sets the durationMinutes of the threshold.
         * @param durationMinutes The duration minutes of the threshold
         * @return This object
         */
        public Builder durationMinutes(int durationMinutes)
        {
            threshold.setDurationMinutes(durationMinutes);
            return this;
        }

        /**
         * Sets the value of the threshold.
         * @param value The value of the threshold
         * @return This object
         */
        public Builder value(int value)
        {
            threshold.setValue(value);
            return this;
        }

        /**
         * Sets the time function of the threshold.
         * @param timeFunction The time function of the threshold
         * @return This object
         */
        public Builder timeFunction(String timeFunction)
        {
            threshold.setTimeFunction(timeFunction);
            return this;
        }

        /**
         * Sets the time function of the threshold to "any".
         * @return This object
         */
        public Builder anyTimeFunction()
        {
            threshold.setTimeFunction(TimeFunction.ANY);
            return this;
        }

        /**
         * Sets the time function of the threshold to "all".
         * @return This object
         */
        public Builder allTimeFunction()
        {
            threshold.setTimeFunction(TimeFunction.ALL);
            return this;
        }

        /**
         * Returns the configured threshold instance
         * @return The threshold instance
         */
        public Threshold build()
        {
            return threshold;
        }
    }
}