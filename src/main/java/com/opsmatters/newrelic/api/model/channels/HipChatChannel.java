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
 * Represents a New Relic HipChat alert channel.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class HipChatChannel extends AlertChannel
{
    /**
     * Default constructor.
     */
    public HipChatChannel()
    {
        this(new HipChatConfiguration());
    }

    /**
     * Constructor that takes a configuration.
     * @param configuration The alert channel configuration
     */
    public HipChatChannel(HipChatConfiguration configuration)
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
        return "HipChatChannel ["+super.toString()+"]";
    }

    /**
     * Returns a builder for the HipChat channel.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make HipChat channel construction easier.
     */
    public static class Builder extends AlertChannel.Builder<HipChatChannel, Builder>
    {
        private HipChatConfiguration configuration = new HipChatConfiguration();
        private HipChatChannel channel = new HipChatChannel(configuration);

        /**
         * Default constructor.
         */
        public Builder()
        {
            channel(channel);
        }

        /**
         * Sets the room id of the HipChat alerts.
         * @param room_id The room id of the alerts
         * @return This object
         */
        public Builder roomId(String room_id)
        {
            configuration.setRoomId(room_id);
            return this;
        }

        /**
         * Sets the auth token of the HipChat alerts.
         * @param auth_token The auth token of the alerts
         * @return This object
         */
        public Builder authToken(String auth_token)
        {
            configuration.setAuthToken(auth_token);
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
         * Returns the configured HipChat channel instance
         * @return The HipChat channel instance
         */
        public HipChatChannel build()
        {
            return channel;
        }
    }
}