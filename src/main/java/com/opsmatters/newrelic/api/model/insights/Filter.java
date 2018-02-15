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

package com.opsmatters.newrelic.api.model.insights;

import java.util.List;
import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;

/**
 * Represents a New Relic Insights dashboard metadata.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class Filter
{
    // The field names
    public static final String EVENT_TYPES = "event_types";
    public static final String ATTRIBUTES = "attributes";

    @SerializedName("event_types")
    private List<String> eventTypes;

    private List<String> attributes;

    /**
     * Default constructor.
     */
    public Filter()
    {
    }

    /**
     * Sets the event types of the dashboard filter.
     * @param eventTypes The event types of the dashboard filter
     */
    public void setEventTypes(List<String> eventTypes)
    {
        this.eventTypes = eventTypes;
    }

    /**
     * Returns the event types of the dashboard filter.
     * @return The event types of the dashboard filter
     */
    public List<String> getEventTypes()
    {
        return eventTypes;
    }

    /**
     * Adds an event type to the dashboard filter.
     * @param eventType The event type to add to the dashboard filter
     */
    public void addEventType(String eventType)
    {
        if(this.eventTypes == null)
            this.eventTypes = new ArrayList<String>();
        this.eventTypes.add(eventType);
    }

    /**
     * Sets the attributes of the dashboard filter.
     * @param attributes The attributes of the dashboard filter
     */
    public void setAttributes(List<String> attributes)
    {
        this.attributes = attributes;
    }

    /**
     * Returns the attributes of the dashboard filter.
     * @return The attributes of the dashboard filter
     */
    public List<String> getAttributes()
    {
        return attributes;
    }

    /**
     * Adds an attribute to the dashboard filter.
     * @param attribute The attribute to add to the dashboard filter
     */
    public void addAttribute(String attribute)
    {
        if(this.attributes == null)
            this.attributes = new ArrayList<String>();
        this.attributes.add(attribute);
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "Filter [eventTypes="+eventTypes
            +", attributes="+attributes
            +"]";
    }

    /**
     * Returns a builder for the filter.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make filter construction easier.
     */
    public static class Builder
    {
        private Filter filter = new Filter();

        /**
         * Sets the event types of the dashboard filter.
         * @param eventTypes The event types of the dashboard filter
         * @return This object
         */
        public Builder eventTypes(List<String> eventTypes)
        {
            filter.setEventTypes(eventTypes);
            return this;
        }

        /**
         * Adds an event type to the dashboard filter.
         * @param eventType The event type to add to the dashboard filter
         * @return This object
         */
        public Builder addEventType(String eventType)
        {
            filter.addEventType(eventType);
            return this;
        }

        /**
         * Sets the attributes of the dashboard filter.
         * @param attributes The attributes of the dashboard filter
         * @return This object
         */
        public Builder attributes(List<String> attributes)
        {
            filter.setAttributes(attributes);
            return this;
        }

        /**
         * Adds an attribute to the dashboard filter.
         * @param attribute The attribute to add to the dashboard filter
         * @return This object
         */
        public Builder addAttribute(String attribute)
        {
            filter.addAttribute(attribute);
            return this;
        }

        /**
         * Returns the configured filter instance
         * @return The filter instance
         */
        public Filter build()
        {
            return filter;
        }
    }
}