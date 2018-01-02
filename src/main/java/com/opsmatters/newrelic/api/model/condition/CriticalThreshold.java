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

/**
 * Represents a New Relic Infrastructure critical threshold.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class CriticalThreshold
{
    private Integer duration_minutes;
    
    /**
     * Default constructor.
     */
    public CriticalThreshold()
    {
    }
    
    /**
     * Constructor that takes a duration_minutes.
     * @param duration_minutes The duration_minutes of the critical threshold
     */
    public CriticalThreshold(int duration_minutes)
    {
        this.duration_minutes = duration_minutes;
    }
    
    /**
     * Sets the duration_minutes of the critical threshold.
     * @param duration_minutes The duration_minutes of the critical threshold
     */
    public void setDurationMinutes(int duration_minutes)
    {
        this.duration_minutes = duration_minutes;
    }

    /**
     * Returns the duration_minutes of the critical threshold.
     * @return The duration_minutes of the critical threshold
     */
    public int getDurationMinutes()
    {
        return duration_minutes;
    }
    
    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "CriticalThreshold [duration_minutes="+duration_minutes+"]";
    }

    /**
     * Returns a builder for the critical threshold.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make critical threshold construction easier.
     */
    public static class Builder
    {
        private CriticalThreshold threshold = new CriticalThreshold();

        /**
         * Sets the duration_minutes of the critical threshold.
         * @param duration_minutes The duration_minutes of the critical threshold
         * @return This object
         */
        public Builder durationMinutes(int duration_minutes)
        {
            threshold.setDurationMinutes(duration_minutes);
            return this;
        }

        /**
         * Returns the configured critical threshold instance
         * @return The critical threshold instance
         */
        public CriticalThreshold build()
        {
            return threshold;
        }
    }
}