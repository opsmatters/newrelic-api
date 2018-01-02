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
 * Represents a New Relic HipChat channel configuration.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class HipChatConfiguration extends ChannelConfiguration
{
    private String auth_token;
    private String room_id;

    /**
     * Default constructor.
     */
    public HipChatConfiguration()
    {
        super("hipchat");
    }
   
    /**
     * Constructor that takes a room id.
     * @param room_id The room id
     */
    public HipChatConfiguration(String room_id)
    {
        this();
        setRoomId(room_id);
    }

    /**
     * Sets the HipChat room id for the alerts.
     * @param room_id The room id for the alerts
     */
    public void setRoomId(String room_id)
    {
        this.room_id = room_id;
    }

    /**
     * Returns the HipChat room id for the alerts.
     * @return The room id for the alerts
     */
    public String getRoomId()
    {
        return room_id;
    }

    /**
     * Sets the HipChat auth token for the alerts.
     * @param auth_token The auth token for the alerts
     */
    public void setAuthToken(String auth_token)
    {
        this.auth_token = auth_token;
    }

    /**
     * Returns the HipChat auth token for the alerts.
     * @return The auth token for the alerts
     */
    public String getAuthToken()
    {
        return auth_token;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "HipChatConfiguration ["+super.toString()
            +", room_id="+room_id
            +", auth_token="+auth_token
            +"]";
    }

    /**
     * Returns a builder for the HipChat configuration.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make HipChat configuration construction easier.
     */
    public static class Builder
    {
        private HipChatConfiguration configuration = new HipChatConfiguration();

        /**
         * Sets the room id of the HipChat configuration.
         * @param room_id The room id of the alerts
         * @return This object
         */
        public Builder roomId(String room_id)
        {
            configuration.setRoomId(room_id);
            return this;
        }

        /**
         * Sets the auth token of the HipChat configuration.
         * @param auth_token The auth token of the alerts
         * @return This object
         */
        public Builder authToken(String auth_token)
        {
            configuration.setAuthToken(auth_token);
            return this;
        }

        /**
         * Returns the configured HipChat configuration instance
         * @return The HipChat configuration instance
         */
        public HipChatConfiguration build()
        {
            return configuration;
        }
    }
}