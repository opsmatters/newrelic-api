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

package com.opsmatters.newrelic.api.services;

import java.util.Collection;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import com.google.common.base.Optional;
import com.google.common.collect.Maps;
import com.opsmatters.newrelic.api.NewRelicClient;
import com.opsmatters.newrelic.api.model.alerts.channels.AlertChannel;

/**
 * The set of operations used for alert channels.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class AlertChannelService extends BaseFluent
{
    /**
     * Constructor that takes a http context and API client.
     * @param httpContext The set of HTTP operations
     * @param client The client used to invoke the New Relic operations
     */
    public AlertChannelService(HttpContext httpContext, NewRelicClient client)
    {
        super(httpContext, client);
    }

    /**
     * Returns the set of alert channels.
     * @return The set of alert channels
     */
    public Collection<AlertChannel> list()
    {
        return HTTP.GET("/v2/alerts_channels.json", null, null, ALERT_CHANNELS).get();
    }

    /**
     * Returns the set of alert channels with the given name.
     * @param name The name of the alert channels to return
     * @return The set of alert channels
     */
    public Collection<AlertChannel> list(String name)
    {
        List<AlertChannel> ret = new ArrayList<AlertChannel>();
        Collection<AlertChannel> channels = list();
        for(AlertChannel channel : channels)
        {
            if(channel.getName().equals(name))
                ret.add(channel);
        }
        return ret;
    }

    /**
     * Returns the set of alert channels with the given name and type.
     * @param name The name of the alert channels to return
     * @param type The type of the alert channels to return
     * @return The set of alert channels
     */
    public Collection<AlertChannel> list(String name, String type)
    {
        List<AlertChannel> ret = new ArrayList<AlertChannel>();
        Collection<AlertChannel> channels = list();
        for(AlertChannel channel : channels)
        {
            if((name == null || channel.getName().equals(name))
                && (type == null || channel.getType().equals(type)))
            {
                ret.add(channel);
            }
        }
        return ret;
    }

    /**
     * Returns the alert channel with the given id.
     * <P>
     * This is needed because the API does not contain an operation to get a channel using the id directly.
     * @param channelId The id of the alert channel to return
     * @return The alert channel
     */
    public Optional<AlertChannel> show(long channelId)
    {
        Optional<AlertChannel> ret = Optional.absent();
        Collection<AlertChannel> channels = list();
        for(AlertChannel channel : channels)
        {
            if(channel.getId() == channelId)
                ret = Optional.of(channel);
        }
        return ret;
    }

    /**
     * Creates the given alert channel.
     * @param channel The alert channel to create
     * @return The alert channel that was created
     */
    public Optional<AlertChannel> create(AlertChannel channel)
    {
        return Optional.of(HTTP.POST("/v2/alerts_channels.json", channel, ALERT_CHANNELS).get().iterator().next());
    }

    /**
     * Deletes the alert channel with the given id.
     * @param channelId The id of the alert channel to delete
     * @return This object
     */
    public AlertChannelService delete(long channelId)
    {
        HTTP.DELETE(String.format("/v2/alerts_channels/%d.json", channelId));
        return this;
    }
}
