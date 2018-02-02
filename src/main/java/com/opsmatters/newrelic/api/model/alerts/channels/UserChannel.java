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

/**
 * Represents a New Relic User alert channel.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class UserChannel extends AlertChannel<UserConfiguration>
{
    /**
     * Default constructor.
     */
    public UserChannel()
    {
        this(new UserConfiguration());
    }

    /**
     * Constructor that takes a configuration.
     * @param configuration The alert channel configuration
     */
    public UserChannel(UserConfiguration configuration)
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
        return "UserChannel ["+super.toString()+"]";
    }

    /**
     * Returns a builder for the User channel.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make User channel construction easier.
     */
    public static class Builder extends AlertChannel.Builder<UserChannel, Builder>
    {
        private UserConfiguration configuration = new UserConfiguration();
        private UserChannel channel = new UserChannel(configuration);

        /**
         * Default constructor.
         */
        public Builder()
        {
            channel(channel);
        }

        /**
         * Sets the user id of the User alerts.
         * @param userId The user id of the alerts
         * @return This object
         */
        public Builder userId(String userId)
        {
            configuration.setUserId(userId);
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
         * Returns the configured User channel instance
         * @return The User channel instance
         */
        public UserChannel build()
        {
            return channel;
        }
    }
}