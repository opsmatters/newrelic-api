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

import java.util.List;
import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;

/**
 * Represents a New Relic Browser plugin.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class Plugin extends Entity
{
    /**
     * The type of the entity.
     */
    public static final EntityType TYPE = EntityType.PLUGIN;

    private String guid;

    private String publisher;

    @SerializedName("component_agent_count")
    private Integer componentAgentCount;

    private PluginDetails details;

    @SerializedName("summary_metrics")
    private List<PluginMetric> summaryMetrics = new ArrayList<PluginMetric>();

    /**
     * Default constructor.
     */
    public Plugin()
    {
        super(TYPE.value());
    }
    
    /**
     * Returns the guid of the plugin.
     * @return The guid of the plugin
     */
    public String getGuid()
    {
        return guid;
    }

    /**
     * Returns the publisher of the plugin.
     * @return The publisher of the plugin
     */
    public String getPublisher()
    {
        return publisher;
    }

    /**
     * Returns the component agent count of the plugin.
     * @return The component agent count of the plugin
     */
    public int getComponentAgentCount()
    {
        return componentAgentCount;
    }

    /**
     * Returns the details of the plugin.
     * @return The details of the plugin
     */
    public PluginDetails getDetails()
    {
        return details;
    }

    /**
     * Returns the metrics of the plugin.
     * @return The metrics of the plugin
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
        return "Plugin ["+super.toString()
            +", guid="+guid
            +", publisher="+publisher
            +", componentAgentCount="+componentAgentCount
            +", details="+details
            +", summaryMetrics="+summaryMetrics
            +"]";
    }
}