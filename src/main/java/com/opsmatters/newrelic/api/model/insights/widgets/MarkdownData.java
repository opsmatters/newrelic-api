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
 * Represents a New Relic Insights dashboard markdown data source.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class MarkdownData extends WidgetData
{
    private String source;

    /**
     * Default constructor.
     */
    public MarkdownData()
    {
    }

    /**
     * Constructor that takes markdown.
     * @param source The markdown of the widget
     */
    public MarkdownData(String source)
    {
        setSource(source);
    }

    /**
     * Sets the source of the widget.
     * @param source The source of the widget
     */
    public void setSource(String source)
    {
        this.source = source;
    }

    /**
     * Returns the source of the widget.
     * @return The source of the widget
     */
    public String getSource()
    {
        return source;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "MarkdownData [source="+source+"]";
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
        private MarkdownData data = new MarkdownData();

        /**
         * Sets the source of the widget data.
         * @param source The source of the widget data
         * @return This object
         */
        public Builder source(String source)
        {
            data.setSource(source);
            return this;
        }

        /**
         * Returns the configured widget data instance
         * @return The widget data instance
         */
        public MarkdownData build()
        {
            return data;
        }
    }
}