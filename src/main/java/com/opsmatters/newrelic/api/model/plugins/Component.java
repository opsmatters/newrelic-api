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

package com.opsmatters.newrelic.api.model.plugins;

import java.util.Map;
import java.util.HashMap;
import com.opsmatters.newrelic.api.model.NamedResource;

/**
 * Represents a New Relic plugin agent data.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class Component implements NamedResource
{
    private String name;

    private String guid;

    private Integer duration;

    private Map<String,Object> metrics = new HashMap<String,Object>();

    /**
     * Default constructor.
     */
    public Component()
    {
    }

    /**
     * Sets the name of the component.
     * @param name The name of the component
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Returns the name of the component.
     * @return The name of the component
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the guid of the component.
     * @param guid The guid of the component
     */
    public void setGuid(String guid)
    {
        this.guid = guid;
    }

    /**
     * Returns the guid of the component.
     * @return The guid of the component
     */
    public String getGuid()
    {
        return guid;
    }

    /**
     * Sets the duration over which the metric data was collected (in seconds).
     * @param duration The duration of the metric data
     */
    public void setDuration(Integer duration)
    {
        this.duration = duration;
    }

    /**
     * Returns the duration over which the metric data was collected (in seconds).
     * @return The duration of the metric data
     */
    public Integer getDuration()
    {
        return duration;
    }

    /**
     * Returns the set of metrics.
     * @return The set of metrics
     */
    public Map<String,Object> getMetrics()
    {
        return metrics;
    }

    /**
     * Adds an integer metric to the set of metrics.
     * @param name The name of the metric
     * @param value The value of the metric timeslice
     */
    public void addMetric(String name, int value)
    {
        metrics.put(name, value);
    }

    /**
     * Adds a floating-point metric to the set of metrics.
     * @param name The name of the metric
     * @param value The value of the metric timeslice
     */
    public void addMetric(String name, double value)
    {
        metrics.put(name, value);
    }

    /**
     * Adds an integer metric to the set of metrics.
     * @param name The name of the metric
     * @param values An array of 5 values representing the metric timeslice
     */
    public void addMetric(String name, int[] values)
    {
        metrics.put(name, values);
    }

    /**
     * Adds a floating-point metric to the set of metrics.
     * @param name The name of the metric
     * @param values An array of 5 values representing the metric timeslice
     */
    public void addMetric(String name, double[] values)
    {
        metrics.put(name, values);
    }

    /**
     * Adds a metric to the set of metrics.
     * @param <T> The type parameter used for the timeslice
     * @param name The name of the metric
     * @param timeslice The values representing the metric timeslice
     */
    public <T> void addMetric(String name, MetricTimeslice<T> timeslice)
    {
        metrics.put(name, timeslice);
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "Component [name="+name
            +", guid="+guid
            +", duration="+duration
            +"]";
    }

    /**
     * Returns a builder for the plugin component.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make plugin data construction easier.
     */
    public static class Builder
    {
        private Component component = new Component();

        /**
         * Sets the name of the component.
         * @param name The name of the component
         * @return This object
         */
        public Builder name(String name)
        {
            component.setName(name);
            return this;
        }

        /**
         * Sets the duration over which the metric data was collected (in seconds).
         * @param duration The duration of the metric data
         * @return This object
         */
        public Builder duration(int duration)
        {
            component.setDuration(duration);
            return this;
        }

        /**
         * Sets the guid of the component.
         * @param guid The guid of the component
         * @return This object
         */
        public Builder guid(String guid)
        {
            component.setGuid(guid);
            return this;
        }

        /**
         * Adds an integer metric to the set of metrics.
         * @param name The name of the metric
         * @param value The value of the metric timeslice
         * @return This object
         */
        public Builder addMetric(String name, int value)
        {
            component.addMetric(name, value);
            return this;
        }

        /**
         * Adds a floating-point metric to the set of metrics.
         * @param name The name of the metric
         * @param value The value of the metric timeslice
         * @return This object
         */
        public Builder addMetric(String name, double value)
        {
            component.addMetric(name, value);
            return this;
        }

        /**
         * Adds an integer metric to the set of metrics.
         * @param name The name of the metric
         * @param values An array of 5 values representing the metric timeslice
         * @return This object
         */
        public Builder addMetric(String name, int[] values)
        {
            component.addMetric(name, values);
            return this;
        }

        /**
         * Adds a floating-point metric to the set of metrics.
         * @param name The name of the metric
         * @param values An array of 5 values representing the metric timeslice
         * @return This object
         */
        public Builder addMetric(String name, double[] values)
        {
            component.addMetric(name, values);
            return this;
        }

        /**
         * Adds an integer metric to the set of metrics.
         * @param <T> The type parameter used for the timeslice
         * @param name The name of the metric
         * @param timeslice The values representing the metric timeslice
         * @return This object
         */
        public <T> Builder addMetric(String name, MetricTimeslice<T> timeslice)
        {
            component.addMetric(name, timeslice);
            return this;
        }

        /**
         * Returns the configured component instance
         * @return The component instance
         */
        public Component build()
        {
            return component;
        }
    }
}