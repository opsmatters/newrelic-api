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

import com.google.gson.annotations.SerializedName;

/**
 * Represents a New Relic VictorOps channel configuration.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class VictorOpsConfiguration extends ChannelConfiguration
{
    /**
     * The type of the channel configuration.
     */
    public static final ChannelType TYPE = ChannelType.VICTOROPS;

    private String key;

    @SerializedName("route_key")
    private String routeKey;

    /**
     * Default constructor.
     */
    public VictorOpsConfiguration()
    {
        super(TYPE.value());
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
     * @param routeKey The route key of the alerts
     */
    public void setRouteKey(String routeKey)
    {
        this.routeKey = routeKey;
    }

    /**
     * Returns the route key of the alerts.
     * @return The route key of the alerts
     */
    public String getRouteKey()
    {
        return routeKey;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "VictorOpsConfiguration ["+super.toString()
            +", key="+key
            +", routeKey="+routeKey
            +"]";
    }
}