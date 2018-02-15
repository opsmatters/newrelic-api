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
import com.opsmatters.newrelic.api.model.plugins.PluginComponent;
import com.opsmatters.newrelic.api.model.metrics.Metric;
import com.opsmatters.newrelic.api.model.metrics.MetricData;
import com.opsmatters.newrelic.api.util.QueryParameterList;

/**
 * The set of operations used for plugin components.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class PluginComponentService extends BaseFluent
{
    /**
     * Constructor that takes a http context and API client.
     * @param httpContext The set of HTTP operations
     * @param client The client used to invoke the New Relic operations
     */
    public PluginComponentService(HttpContext httpContext, NewRelicClient client)
    {
        super(httpContext, client);
    }

    /**
     * Returns the set of plugin components with the given query parameters.
     * @param queryParams The query parameters
     * @return The set of plugin components
     */
    public Collection<PluginComponent> list(List<String> queryParams)
    {
        return HTTP.GET("/v2/components.json", null, queryParams, PLUGIN_COMPONENTS).get();
    }

    /**
     * Returns the set of plugin components.
     * @return The set of plugin components
     */
    public Collection<PluginComponent> list()
    {
        return list(null);
    }

    /**
     * Returns the plugin component for the given id.
     * @param componentId The plugin component id
     * @return The plugin component
     */
    public Optional<PluginComponent> show(long componentId)
    {
        return HTTP.GET(String.format("/v2/components/%d.json", componentId), PLUGIN_COMPONENT);
    }

    /**
     * Returns the set of metrics for the given plugin component.
     * @param componentId The plugin component id
     * @param name Filter metrics by name (or part of name)
     * @return The set of metrics
     */
    public Collection<Metric> metricNames(long componentId, String name)
    {
        QueryParameterList queryParams = new QueryParameterList();
        if(name != null && name.length() > 0)
            queryParams.add("name", name);
        return HTTP.GET(String.format("/v2/components/%d/metrics.json", componentId), null, queryParams, METRICS).get();
    }

    /**
     * Returns the set of metrics for the given plugin component.
     * @param componentId The plugin component id
     * @return The set of metrics
     */
    public Collection<Metric> metricNames(long componentId)
    {
        return metricNames(componentId, null);
    }

    /**
     * Returns the set of metric data for the given plugin component.
     * @param componentId The plugin component id
     * @param queryParams The query parameters
     * @return The set of metric data
     */
    public Optional<MetricData> metricData(long componentId, List<String> queryParams)
    {
        return HTTP.GET(String.format("/v2/components/%d/metrics/data.json", componentId), null, queryParams, METRIC_DATA);
    }

    /**
     * Returns a builder for the plugin component filters.
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
         * Adds the plugin id filter to the filters.
         * @param pluginId The plugin id to filter on
         * @return This object
         */
        public FilterBuilder pluginId(long pluginId)
        {
            filters.add("filter[plugin_id]", Long.toString(pluginId));
            return this;
        }

        /**
         * Adds the health status filter to the filters.
         * @param healthStatus <CODE>true</CODE> if the plugin health status should be included
         * @return This object
         */
        public FilterBuilder healthStatus(boolean healthStatus)
        {
            filters.add("health_status", Boolean.toString(healthStatus));
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
