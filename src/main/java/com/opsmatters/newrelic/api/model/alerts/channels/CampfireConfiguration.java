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

/**
 * Represents a New Relic Campfire channel configuration.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class CampfireConfiguration extends ChannelConfiguration
{
    /**
     * The type of the channel configuration.
     */
    public static final ChannelType TYPE = ChannelType.CAMPFIRE;

    private String subdomain;
    private String token;
    private String room;

    /**
     * Default constructor.
     */
    public CampfireConfiguration()
    {
        super(TYPE.value());
    }
   
    /**
     * Sets the room of the alerts.
     * @param room The room of the alerts
     */
    public void setRoom(String room)
    {
        this.room = room;
    }

    /**
     * Returns the room of the alerts.
     * @return The room of the alerts
     */
    public String getRoom()
    {
        return room;
    }

    /**
     * Sets the token for the alerts.
     * @param token The token for the alerts
     */
    public void setToken(String token)
    {
        this.token = token;
    }

    /**
     * Returns the token for the alerts.
     * @return The token for the alerts
     */
    public String getToken()
    {
        return token;
    }

    /**
     * Sets the subdomain for the alerts.
     * @param subdomain The subdomain for the alerts
     */
    public void setSubdomain(String subdomain)
    {
        this.subdomain = subdomain;
    }

    /**
     * Returns the subdomain for the alerts.
     * @return The subdomain for the alerts
     */
    public String getSubdomain()
    {
        return subdomain;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "CampfireConfiguration ["+super.toString()
            +", room="+room
            +", token="+token
            +", subdomain="+subdomain
            +"]";
    }
}