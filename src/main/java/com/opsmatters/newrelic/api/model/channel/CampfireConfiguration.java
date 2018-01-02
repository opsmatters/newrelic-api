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
 * Represents a New Relic Campfire channel configuration.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class CampfireConfiguration extends ChannelConfiguration
{
    private String subdomain;
    private String token;
    private String room;

    /**
     * Default constructor.
     */
    public CampfireConfiguration()
    {
        super("campfire");
    }
   
    /**
     * Constructor that takes a room.
     * @param room The room
     */
    public CampfireConfiguration(String room)
    {
        this();
        setRoom(room);
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

    /**
     * Returns a builder for the Campfire configuration.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make Campfire configuration construction easier.
     */
    public static class Builder
    {
        private CampfireConfiguration configuration = new CampfireConfiguration();

        /**
         * Sets the room of the Campfire configuration.
         * @param room The room of the alerts
         * @return This object
         */
        public Builder room(String room)
        {
            configuration.setRoom(room);
            return this;
        }

        /**
         * Sets the token of the Campfire configuration.
         * @param token The token of the alerts
         * @return This object
         */
        public Builder token(String token)
        {
            configuration.setToken(token);
            return this;
        }

        /**
         * Sets the subdomain of the Campfire configuration.
         * @param subdomain The subdomain of the alerts
         * @return This object
         */
        public Builder subdomain(String subdomain)
        {
            configuration.setSubdomain(subdomain);
            return this;
        }

        /**
         * Returns the configured Campfire configuration instance
         * @return The Campfire configuration instance
         */
        public CampfireConfiguration build()
        {
            return configuration;
        }
    }
}