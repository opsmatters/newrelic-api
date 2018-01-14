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

package com.opsmatters.newrelic.api.model.entities;

import com.google.gson.annotations.SerializedName;
import com.opsmatters.newrelic.api.model.NamedResource;

/**
 * Represents a New Relic plugin metric summary.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class PluginMetric extends NamedResource
{
    private String metric;

    @SerializedName("value_function")
    private String valueFunction;

    private PluginMetricThresholds thresholds;

    private PluginMetricValues values;

    /**
     * Default constructor.
     */
    public PluginMetric()
    {
    }

    /**
     * Returns the resource name of the metric.
     * @return The resource name of the metric
     */
    public String getMetric()
    {
        return metric;
    }
    
    /**
     * Returns the value function of the metric.
     * @return The value function of the metric
     */
    public String getValueFunction()
    {
        return valueFunction;
    }

    /**
     * Returns the thresholds of the metric.
     * @return The thresholds of the metric
     */
    public PluginMetricThresholds getThresholds()
    {
        return thresholds;
    }

    /**
     * Returns the values of the metric.
     * @return The values of the metric
     */
    public PluginMetricValues getValues()
    {
        return values;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "PluginMetric [metric="+metric
            +", valueFunction="+valueFunction
            +", thresholds="+thresholds
            +", values="+values
            +"]";
    }
}