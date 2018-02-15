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

package com.opsmatters.newrelic.api.model.insights.widgets;

/**
 * Represents a New Relic Insights event data source.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class EventsData extends WidgetData
{
    // The field names
    public static final String NRQL = "nrql";

    private String nrql;

    /**
     * Default constructor.
     */
    public EventsData()
    {
    }

    /**
     * Constructor that takes a query.
     * @param nrql The NRQL query of the widget
     */
    public EventsData(String nrql)
    {
        setNrql(nrql);
    }

    /**
     * Sets the NRQL query of the widget.
     * @param nrql The NRQL query of the widget
     */
    public void setNrql(String nrql)
    {
        this.nrql = nrql;
    }

    /**
     * Returns the NRQL query of the widget.
     * @return The NRQL query of the widget
     */
    public String getNrql()
    {
        return nrql;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "EventsData [nrql="+nrql+"]";
    }

    /**
     * Returns a builder for the widget data.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make widget data construction easier.
     */
    public static class Builder
    {
        private EventsData data = new EventsData();

        /**
         * Sets the nrql of the widget data.
         * @param nrql The nrql of the widget data
         * @return This object
         */
        public Builder nrql(String nrql)
        {
            data.setNrql(nrql);
            return this;
        }

        /**
         * Returns the configured widget data instance
         * @return The widget data instance
         */
        public EventsData build()
        {
            return data;
        }
    }
}