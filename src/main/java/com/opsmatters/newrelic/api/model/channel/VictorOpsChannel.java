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

package com.opsmatters.newrelic.api.model.channel;

import java.util.List;

/**
 * Represents a New Relic VictorOps alert channel.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class VictorOpsChannel extends AlertChannel
{
    /**
     * Default constructor.
     */
    public VictorOpsChannel()
    {
        this(new VictorOpsConfiguration());
    }

    /**
     * Constructor that takes a configuration.
     * @param configuration The alert channel configuration
     */
    public VictorOpsChannel(VictorOpsConfiguration configuration)
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
        return "VictorOpsChannel ["+super.toString()+"]";
    }

    /**
     * Returns a builder for the VictorOps channel.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make VictorOps channel construction easier.
     */
    public static class Builder extends AlertChannel.Builder<VictorOpsChannel, Builder>
    {
        private VictorOpsConfiguration configuration = new VictorOpsConfiguration();
        private VictorOpsChannel channel = new VictorOpsChannel(configuration);

        /**
         * Default constructor.
         */
        public Builder()
        {
            channel(channel);
        }

        /**
         * Sets the key of the VictorOps alerts.
         * @param key The key of the alerts
         * @return This object
         */
        public Builder key(String key)
        {
            configuration.setKey(key);
            return this;
        }

        /**
         * Sets the route key of the VictorOps alerts.
         * @param route_key The route key of the alerts
         * @return This object
         */
        public Builder routeKey(String route_key)
        {
            configuration.setRouteKey(route_key);
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
         * Returns the configured VictorOps channel instance
         * @return The VictorOps channel instance
         */
        public VictorOpsChannel build()
        {
            return channel;
        }
    }
}