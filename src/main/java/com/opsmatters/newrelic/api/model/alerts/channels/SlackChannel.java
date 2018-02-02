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
 * Represents a New Relic Slack alert channel.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class SlackChannel extends AlertChannel<SlackConfiguration>
{
    /**
     * Default constructor.
     */
    public SlackChannel()
    {
        this(new SlackConfiguration());
    }

    /**
     * Constructor that takes a configuration.
     * @param configuration The alert channel configuration
     */
    public SlackChannel(SlackConfiguration configuration)
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
        return "SlackChannel ["+super.toString()+"]";
    }

    /**
     * Returns a builder for the Slack channel.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make Slack channel construction easier.
     */
    public static class Builder extends AlertChannel.Builder<SlackChannel, Builder>
    {
        private SlackConfiguration configuration = new SlackConfiguration();
        private SlackChannel channel = new SlackChannel(configuration);

        /**
         * Default constructor.
         */
        public Builder()
        {
            channel(channel);
        }

        /**
         * Sets the channel of the Slack alerts.
         * @param channel The channel of the alerts
         * @return This object
         */
        public Builder channel(String channel)
        {
            configuration.setChannel(channel);
            return this;
        }

        /**
         * Sets the URL of the Slack alerts.
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
         * Returns the configured Slack channel instance
         * @return The Slack channel instance
         */
        public SlackChannel build()
        {
            return channel;
        }
    }
}