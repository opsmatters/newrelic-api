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
 * Represents a New Relic Plugins alert condition.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class PluginsAlertCondition extends MetricCondition
{
    @SerializedName("metric_description")
    private String metricDescription;

    @SerializedName("value_function")
    private String valueFunction;

    private PluginId plugin;

    /**
     * Represents a Plugin value function.  
     */
    public enum ValueFunction
    {
        MIN("min"),
        MAX("max"),
        AVERAGE("average"),
        SAMPLE_SIZE("sample_size"),
        TOTAL("total"),
        PERCENT("percent");

        ValueFunction(String value)
        {
            this.value = value;
        }

        public String value()
        {
            return value;
        }

        /**
         * Returns the type for the given value.
         * @param value The type value
         * @return The type for the given value
         */
        public static ValueFunction fromValue(String value)
        {
            ValueFunction[] types = values();
            for(ValueFunction type : types)
            {
                if(type.value().equals(value))
                    return type;
            }
            return null;
        }

        /**
         * Returns <CODE>true</CODE> if the given value is contained in the list of types.
         * @param value The type value
         * @return <CODE>true</CODE> if the given value is contained in the list of types
         */
        public static boolean contains(String value)
        {
            return fromValue(value) != null;
        }

        private String value;
    }

    /**
     * Default constructor.
     */
    public PluginsAlertCondition()
    {
    }

    /**
     * Sets the metric description of the alert condition.
     * @param metricDescription The metric description of the alert condition
     */
    public void setMetricDescription(String metricDescription)
    {
        this.metricDescription = metricDescription;
    }

    /**
     * Returns the metric description of the alert condition.
     * @return The metric description of the alert condition
     */
    public String getMetricDescription()
    {
        return metricDescription;
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
     * Sets the plugin of the alert condition.
     * @param plugin The plugin of the alert condition
     */
    public void setPlugin(PluginId plugin)
    {
        this.plugin = plugin;
    }

    /**
     * Returns the plugin of the alert condition.
     * @return The plugin of the alert condition
     */
    public PluginId getPlugin()
    {
        return plugin;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "PluginsAlertCondition ["+super.toString()
            +", metricDescription="+metricDescription
            +", valueFunction="+valueFunction
            +", plugin="+plugin
            +"]";
    }

    /**
     * Returns a builder for the Plugins alert condition.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make Plugins alert condition construction easier.
     */
    public static class Builder extends MetricCondition.Builder<PluginsAlertCondition, Builder>
    {
        private PluginsAlertCondition condition = new PluginsAlertCondition();

        /**
         * Default constructor.
         */
        public Builder()
        {
            condition(condition);
        }

        /**
         * Sets the metric description of the alert condition.
         * @param metricDescription The metric description of the alert condition
         * @return This object
         */
        public Builder metricDescription(String metricDescription)
        {
            condition.setMetricDescription(metricDescription);
            return this;
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
         * Sets the value function of the alert condition to "min".
         * @return This object
         */
        public Builder minValueFunction()
        {
            condition.setValueFunction(ValueFunction.MIN);
            return this;
        }

        /**
         * Sets the value function of the alert condition to "max".
         * @return This object
         */
        public Builder maxValueFunction()
        {
            condition.setValueFunction(ValueFunction.MAX);
            return this;
        }

        /**
         * Sets the value function of the alert condition to "average".
         * @return This object
         */
        public Builder averageValueFunction()
        {
            condition.setValueFunction(ValueFunction.AVERAGE);
            return this;
        }

        /**
         * Sets the value function of the alert condition to "sample_size".
         * @return This object
         */
        public Builder sampleSizeValueFunction()
        {
            condition.setValueFunction(ValueFunction.SAMPLE_SIZE);
            return this;
        }

        /**
         * Sets the value function of the alert condition to "total".
         * @return This object
         */
        public Builder totalValueFunction()
        {
            condition.setValueFunction(ValueFunction.TOTAL);
            return this;
        }

        /**
         * Sets the value function of the alert condition to "percent".
         * @return This object
         */
        public Builder percentValueFunction()
        {
            condition.setValueFunction(ValueFunction.PERCENT);
            return this;
        }

        /**
         * Sets the plugin of the alert condition.
         * @param plugin The plugin of the alert condition
         * @return This object
         */
        public Builder plugin(PluginId plugin)
        {
            condition.setPlugin(plugin);
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
        public PluginsAlertCondition build()
        {
            return condition;
        }
    }
}