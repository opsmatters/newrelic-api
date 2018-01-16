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

/**
 * Represents a New Relic plugin.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class PluginId
{
    private String id;
    private String guid;
    
    /**
     * Default constructor.
     */
    public PluginId()
    {
    }
    
    /**
     * Sets the id of the plugin.
     * @param id The id of the plugin
     */
    public void setId(String id)
    {
        this.id = id;
    }

    /**
     * Returns the id of the plugin.
     * @return The id of the plugin
     */
    public String getId()
    {
        return id;
    }

    /**
     * Sets the guid of the plugin.
     * @param guid The guid of the plugin
     */
    public void setGuid(String guid)
    {
        this.guid = guid;
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
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "PluginId [id="+id
            +", guid="+guid
            +"]";
    }

    /**
     * Returns a builder for the plugin.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make plugin construction easier.
     */
    public static class Builder
    {
        private PluginId plugin = new PluginId();

        /**
         * Sets the id of the plugin.
         * @param id The id of the plugin
         * @return This object
         */
        public Builder id(String id)
        {
            plugin.setId(id);
            return this;
        }

        /**
         * Sets the guid of the plugin.
         * @param guid The guid of the plugin
         * @return This object
         */
        public Builder guid(String guid)
        {
            plugin.setGuid(guid);
            return this;
        }

        /**
         * Returns the configured plugin instance
         * @return The plugin instance
         */
        public PluginId build()
        {
            return plugin;
        }
    }
}