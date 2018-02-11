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
 * Represents a New Relic Insights event chart with thresholds.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class ThresholdEventChart extends Widget
{
    /**
     * Represents the available visualizations for this widget type.  
     */
    public enum Visualization
    {
        BILLBOARD("billboard"),
        BILLBOARD_COMPARISON("billboard_comparison"),
        GAUGE("gauge");

        Visualization(String value)
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
        public static Visualization fromValue(String value)
        {
            Visualization[] types = values();
            for(Visualization type : types)
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

    /**
     * Default constructor.
     */
    public ThresholdEventChart()
    {
    }

    /**
     * Sets the visualization of the widget.
     * @param visualization The visualization of the widget
     */
    public void setVisualization(Visualization visualization)
    {
        setVisualization(visualization.value());
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "ThresholdEventChart ["+super.toString()+"]";
    }

    /**
     * Returns a builder for the widget.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make widget construction easier.
     */
    public static class Builder extends Widget.Builder<ThresholdEventChart, Builder>
    {
        private ThresholdEventChart widget = new ThresholdEventChart();
        private ThresholdPresentation presentation = new ThresholdPresentation();

        /**
         * Default constructor.
         */
        public Builder()
        {
            widget.setPresentation(presentation);
            widget(widget);
        }

        /**
         * Sets the visualization of the widget.
         * @param visualization The visualization of the widget
         * @return This object
         */
        public Builder visualization(Visualization visualization)
        {
            widget.setVisualization(visualization);
            return this;
        }

        /**
         * Sets the presentation of the widget.
         * @param presentation The presentation of the widget
         * @return This object
         */
        public Builder presentation(ThresholdPresentation presentation)
        {
            widget.setPresentation(presentation);
            return this;
        }

        /**
         * Sets the threshold of the presentation.
         * @param threshold The threshold of the presentation
         * @return This object
         */
        public Builder threshold(Threshold threshold)
        {
            presentation.setThreshold(threshold);
            return this;
        }

        /**
         * Adds an item to the list of widget data items.
         * @param data The list of widget data items
         * @return This object
         */
        public Builder addData(EventsData data)
        {
            if(data != null)
                widget.addData(data);
            return this;
        }

        /**
         * Adds the given nrql to the list of widget data items.
         * @param nrql The nrql of the widget data
         * @return This object
         */
        public Builder addNrqlData(String nrql)
        {
            if(nrql != null)
                widget.addData(new EventsData(nrql));
            return this;
        }

        /**
         * Returns this object.
         * @return This object
         */
        @Override
        protected Builder self()
        {
            return this;
        }

        /**
         * Returns the configured widget instance
         * @return The widget instance
         */
        @Override
        public ThresholdEventChart build()
        {
            return widget;
        }
    }
}