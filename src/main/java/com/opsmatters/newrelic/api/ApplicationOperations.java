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
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import com.google.common.base.Optional;
import com.google.common.collect.Maps;
import com.opsmatters.newrelic.api.model.entity.Application;
import com.opsmatters.newrelic.api.model.entity.Metric;
import com.opsmatters.newrelic.api.model.entity.MetricData;
import com.opsmatters.newrelic.util.Utils;
import com.opsmatters.newrelic.util.QueryParameterList;

/**
 * The set of operations used for applications.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class ApplicationOperations extends BaseFluent
{
    /**
     * Constructor that takes a http context and API service.
     * @param httpContext The set of HTTP operations
     * @param apiService The set of API operations
     */
    public ApplicationOperations(HttpContext httpContext, NewRelicApiService apiService)
    {
        super(httpContext, apiService);
    }

    /**
     * Returns the set of applications with the given query parameters.
     * @param queryParams The query parameters
     * @return The set of applications
     */
    public Collection<Application> list(Map<String,Object> queryParams)
    {
        return HTTP.GET("/applications.json", APPLICATIONS).get();
    }

    /**
     * Returns the set of applications.
     * @return The set of applications
     */
    public Collection<Application> list()
    {
        return list(null);
    }

    /**
     * Returns the application for the given application id.
     * @param id The id for the application to return
     * @return The application
     */
    public Optional<Application> show(long id)
    {
        return HTTP.GET(String.format("/applications/%d.json", id), APPLICATION);
    }

    /**
     * Updates the given application.
     * @param application The application to update
     * @return The application that was updated
     */
    public Optional<Application> update(Application application)
    {
        return HTTP.PUT(String.format("/applications/%d.json", application.getId()), application, APPLICATION);
    }

    /**
     * Deletes the given application.
     * @param id The id of the application to delete
     * @return This object
     */
    public ApplicationOperations delete(long id)
    {
        HTTP.DELETE(String.format("/applications/%d.json", id));       
        return this;
    }

    /**
     * Returns the set of metrics for the given application.
     * @param id The id of the application to return metrics for
     * @param name Filter metrics by name (or part of name)
     * @return The set of metrics
     */
    public Collection<Metric> metricNames(long id, String name)
    {
        QueryParameterList queryParams = new QueryParameterList();
        if(name != null && name.length() > 0)
            queryParams.add("name", name);
        return HTTP.GET(String.format("/applications/%d/metrics.json", id), null, queryParams, METRICS).get();
    }

    /**
     * Returns the set of metrics for the given application.
     * @param id The id of the application to return metrics for
     * @return The set of metrics
     */
    public Collection<Metric> metricNames(long id)
    {
        return metricNames(id, null);
    }

    /**
     * Returns the set of metric data for the given application.
     * @param id The id of the application to return metric data for
     * @param queryParams The query parameters
     * @return The set of metric data
     */
    public Optional<MetricData> metricData(long id, List<String> queryParams)
    {
        return HTTP.GET(String.format("/applications/%d/metrics/data.json", id), null, queryParams, METRIC_DATA);
    }

    /**
     * Returns a builder for the application filters.
     * @return The builder instance.
     */
    public static ApplicationFilterBuilder filters()
    {
        return new ApplicationFilterBuilder();
    }

    /**
     * Builder to make application filter construction easier.
     */
    public static class ApplicationFilterBuilder
    {
        private Map<String,Object> filters = Maps.newHashMap();

        /**
         * Adds the name filter to the filters.
         * @param name The name to filter on
         * @return This object
         */
        public ApplicationFilterBuilder name(String name)
        {
            filters.put("filter[name]", name);
            return this;
        }

        /**
         * Adds the host filter to the filters.
         * @param host The host to filter on
         * @return This object
         */
        public ApplicationFilterBuilder host(String host)
        {
            filters.put("filter[host]", host);
            return this;
        }

        /**
         * Adds the id filter to the filters.
         * @param ids The comma-separated list of ids to filter on
         * @return This object
         */
        public ApplicationFilterBuilder ids(String ids)
        {
            filters.put("filter[ids]", ids);
            return this;
        }

        /**
         * Adds the language filter to the filters.
         * @param language The language to filter on
         * @return This object
         */
        public ApplicationFilterBuilder language(String language)
        {
            filters.put("filter[language]", language);
            return this;
        }

        /**
         * Set to <CODE>true</CODE> if the applications should be sorted by health status.
         * @param sort <CODE>true</CODE> if the applications should be sorted by health status
         * @return This object
         */
        public ApplicationFilterBuilder sortByHealthStatus(boolean sort)
        {
            filters.put("sort[health_status]", sort);
            return this;
        }

        /**
         * Adds the sort token to the filters to ensure correct sorted pagination.
         * @param token The sort token to ensure correct sorted pagination
         * @return This object
         */
        public ApplicationFilterBuilder sortToken(String token)
        {
            filters.put("sort[token]", token);
            return this;
        }

        /**
         * Returns the configured filters
         * @return The filters
         */
        public Map<String,Object> build()
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

    /**
     * Builder to make metric parameter construction easier.
     */
    public static class MetricParameterBuilder
    {
        private QueryParameterList parameters = new QueryParameterList();

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
}
