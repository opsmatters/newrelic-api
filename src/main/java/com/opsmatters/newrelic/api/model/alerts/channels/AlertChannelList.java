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
import java.util.Map;
import java.util.LinkedHashMap;

/**
 * Adds lookup functions to a list of New Relic alert channels.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class AlertChannelList
{
    private Map<String,AlertChannel> names = new LinkedHashMap<String,AlertChannel>();
    private Map<Long,AlertChannel> ids = new LinkedHashMap<Long,AlertChannel>();

    /**
     * Default constructor.
     */
    public AlertChannelList()
    {
    }

    /**
     * Constructor that takes a list of alert channels.
     * @param channels The alert channels for the list
     */
    public AlertChannelList(List<AlertChannel> channels)
    {
        add(channels);
    }

    /**
     * Adds a list of alert channels.
     * @param channels The alert channels to add
     */
    public void add(List<AlertChannel> channels)
    {
        for(AlertChannel channel : channels)
        {
            names.put(channel.getName(), channel);
            if(channel.getId() != null)
                ids.put(channel.getId(), channel);
        }
    }

    /**
     * Returns the first alert channel for the given name.
     * @param name The name of the alert channel
     * @return The first alert channel for the given name
     */
    public AlertChannel get(String name)
    {
        return names.get(name);
    }

    /**
     * Returns the alert channel for the given id.
     * @param id The id of the alert channel
     * @return The alert channel for the given id
     */
    public AlertChannel get(long id)
    {
        return ids.get(id);
    }

    /**
     * Returns the number of alert channels.
     * @return The number of alert channels
     */
    public int size()
    {
        return ids.size();
    }
}