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

package com.opsmatters.newrelic.api.model;

import java.util.List;
import com.opsmatters.newrelic.api.model.channel.ChannelConfiguration;

/**
 * Represents a New Relic alert channel.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class AlertChannel extends NamedEntity
{
    private String type;
    private ChannelConfiguration configuration;
    private ChannelLinks links;

    /**
     * Default constructor.
     */
    public AlertChannel()
    {
    }
   
    /**
     * Constructor that takes a name.
     * @param name The name of the channel
     */
    public AlertChannel(String name)
    {
        setName(name);
    }

    /**
     * Sets the type of the channel.
     * @param type The type of the channel
     */
    public void setType(String type)
    {
        this.type = type;
    }

    /**
     * Returns the type of the channel.
     * @return The type of the channel
     */
    public String getType()
    {
        return type;
    }

    /**
     * Sets the configuration of the channel.
     * @param configuration The configuration of the channel
     */
    public void setConfiguration(ChannelConfiguration configuration)
    {
        this.configuration = configuration;
    }

    /**
     * Returns the configuration of the channel.
     * @return The configuration of the channel
     */
    public ChannelConfiguration getConfiguration()
    {
        return configuration;
    }

    /**
     * Sets the policy ids of the channel.
     * @param policyIds The policy ids of the channel
     */
    public void setPolicyIds(List<Integer> policyIds)
    {
        links.setPolicyIds(policyIds);
    }

    /**
     * Returns the policy ids of the channel.
     * @return The policy ids of the channel
     */
    public List<Integer> getPolicyIds()
    {
        return links.getPolicyIds();
    }

    /**
     * Returns the links of the channel.
     * @return The links of the channel
     */
    public ChannelLinks getLinks()
    {
        return links;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "AlertChannel ["+super.toString()
            +", type="+type
            +", configuration="+configuration
            +", links="+links
            +"]";
    }

    /**
     * Returns a builder for the alert channel.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make alert channel construction easier.
     */
    public static class Builder
    {
        private AlertChannel channel = new AlertChannel();

        /**
         * Sets the name of the alert channel.
         * @param name The name of the alert channel
         * @return This object
         */
        public Builder name(String name)
        {
            channel.setName(name);
            return this;
        }

        /**
         * Sets the configuration of the alert channel.
         * @param configuration The configuration of the alert channel
         * @return This object
         */
        public Builder configuration(ChannelConfiguration configuration)
        {
            channel.setConfiguration(configuration);
            channel.setType(configuration != null ? configuration.getType() : null);
            return this;
        }

        /**
         * Sets the policies of the alert channel.
         * @param policyIds The policyIds of the alert channel
         * @return This object
         */
        public Builder policyIds(List<Integer> policyIds)
        {
            channel.setPolicyIds(policyIds);
            return this;
        }

        /**
         * Returns the configured alert channel instance
         * @return The alert channel instance
         */
        public AlertChannel build()
        {
            return channel;
        }
    }
}