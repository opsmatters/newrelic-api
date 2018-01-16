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

package com.opsmatters.newrelic.api;

import java.util.Collection;
import java.util.List;
import com.google.common.base.Optional;
import com.opsmatters.newrelic.api.model.applications.ApplicationInstance;
import com.opsmatters.newrelic.api.model.metrics.Metric;
import com.opsmatters.newrelic.api.model.metrics.MetricData;
import com.opsmatters.newrelic.util.QueryParameterList;

/**
 * The set of operations used for application instances.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class ApplicationInstanceOperations extends BaseFluent
{
    /**
     * Constructor that takes a http context and API service.
     * @param httpContext The set of HTTP operations
     * @param service The set of API operations
     */
    public ApplicationInstanceOperations(HttpContext httpContext, NewRelicService service)
    {
        super(httpContext, service);
    }

    /**
     * Returns the set of application instances with the given query parameters.
     * @param applicationId The application id
     * @param queryParams The query parameters
     * @return The set of application instances
     */
    public Collection<ApplicationInstance> list(long applicationId, List<String> queryParams)
    {
        return HTTP.GET(String.format("/v2/applications/%d/instances.json", applicationId), null, queryParams, APPLICATION_INSTANCES).get();
    }

    /**
     * Returns the set of application instances.
     * @param applicationId The application id
     * @return The set of application instances
     */
    public Collection<ApplicationInstance> list(long applicationId)
    {
        return list(applicationId, null);
    }

    /**
     * Returns the application instance for the given id.
     * @param applicationId The application id
     * @param instanceId The application instance id
     * @return The application instance
     */
    public Optional<ApplicationInstance> show(long applicationId, long instanceId)
    {
        return HTTP.GET(String.format("/v2/applications/%d/instances/%d.json", applicationId, instanceId), APPLICATION_INSTANCE);
    }

    /**
     * Returns the set of metrics for the given application instance.
     * @param applicationId The application id
     * @param instanceId The application instance id
     * @param name Filter metrics by name (or part of name)
     * @return The set of metrics
     */
    public Collection<Metric> metricNames(long applicationId, long instanceId, String name)
    {
        QueryParameterList queryParams = new QueryParameterList();
        if(name != null && name.length() > 0)
            queryParams.add("name", name);
        return HTTP.GET(String.format("/v2/applications/%d/instances/%d/metrics.json", applicationId, instanceId), null, queryParams, METRICS).get();
    }

    /**
     * Returns the set of metrics for the given application instance.
     * @param applicationId The application id
     * @param instanceId The application instance id
     * @return The set of metrics
     */
    public Collection<Metric> metricNames(long applicationId, long instanceId)
    {
        return metricNames(applicationId, instanceId, null);
    }

    /**
     * Returns the set of metric data for the given application instance.
     * @param applicationId The application id
     * @param instanceId The application instance id
     * @param queryParams The query parameters
     * @return The set of metric data
     */
    public Optional<MetricData> metricData(long applicationId, long instanceId, List<String> queryParams)
    {
        return HTTP.GET(String.format("/v2/applications/%d/instances/%d/metrics/data.json", applicationId, instanceId), null, queryParams, METRIC_DATA);
    }

    /**
     * Returns a builder for the key transaction filters.
     * @return The builder instance.
     */
    public static FilterBuilder filters()
    {
        return new FilterBuilder();
    }

    /**
     * Builder to make application instance filter construction easier.
     */
    public static class FilterBuilder
    {
        private QueryParameterList filters = new QueryParameterList();

        /**
         * Adds the hostname filter to the filters.
         * @param hostname The hostname to filter on
         * @return This object
         */
        public FilterBuilder hostname(String hostname)
        {
            filters.add("filter[hostname]", hostname);
            return this;
        }

        /**
         * Adds the id filter to the filters.
         * @param ids The comma-separated list of ids to filter on
         * @return This object
         */
        public FilterBuilder ids(String ids)
        {
            filters.add("filter[ids]", ids);
            return this;
        }

        /**
         * Returns the configured filters
         * @return The filters
         */
        public List<String> build()
        {
            return filters;
        }
    }

    /**
     * Returns a builder for the metric data parameters.
     * @return The builder instance.
     */
    public static MetricParameterBuilder metrics()
    {
        return new MetricParameterBuilder();
    }
}
