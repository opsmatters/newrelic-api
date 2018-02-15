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
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import com.google.common.base.Optional;
import com.google.common.collect.Maps;
import com.opsmatters.newrelic.api.NewRelicClient;
import com.opsmatters.newrelic.api.model.applications.Application;
import com.opsmatters.newrelic.api.model.metrics.Metric;
import com.opsmatters.newrelic.api.model.metrics.MetricData;
import com.opsmatters.newrelic.api.util.QueryParameterList;

/**
 * The set of operations used for applications.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class ApplicationService extends BaseFluent
{
    /**
     * Constructor that takes a http context and API client.
     * @param httpContext The set of HTTP operations
     * @param client The client used to invoke the New Relic operations
     */
    public ApplicationService(HttpContext httpContext, NewRelicClient client)
    {
        super(httpContext, client);
    }

    /**
     * Returns the set of applications with the given query parameters.
     * @param queryParams The query parameters
     * @return The set of applications
     */
    public Collection<Application> list(List<String> queryParams)
    {
        return HTTP.GET("/v2/applications.json", null, queryParams, APPLICATIONS).get();
    }

    /**
     * Returns the set of applications.
     * @return The set of applications
     */
    public Collection<Application> list()
    {
        List<String> queryParams = null;
        return list(queryParams);
    }

    /**
     * Returns the set of applications for the given name.
     * @param name The name of the applications
     * @return The set of applications
     */
    public Collection<Application> list(String name)
    {
        List<Application> ret = new ArrayList<Application>();
        Collection<Application> applications = list();
        for(Application application : applications)
        {
            if(name == null || application.getName().equals(name))
                ret.add(application);
        }
        return ret;
    }

    /**
     * Returns the application for the given application id.
     * @param applicationId The id for the application to return
     * @return The application
     */
    public Optional<Application> show(long applicationId)
    {
        return HTTP.GET(String.format("/v2/applications/%d.json", applicationId), APPLICATION);
    }

    /**
     * Updates the given application.
     * @param application The application to update
     * @return The application that was updated
     */
    public Optional<Application> update(Application application)
    {
        return HTTP.PUT(String.format("/v2/applications/%d.json", application.getId()), application, APPLICATION);
    }

    /**
     * Deletes the given application.
     * @param applicationId The id of the application to delete
     * @return This object
     */
    public ApplicationService delete(long applicationId)
    {
        HTTP.DELETE(String.format("/v2/applications/%d.json", applicationId));       
        return this;
    }

    /**
     * Returns the set of metrics for the given application.
     * @param applicationId The id of the application to return metrics for
     * @param name Filter metrics by name (or part of name)
     * @return The set of metrics
     */
    public Collection<Metric> metricNames(long applicationId, String name)
    {
        QueryParameterList queryParams = new QueryParameterList();
        if(name != null && name.length() > 0)
            queryParams.add("name", name);
        return HTTP.GET(String.format("/v2/applications/%d/metrics.json", applicationId), null, queryParams, METRICS).get();
    }

    /**
     * Returns the set of metrics for the given application.
     * @param applicationId The id of the application to return metrics for
     * @return The set of metrics
     */
    public Collection<Metric> metricNames(long applicationId)
    {
        return metricNames(applicationId, null);
    }

    /**
     * Returns the set of metric data for the given application.
     * @param applicationId The id of the application to return metric data for
     * @param queryParams The query parameters
     * @return The set of metric data
     */
    public Optional<MetricData> metricData(long applicationId, List<String> queryParams)
    {
        return HTTP.GET(String.format("/v2/applications/%d/metrics/data.json", applicationId), null, queryParams, METRIC_DATA);
    }

    /**
     * Returns a builder for the application filters.
     * @return The builder instance.
     */
    public static FilterBuilder filters()
    {
        return new FilterBuilder();
    }

    /**
     * Builder to make filter construction easier.
     */
    public static class FilterBuilder
    {
        private QueryParameterList filters = new QueryParameterList();

        /**
         * Adds the name filter to the filters.
         * @param name The name to filter on
         * @return This object
         */
        public FilterBuilder name(String name)
        {
            filters.add("filter[name]", name);
            return this;
        }

        /**
         * Adds the host filter to the filters.
         * @param host The host to filter on
         * @return This object
         */
        public FilterBuilder host(String host)
        {
            filters.add("filter[host]", host);
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
         * Adds the language filter to the filters.
         * @param language The language to filter on
         * @return This object
         */
        public FilterBuilder language(String language)
        {
            filters.add("filter[language]", language);
            return this;
        }

        /**
         * Set to <CODE>true</CODE> if the applications should be sorted by health status.
         * @param sort <CODE>true</CODE> if the applications should be sorted by health status
         * @return This object
         */
        public FilterBuilder sortByHealthStatus(boolean sort)
        {
            filters.add("sort[health_status]", sort);
            return this;
        }

        /**
         * Adds the sort token to the filters to ensure correct sorted pagination.
         * @param token The sort token to ensure correct sorted pagination
         * @return This object
         */
        public FilterBuilder sortToken(String token)
        {
            filters.add("sort[token]", token);
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
