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
 * Represents a New Relic Slack channel configuration.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class SlackConfiguration extends ChannelConfiguration
{
    private String url;
    private String channel;

    /**
     * Default constructor.
     */
    public SlackConfiguration()
    {
        super("slack");
    }
   
    /**
     * Constructor that takes a channel.
     * @param channel The channel
     */
    public SlackConfiguration(String channel)
    {
        this();
        setChannel(channel);
    }

    /**
     * Sets the Slack channel for the alerts.
     * @param channel The channel for the alerts
     */
    public void setChannel(String channel)
    {
        this.channel = channel;
    }

    /**
     * Returns the Slack channel for the alerts.
     * @return The channel for the alerts
     */
    public String getChannel()
    {
        return channel;
    }

    /**
     * Sets the Slack URL for the alerts.
     * @param url The url for the alerts
     */
    public void setUrl(String url)
    {
        this.url = url;
    }

    /**
     * Returns the Slack url for the alerts.
     * @return The url for the alerts
     */
    public String getUrl()
    {
        return url;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "SlackConfiguration ["+super.toString()
            +", channel="+channel
            +", url="+url
            +"]";
    }

    /**
     * Returns a builder for the Slack configuration.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make Slack configuration construction easier.
     */
    public static class Builder
    {
        private SlackConfiguration configuration = new SlackConfiguration();

        /**
         * Sets the channel of the Slack configuration.
         * @param channel The channel of the alerts
         * @return This object
         */
        public Builder channel(String channel)
        {
            configuration.setChannel(channel);
            return this;
        }

        /**
         * Sets the URL of the Slack configuration.
         * @param url The url of the alerts
         * @return This object
         */
        public Builder url(String url)
        {
            configuration.setUrl(url);
            return this;
        }

        /**
         * Returns the configured Slack configuration instance
         * @return The Slack configuration instance
         */
        public SlackConfiguration build()
        {
            return configuration;
        }
    }
}