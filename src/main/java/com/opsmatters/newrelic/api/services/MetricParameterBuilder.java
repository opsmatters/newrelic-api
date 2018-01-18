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

package com.opsmatters.newrelic.api.services;

import java.util.List;
import com.opsmatters.newrelic.util.Utils;
import com.opsmatters.newrelic.util.QueryParameterList;

/**
 * Builder to make metric parameter construction easier.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class MetricParameterBuilder
{
    private QueryParameterList parameters = new QueryParameterList();

    /**
     * Returns a builder for the metric data parameters.
     * @return The builder instance.
     */
    public static MetricParameterBuilder builder()
    {
        return new MetricParameterBuilder();
    }

    /**
    * Adds a names parameter to the parameters.
    * @param name The metric name to add
    * @return This object
    */
    public MetricParameterBuilder names(String name)
    {
        parameters.add("names[]", name);
        return this;
    }

    /**
     * Adds a values parameter to the parameters.
     * @param value The metric value to add
     * @return This object
     */
    public MetricParameterBuilder values(String value)
    {
        parameters.add("values[]", value);
        return this;
    }

    /**
     * Adds the from parameter to the parameters.
     * @param from The from date parameter to add
     * @return This object
     */
    public MetricParameterBuilder from(long from)
    {
        parameters.add("from", Utils.getUtcDateTime(from));
        return this;
    }

    /**
     * Adds the to parameter to the parameters.
     * @param to The to date parameter to add
     * @return This object
     */
    public MetricParameterBuilder to(long to)
    {
        parameters.add("to", Utils.getUtcDateTime(to));
        return this;
    }

    /**
     * Adds the period parameter to the parameters.
     * @param period The period of timeslices in seconds
     * @return This object
     */
    public MetricParameterBuilder period(int period)
    {
        parameters.add("period", Integer.toString(period));
        return this;
    }

    /**
     * Adds the summarize parameter to the parameters.
     * @param summarize <CODE>true</CODE> if the metric data should be summarized
     * @return This object
     */
    public MetricParameterBuilder summarize(boolean summarize)
    {
        parameters.add("summarize", Boolean.toString(summarize));
        return this;
    }

    /**
     * Adds the raw parameter to the parameters.
     * @param raw <CODE>true</CODE> if the metric data should be returned unformatted
     * @return This object
     */
    public MetricParameterBuilder raw(boolean raw)
    {
        parameters.add("raw", Boolean.toString(raw));
        return this;
    }

    /**
     * Returns the configured parameters
     * @return The parameters
     */
    public List<String> build()
    {
        if(!parameters.contains("names[]"))
            throw new IllegalArgumentException("parameter list must contain at least one names[] parameter");
        return parameters;
    }
}
