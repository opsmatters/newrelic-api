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

/**
 * Represents a New Relic metric with a set of values.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class Metric
{
    private String name;
    private List<String> values = new ArrayList<String>();

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
     * Sets the list of values.
     * @param values The list of values
     */
    public void setValues(List<String> values)
    {
        this.values.clear();
        this.values.addAll(values);
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
            +", values="+values
            +"]";
    }
}