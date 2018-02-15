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

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * Represents a New Relic Insights dashboard inventory data source.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class InventoryData extends WidgetData
{
    // The field names
    public static final String SOURCES = "sources";
    public static final String FILTERS = "filters";

    private List<String> sources;

    private Map<String,String> filters;

    /**
     * Default constructor.
     */
    public InventoryData()
    {
    }

    /**
     * Sets the list of sources for the widget.
     * @param sources The list of sources for the widget
     */
    public void setSources(List<String> sources)
    {
        this.sources = sources;
    }

    /**
     * Adds the given source to the list of sources for the widget.
     * @param source The source to add to the list of sources
     */
    public void addSource(String source)
    {
        if(this.sources == null)
            this.sources = new ArrayList<String>();
        this.sources.add(source);
    }

    /**
     * Returns the list of sources for the widget.
     * @return The list of sources for the widget
     */
    public List<String> getSources()
    {
        return sources;
    }

    /**
     * Sets the list of filters for the widget.
     * @param filters The list of filters for the widget
     */
    public void setFilters(Map<String,String> filters)
    {
        this.filters = filters;
    }

    /**
     * Adds the given filter to the list of filters for the widget.
     * @param name The name of the filter to add
     * @param value The value of the filter to add
     */
    public void addFilter(String name, String value)
    {
        if(this.filters == null)
            this.filters = new HashMap<String,String>();
        this.filters.put(name, value);
    }

    /**
     * Returns the list of filters for the widget.
     * @return The list of filters for the widget
     */
    public Map<String,String> getFilters()
    {
        return filters;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "InventoryData [sources="+sources
            +", filters="+filters
            +"]";
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
        private InventoryData data = new InventoryData();

        /**
         * Sets the sources of the widget data.
         * @param sources The sources of the widget data
         * @return This object
         */
        public Builder sources(List<String> sources)
        {
            data.setSources(sources);
            return this;
        }

        /**
         * Adds the given source to the list of sources for the widget.
         * @param source The source to add to the list of sources
         * @return This object
         */
        public Builder addSource(String source)
        {
            data.addSource(source);
            return this;
        }

        /**
         * Sets the filters of the widget data.
         * @param filters The filters of the widget data
         * @return This object
         */
        public Builder filters(Map<String,String> filters)
        {
            data.setFilters(filters);
            return this;
        }

        /**
         * Adds the given filter to the list of filters for the widget.
         * @param name The name of the filter to add
         * @param value The value of the filter to add
         * @return This object
         */
        public Builder addFilter(String name, String value)
        {
            data.addFilter(name, value);
            return this;
        }

        /**
         * Returns the configured widget data instance
         * @return The widget data instance
         */
        public InventoryData build()
        {
            return data;
        }
    }
}