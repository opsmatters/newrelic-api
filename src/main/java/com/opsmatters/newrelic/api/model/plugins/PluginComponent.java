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

package com.opsmatters.newrelic.api.model.plugins;

import java.util.List;
import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;
import com.opsmatters.newrelic.api.model.Entity;
import com.opsmatters.newrelic.api.model.EntityType;

/**
 * Represents a New Relic Browser plugin component.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class PluginComponent extends Entity
{
    /**
     * The type of the entity.
     */
    public static final EntityType TYPE = EntityType.PLUGIN;

    @SerializedName("health_status")
    private String healthStatus;

    @SerializedName("summary_metrics")
    private List<PluginMetric> summaryMetrics = new ArrayList<PluginMetric>();

    /**
     * Default constructor.
     */
    public PluginComponent()
    {
        super(TYPE.value());
    }
    
    /**
     * Returns the health status of the plugin component.
     * @return The health status of the plugin component
     */
    public String getHealthStatus()
    {
        return healthStatus;
    }

    /**
     * Returns the metrics of the plugin component.
     * @return The metrics of the plugin component
     */
    public List<PluginMetric> getMetrics()
    {
        return summaryMetrics;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "PluginComponent ["+super.toString()
            +", healthStatus="+healthStatus
            +", summaryMetrics="+summaryMetrics
            +"]";
    }
}