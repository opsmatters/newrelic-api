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
 * Represents a New Relic channel configuration.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class ChannelConfiguration
{
    private transient String type;

    /**
     * Constructor that takes a channel type.
     * @param type The channel type
     */
    public ChannelConfiguration(String type)
    {
        setType(type);
    }

    /**
     * Sets the channel type for the alerts.
     * @param type The channel type for the alerts
     */
    public void setType(String type)
    {
        this.type = type;
    }

    /**
     * Returns the channel type for the alerts.
     * @return The channel type for the alerts
     */
    public String getType()
    {
        return type;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "type="+type;
    }
}