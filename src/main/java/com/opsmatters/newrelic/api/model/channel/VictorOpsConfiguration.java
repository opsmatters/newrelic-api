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

/**
 * Represents a New Relic VictorOps channel configuration.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class VictorOpsConfiguration extends ChannelConfiguration
{
    private String key;
    private String route_key;

    /**
     * Default constructor.
     */
    public VictorOpsConfiguration()
    {
        super("victorops");
    }
   
    /**
     * Constructor that takes a route key.
     * @param route_key The route key
     */
    public VictorOpsConfiguration(String route_key)
    {
        this();
        setRouteKey(route_key);
    }

    /**
     * Sets the key of the alerts.
     * @param key The key of the alerts
     */
    public void setKey(String key)
    {
        this.key = key;
    }

    /**
     * Returns the key of the alerts.
     * @return The key of the alerts
     */
    public String getKey()
    {
        return key;
    }

    /**
     * Sets the route key of the alerts.
     * @param route_key The route key of the alerts
     */
    public void setRouteKey(String route_key)
    {
        this.route_key = route_key;
    }

    /**
     * Returns the route key of the alerts.
     * @return The route key of the alerts
     */
    public String getRouteKey()
    {
        return route_key;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "VictorOpsConfiguration ["+super.toString()
            +", key="+key
            +", route_key="+route_key
            +"]";
    }

    /**
     * Returns a builder for the VictorOps configuration.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make VictorOps configuration construction easier.
     */
    public static class Builder
    {
        private VictorOpsConfiguration configuration = new VictorOpsConfiguration();

        /**
         * Sets the key of the VictorOps configuration.
         * @param key The key of the alerts
         * @return This object
         */
        public Builder key(String key)
        {
            configuration.setKey(key);
            return this;
        }

        /**
         * Sets the route key of the VictorOps configuration.
         * @param route_key The route key of the alerts
         * @return This object
         */
        public Builder routeKey(String route_key)
        {
            configuration.setRouteKey(route_key);
            return this;
        }

        /**
         * Returns the configured VictorOps configuration instance
         * @return The VictorOps configuration instance
         */
        public VictorOpsConfiguration build()
        {
            return configuration;
        }
    }
}