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

/**
 * Represents the available New Relic channel types.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public enum ChannelType
{
    USER("user"),
    EMAIL("email"),
    SLACK("slack"),
    HIPCHAT("hipchat"),
    OPSGENIE("opsgenie"),
    PAGERDUTY("pagerduty"),
    VICTOROPS("victorops"),
    CAMPFIRE("campfire"),
    XMATTERS("xmatters"),
    WEBHOOK("webhook");

    ChannelType(String value)
    {
        this.value = value;
    }

    public String value()
    {
        return value;
    }

    /**
     * Returns the type for the given value.
     * @param value The type value
     * @return The type for the given value
     */
    public static ChannelType fromValue(String value)
    {
        ChannelType[] types = values();
        for(ChannelType type : types)
        {
            if(type.value().equals(value))
                return type;
        }
        return null;
    }

    /**
     * Returns <CODE>true</CODE> if the given value is contained in the list of types.
     * @param value The type value
     * @return <CODE>true</CODE> if the given value is contained in the list of types
     */
    public static boolean contains(String value)
    {
        return fromValue(value) != null;
    }

    private String value;
}