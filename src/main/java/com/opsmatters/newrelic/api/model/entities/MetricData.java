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
import java.util.Date;
import com.google.gson.annotations.SerializedName;

/**
 * Represents a New Relic metric with a set of data values.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class MetricData
{
    private Date from;
    private Date to;

    @SerializedName("metrics_found")
    private List<String> metricsFound;

    @SerializedName("metrics_not_found")
    private List<String> metricsNotFound;

    private List<MetricTimeslices> metrics = new ArrayList<MetricTimeslices>();

    /**
     * Default constructor.
     */
    public MetricData()
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
     * Sets the metrics found.
     * @param metricsFound The metrics found
     */
    public void setMetricsFound(List<String> metricsFound)
    {
        this.metricsFound = metricsFound;
    }

    /**
     * Returns the metrics found.
     * @return The metrics found
     */
    public List<String> getMetricsFound()
    {
        return metricsFound;
    }

    /**
     * Sets the metrics not found.
     * @param metricsNotFound The metrics not found
     */
    public void setMetricsNotFound(List<String> metricsNotFound)
    {
        this.metricsNotFound = metricsNotFound;
    }

    /**
     * Returns the metrics not found.
     * @return The metrics not found
     */
    public List<String> getMetricsNotFound()
    {
        return metricsNotFound;
    }

    /**
     * Sets the list of metrics.
     * @param metrics The list of metrics
     */
    public void setMetrics(List<MetricTimeslices> metrics)
    {
        this.metrics.clear();
        this.metrics.addAll(metrics);
    }

    /**
     * Returns the list of metrics.
     * @return The list of metrics
     */
    public List<MetricTimeslices> getMetrics()
    {
        return metrics;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "MetricData [from="+from
            +", to="+to
            +", metricsFound="+metricsFound
            +", metricsNotFound="+metricsNotFound
            +", metrics="+metrics
            +"]";
    }
}