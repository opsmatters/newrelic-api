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

package com.opsmatters.newrelic.api.model.channels;

import java.util.List;

/**
 * Represents a New Relic Campfire alert channel.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class CampfireChannel extends AlertChannel
{
    /**
     * Default constructor.
     */
    public CampfireChannel()
    {
        this(new CampfireConfiguration());
    }

    /**
     * Constructor that takes a configuration.
     * @param configuration The alert channel configuration
     */
    public CampfireChannel(CampfireConfiguration configuration)
    {
        setConfiguration(configuration);
        setType(configuration.getType());
    }
   
    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "CampfireChannel ["+super.toString()+"]";
    }

    /**
     * Returns a builder for the Campfire channel.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make Campfire channel construction easier.
     */
    public static class Builder extends AlertChannel.Builder<CampfireChannel, Builder>
    {
        private CampfireConfiguration configuration = new CampfireConfiguration();
        private CampfireChannel channel = new CampfireChannel(configuration);

        /**
         * Default constructor.
         */
        public Builder()
        {
            channel(channel);
        }

        /**
         * Sets the room of the Campfire alerts.
         * @param room The room of the alerts
         * @return This object
         */
        public Builder room(String room)
        {
            configuration.setRoom(room);
            return this;
        }

        /**
         * Sets the token of the Campfire alerts.
         * @param token The token of the alerts
         * @return This object
         */
        public Builder token(String token)
        {
            configuration.setToken(token);
            return this;
        }

        /**
         * Sets the subdomain of the Campfire alerts.
         * @param subdomain The subdomain of the alerts
         * @return This object
         */
        public Builder subdomain(String subdomain)
        {
            configuration.setSubdomain(subdomain);
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
         * Returns the configured Campfire channel instance
         * @return The Campfire channel instance
         */
        public CampfireChannel build()
        {
            return channel;
        }
    }
}