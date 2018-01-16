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

package com.opsmatters.newrelic.api.model.alerts.channels;

import java.util.List;
import com.opsmatters.newrelic.api.model.NamedResource;

/**
 * Represents a New Relic alert channel.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public abstract class AlertChannel extends NamedResource
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
    public void setPolicyIds(List<Long> policyIds)
    {
        links.setPolicyIds(policyIds);
    }

    /**
     * Returns the policy ids of the channel.
     * @return The policy ids of the channel
     */
    public List<Long> getPolicyIds()
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
        return super.toString()
            +", type="+type
            +", configuration="+configuration
            +", links="+links;
    }

    /**
     * Builder to make alert channel construction easier.
     */
    protected abstract static class Builder<T extends AlertChannel, B extends Builder<T,B>>
    {
        private AlertChannel channel;

        /**
         * Sets the alert channel.
         * @param channel The alert channel
         * @return This object
         */
        public B channel(AlertChannel channel)
        {
            this.channel = channel;
            return self();
        }

        /**
         * Sets the name of the alert channel.
         * @param name The name of the alert channel
         * @return This object
         */
        public B name(String name)
        {
            channel.setName(name);
            return self();
        }

        /**
         * Sets the policies of the alert channel.
         * @param policyIds The policyIds of the alert channel
         * @return This object
         */
        public B policyIds(List<Long> policyIds)
        {
            channel.setPolicyIds(policyIds);
            return self();
        }

        /**
         * Returns this object.
         * @return This object
         */
        protected abstract B self();

        /**
         * Returns the configured alert channel instance
         * @return The alert channel instance
         */
        public abstract T build();
    }
}