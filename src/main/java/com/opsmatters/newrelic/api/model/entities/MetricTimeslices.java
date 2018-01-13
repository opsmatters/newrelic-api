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

package com.opsmatters.newrelic.api.model.entities;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a set of New Relic metric timeslices.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class MetricTimeslices
{
    private String name;
    private List<MetricTimeslice> timeslices = new ArrayList<MetricTimeslice>();

    /**
     * Default constructor.
     */
    public MetricTimeslices()
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
     * Sets the list of timeslices.
     * @param timeslices The list of timeslices
     */
    public void setTimeslices(List<MetricTimeslice> timeslices)
    {
        this.timeslices.clear();
        this.timeslices.addAll(timeslices);
    }

    /**
     * Returns the list of timeslices.
     * @return The list of timeslices
     */
    public List<MetricTimeslice> getTimeslices()
    {
        return timeslices;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "MetricTimeslices [name="+name
            +", timeslices="+timeslices
            +"]";
    }
}