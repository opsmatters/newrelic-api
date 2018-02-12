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

package com.opsmatters.newrelic.api.model.insights.widgets;

/**
 * Represents a New Relic Insights dashboard traffic light state.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class TrafficLightState
{
    private String type;

    private Integer min;

    private Integer max;

    /**
     * Represents the types of traffic light state.  
     */
    public enum StateType
    {
        WRONG("wrong"),
        WARNING("warning"),
        OK("ok");

        StateType(String value)
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
    public TrafficLightState()
    {
    }

    /**
     * Sets the type of the state.
     * @param type The type of the state
     */
    public void setType(String type)
    {
        this.type = type;
    }

    /**
     * Sets the type of the state.
     * @param type The type of the state
     */
    public void setType(StateType type)
    {
        setType(type.value());
    }

    /**
     * Returns the type of the state.
     * @return The type of the state
     */
    public String getType()
    {
        return type;
    }

    /**
     * Sets the minimum of the state.
     * @param min The minimum of the state
     */
    public void setMin(Integer min)
    {
        this.min = min;
    }

    /**
     * Returns the minimum of the state.
     * @return The minimum of the state
     */
    public Integer getMin()
    {
        return min;
    }

    /**
     * Sets the maximum of the state.
     * @param max The maximum of the state
     */
    public void setMax(Integer max)
    {
        this.max = max;
    }

    /**
     * Returns the maximum of the state.
     * @return The maximum of the state
     */
    public Integer getMax()
    {
        return max;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "TrafficLightState [type="+type
            +", min="+min
            +", max="+max
            +"]";
    }

    /**
     * Returns a builder for the traffic light state.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make traffic light state construction easier.
     */
    public static class Builder
    {
        private TrafficLightState state = new TrafficLightState();

        /**
         * Sets the type of the state.
         * @param type The type of the state
         * @return This object
         */
        public Builder type(String type)
        {
            state.setType(type);
            return this;
        }

        /**
         * Sets the type of the state.
         * @param type The type of the state
         * @return This object
         */
        public Builder type(StateType type)
        {
            state.setType(type);
            return this;
        }

        /**
         * Sets the type of the state to "wrong".
         * @return This object
         */
        public Builder wrongType()
        {
            state.setType(StateType.WRONG);
            return this;
        }

        /**
         * Sets the type of the state to "warning".
         * @return This object
         */
        public Builder warningType()
        {
            state.setType(StateType.WARNING);
            return this;
        }

        /**
         * Sets the type of the state to "ok".
         * @return This object
         */
        public Builder okType()
        {
            state.setType(StateType.OK);
            return this;
        }

        /**
         * Sets the minimum of the state.
         * @param min The minimum of the state
         * @return This object
         */
        public Builder min(int min)
        {
            state.setMin(min);
            return this;
        }

        /**
         * Sets the maximum of the state.
         * @param max The maximum of the state
         * @return This object
         */
        public Builder max(int max)
        {
            state.setMax(max);
            return this;
        }

        /**
         * Returns the configured traffic light state instance
         * @return The traffic light state instance
         */
        public TrafficLightState build()
        {
            return state;
        }
    }
}