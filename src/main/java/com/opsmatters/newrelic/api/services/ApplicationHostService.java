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

import java.util.Collection;
import java.util.List;
import com.google.common.base.Optional;
import com.opsmatters.newrelic.api.NewRelicClient;
import com.opsmatters.newrelic.api.model.applications.ApplicationHost;
import com.opsmatters.newrelic.api.model.metrics.Metric;
import com.opsmatters.newrelic.api.model.metrics.MetricData;
import com.opsmatters.newrelic.util.QueryParameterList;

/**
 * The set of operations used for application hosts.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class ApplicationHostService extends BaseFluent
{
    /**
     * Constructor that takes a http context and API client.
     * @param httpContext The set of HTTP operations
     * @param client The client used to invoke the New Relic operations
     */
    public ApplicationHostService(HttpContext httpContext, NewRelicClient client)
    {
        super(httpContext, client);
    }

    /**
     * Returns the set of application hosts with the given query parameters.
     * @param applicationId The application id
     * @param queryParams The query parameters
     * @return The set of application hosts
     */
    public Collection<ApplicationHost> list(long applicationId, List<String> queryParams)
    {
        return HTTP.GET(String.format("/v2/applications/%d/hosts.json", applicationId), null, queryParams, APPLICATION_HOSTS).get();
    }

    /**
     * Returns the set of application hosts.
     * @param applicationId The application id
     * @return The set of application hosts
     */
    public Collection<ApplicationHost> list(long applicationId)
    {
        return list(applicationId, null);
    }

    /**
     * Returns the application host for the given id.
     * @param applicationId The application id
     * @param hostId The application host id
     * @return The application host
     */
    public Optional<ApplicationHost> show(long applicationId, long hostId)
    {
        return HTTP.GET(String.format("/v2/applications/%d/hosts/%d.json", applicationId, hostId), APPLICATION_HOST);
    }

    /**
     * Returns the set of metrics for the given application host.
     * @param applicationId The application id
     * @param hostId The application host id
     * @param name Filter metrics by name (or part of name)
     * @return The set of metrics
     */
    public Collection<Metric> metricNames(long applicationId, long hostId, String name)
    {
        QueryParameterList queryParams = new QueryParameterList();
        if(name != null && name.length() > 0)
            queryParams.add("name", name);
        return HTTP.GET(String.format("/v2/applications/%d/hosts/%d/metrics.json", applicationId, hostId), null, queryParams, METRICS).get();
    }

    /**
     * Returns the set of metrics for the given application host.
     * @param applicationId The application id
     * @param hostId The application host id
     * @return The set of metrics
     */
    public Collection<Metric> metricNames(long applicationId, long hostId)
    {
        return metricNames(applicationId, hostId, null);
    }

    /**
     * Returns the set of metric data for the given application host.
     * @param applicationId The application id
     * @param hostId The application host id
     * @param queryParams The query parameters
     * @return The set of metric data
     */
    public Optional<MetricData> metricData(long applicationId, long hostId, List<String> queryParams)
    {
        return HTTP.GET(String.format("/v2/applications/%d/hosts/%d/metrics/data.json", applicationId, hostId), null, queryParams, METRIC_DATA);
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
     * Builder to make application host filter construction easier.
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
