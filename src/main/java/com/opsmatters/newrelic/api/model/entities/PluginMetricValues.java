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
 * Represents a New Relic plugin values.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class PluginMetricValues
{
    private Float raw;
    private String formatted;

    /**
     * Default constructor.
     */
    public PluginMetricValues()
    {
    }

    /**
     * Returns the raw value of the plugin metric.
     * @return The raw value of the plugin metric
     */
    public Float getRaw()
    {
        return raw;
    }

    /**
     * Returns the formatted value of the plugin metric.
     * @return The formatted value of the plugin metric
     */
    public String getFormatted()
    {
        return formatted;
    }
    
    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "PluginMetricValues [raw="+raw
            +", formatted="+formatted
            +"]";
    }
}