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

/**
 * Represents a New Relic Slack channel configuration.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class SlackConfiguration extends ChannelConfiguration
{
    /**
     * The type of the channel configuration.
     */
    public static final ChannelType TYPE = ChannelType.SLACK;

    private String url;
    private String channel;

    /**
     * Default constructor.
     */
    public SlackConfiguration()
    {
        super(TYPE.value());
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
}