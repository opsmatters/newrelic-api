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

package com.opsmatters.newrelic.api.model.metrics;

import java.util.List;
import java.util.ArrayList;
import com.opsmatters.newrelic.api.model.NamedResource;

/**
 * Represents a New Relic metric with a set of values.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class Metric implements NamedResource
{
    private String name;
    private String units;
    private String scope;
    private List<String> values;

    /**
     * Default constructor.
     */
    public Metric()
    {
    }

    /**
     * Sets the name of the metric.
     * @param name The name of the metric
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Returns the name of the metric.
     * @return The name of the metric
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the units of the metric.
     * @param units The units of the metric
     */
    public void setUnits(String units)
    {
        this.units = units;
    }

    /**
     * Returns the units of the metric.
     * @return The units of the metric
     */
    public String getUnits()
    {
        return units;
    }

    /**
     * Sets the scope of the metric.
     * @param scope The scope of the metric
     */
    public void setScope(String scope)
    {
        this.scope = scope;
    }

    /**
     * Returns the scope of the metric.
     * @return The scope of the metric
     */
    public String getScope()
    {
        return scope;
    }

    /**
     * Sets the list of values.
     * @param values The list of values
     */
    public void setValues(List<String> values)
    {
        this.values.clear();
        this.values.addAll(values);
    }

    /**
     * Adds a value to the list of values.
     * @param value The value to add to the list of values
     */
    public void addValue(String value)
    {
        if(this.values == null)
            this.values = new ArrayList<String>();
        this.values.add(value);
    }

    /**
     * Returns the list of values.
     * @return The list of values
     */
    public List<String> getValues()
    {
        return values;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "Metric [name="+name
            +", units="+units
            +", scope="+scope
            +", values="+values
            +"]";
    }

    /**
     * Returns a builder for the metric.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make metric construction easier.
     */
    public static class Builder
    {
        private Metric metric = new Metric();

        /**
         * Sets the name of the metric.
         * @param name The name of the metric
         * @return This object
         */
        public Builder name(String name)
        {
            metric.setName(name);
            return this;
        }

        /**
         * Sets the scope of the metric.
         * @param scope The scope of the metric
         * @return This object
         */
        public Builder scope(String scope)
        {
            metric.setScope(scope);
            return this;
        }

        /**
         * Sets the units of the metric.
         * @param units The units of the metric
         * @return This object
         */
        public Builder units(String units)
        {
            metric.setUnits(units);
            return this;
        }

        /**
         * Sets the values of the metric.
         * @param values The values of the metric
         * @return This object
         */
        public Builder values(List<String> values)
        {
            metric.setValues(values);
            return this;
        }

        /**
         * Adds the given value to the list of values for the metric.
         * @param value The value to add to the list of values
         * @return This object
         */
        public Builder addValue(String value)
        {
            metric.addValue(value);
            return this;
        }

        /**
         * Returns the configured widget data instance
         * @return The widget data instance
         */
        public Metric build()
        {
            return metric;
        }
    }
}