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

/**
 * Represents a New Relic plugin thresholds.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class PluginMetricThresholds
{
    private Float caution;
    private Float critical;

    /**
     * Default constructor.
     */
    public PluginMetricThresholds()
    {
    }

    /**
     * Returns the caution threshold for the plugin metric.
     * @return The caution threshold for the plugin metric
     */
    public Float getCaution()
    {
        return caution;
    }

    /**
     * Returns the critical threshold for the plugin metric.
     * @return The critical threshold for the plugin metric
     */
    public Float getCritical()
    {
        return critical;
    }
    
    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "PluginMetricThresholds [caution="+caution
            +", critical="+critical
            +"]";
    }
}