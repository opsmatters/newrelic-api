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
 * Represents a New Relic xMatters alert channel.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class xMattersChannel extends AlertChannel
{
    /**
     * Default constructor.
     */
    public xMattersChannel()
    {
        this(new xMattersConfiguration());
    }

    /**
     * Constructor that takes a configuration.
     * @param configuration The alert channel configuration
     */
    public xMattersChannel(xMattersConfiguration configuration)
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
        return "xMattersChannel ["+super.toString()+"]";
    }

    /**
     * Returns a builder for the xMatters channel.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make xMatters channel construction easier.
     */
    public static class Builder extends AlertChannel.Builder<xMattersChannel, Builder>
    {
        private xMattersConfiguration configuration = new xMattersConfiguration();
        private xMattersChannel channel = new xMattersChannel(configuration);

        /**
         * Default constructor.
         */
        public Builder()
        {
            channel(channel);
        }

        /**
         * Sets the channel of the xMatters alerts.
         * @param channel The channel of the alerts
         * @return This object
         */
        public Builder channel(String channel)
        {
            configuration.setChannel(channel);
            return this;
        }

        /**
         * Sets the URL of the xMatters alerts.
         * @param url The url of the alerts
         * @return This object
         */
        public Builder url(String url)
        {
            configuration.setUrl(url);
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
         * Returns the configured xMatters channel instance
         * @return The xMatters channel instance
         */
        public xMattersChannel build()
        {
            return channel;
        }
    }
}