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
 * Represents a New Relic Insights event facet chart.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class FacetChart extends Widget
{
    /**
     * Represents the available visualizations for this widget type.  
     */
    public enum Visualization
    {
        FACET_BAR_CHART("facet_bar_chart"),
        FACETED_LINE_CHART("faceted_line_chart"),
        FACET_PIE_CHART("facet_pie_chart"),
        FACET_TABLE("facet_table"),
        FACETED_AREA_CHART("faceted_area_chart"),
        HEATMAP("heatmap");

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
    public FacetChart()
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
        return "FacetChart ["+super.toString()+"]";
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
    public static class Builder extends Widget.Builder<FacetChart, Builder>
    {
        private FacetChart widget = new FacetChart();
        private DrilldownPresentation presentation = new DrilldownPresentation();

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
        public Builder presentation(DrilldownPresentation presentation)
        {
            widget.setPresentation(presentation);
            return this;
        }

        /**
         * Sets the drilldown dashboard id of the presentation.
         * @param drilldownDashboardId The drilldown dashboard id of the presentation
         * @return This object
         */
        public Builder drilldownDashboardId(long drilldownDashboardId)
        {
            presentation.setDrilldownDashboardId(drilldownDashboardId);
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
        public FacetChart build()
        {
            return widget;
        }
    }
}