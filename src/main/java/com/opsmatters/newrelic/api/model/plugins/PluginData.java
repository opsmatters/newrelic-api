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

/**
 * Represents a set of New Relic plugin metric data.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class PluginData
{
    private Agent agent = new Agent();

    private List<Component> components = new ArrayList<Component>();

    /**
     * Default constructor.
     */
    public PluginData()
    {
    }

    /**
     * Sets the agent details.
     * @param agent The agent details
     */
    public void setAgent(Agent agent)
    {
        this.agent = agent;
    }

    /**
     * Returns the agent details.
     * @return The agent details
     */
    public Agent getAgent()
    {
        return agent;
    }

    /**
     * Returns the components for the plugin data.
     * @return The components for the plugin data
     */
    public List<Component> getComponents()
    {
        return components;
    }

    /**
     * Sets the components for the plugin data.
     * @param components The components for the plugin data
     */
    public void setComponents(List<Component> components)
    {
        this.components.clear();
        this.components.addAll(components);
    }

    /**
     * Adds a component to the plugin data.
     * @param component The component to be added
     */
    public void addComponent(Component component)
    {
        this.components.add(component);
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "PluginData [agent="+agent
            +", components="+components
            +"]";
    }

    /**
     * Returns a builder for the plugin data.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make plugin data construction easier.
     */
    public static class Builder
    {
        private PluginData data = new PluginData();

        /**
         * Sets the host of the agent.
         * @param host The host of the agent
         * @return This object
         */
        public Builder host(String host)
        {
            data.getAgent().setHost(host);
            return this;
        }

        /**
         * Sets the pid of the agent.
         * @param pid The pid of the agent
         * @return This object
         */
        public Builder pid(int pid)
        {
            data.getAgent().setPid(pid);
            return this;
        }

        /**
         * Sets the version of the agent.
         * @param version The version of the agent
         * @return This object
         */
        public Builder version(String version)
        {
            data.getAgent().setVersion(version);
            return this;
        }

        /**
         * Adds a component to the plugin data.
         * @param component The component to be added
         * @return This object
         */
        public Builder addComponent(Component component)
        {
            data.addComponent(component);
            return this;
        }

        /**
         * Returns the configured plugin data instance
         * @return The plugin data instance
         */
        public PluginData build()
        {
            return data;
        }
    }
}