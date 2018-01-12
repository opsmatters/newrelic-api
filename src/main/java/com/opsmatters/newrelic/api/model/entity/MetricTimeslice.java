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

package com.opsmatters.newrelic.api.model.entity;

import java.util.Map;
import java.util.HashMap;
import java.util.Date;
import com.google.gson.annotations.SerializedName;

/**
 * Represents a New Relic metric timeslice.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class MetricTimeslice
{
    private Date from;
    private Date to;

    private Map<String,Object> values = new HashMap<String,Object>();

    /**
     * Default constructor.
     */
    public MetricTimeslice()
    {
    }

    /**
     * Sets the from date for the metric values.
     * @param from The from date for the metric values
     */
    public void setFrom(Date from)
    {
        this.from = from;
    }

    /**
     * Returns the from date for the metric values.
     * @return The from date for the metric values
     */
    public Date getFrom()
    {
        return from;
    }

    /**
     * Sets the to date for the metric values.
     * @param to The to date for the metric values
     */
    public void setTo(Date to)
    {
        this.to = to;
    }

    /**
     * Returns the to date for the metric values.
     * @return The to date for the metric values
     */
    public Date getTo()
    {
        return to;
    }

    /**
     * Sets the list of values.
     * @param values The list of values
     */
    public void setValues(Map<String,Object> values)
    {
        this.values.clear();
        this.values.putAll(values);
    }

    /**
     * Returns the list of values.
     * @return The list of values
     */
    public Map<String,Object> getValues()
    {
        return values;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "MetricTimeslice [from="+from
            +", to="+to
            +", values="+values
            +"]";
    }
}