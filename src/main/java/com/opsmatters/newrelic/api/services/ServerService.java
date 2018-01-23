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
import com.google.common.base.Optional;
import com.google.common.collect.Maps;
import com.opsmatters.newrelic.api.NewRelicClient;
import com.opsmatters.newrelic.api.model.servers.Server;
import com.opsmatters.newrelic.api.model.metrics.Metric;
import com.opsmatters.newrelic.api.model.metrics.MetricData;
import com.opsmatters.newrelic.util.QueryParameterList;

/**
 * The set of operations used for servers.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class ServerService extends BaseFluent
{
    /**
     * Constructor that takes a http context and API client.
     * @param httpContext The set of HTTP operations
     * @param client The client used to invoke the New Relic operations
     */
    public ServerService(HttpContext httpContext, NewRelicClient client)
    {
        super(httpContext, client);
    }

    /**
     * Returns the set of servers with the given query parameters.
     * @param queryParams The query parameters
     * @return The set of servers
     */
    public Collection<Server> list(List<String> queryParams)
    {
        return HTTP.GET("/v2/servers.json", null, queryParams, SERVERS).get();
    }

    /**
     * Returns the set of servers.
     * @return The set of servers
     */
    public Collection<Server> list()
    {
        return list(null);
    }

    /**
     * Returns the server for the given server id.
     * @param serverId The id for the server to return
     * @return The server
     */
    public Optional<Server> show(long serverId)
    {
        return HTTP.GET(String.format("/v2/servers/%d.json", serverId), SERVER);
    }

    /**
     * Updates the given server.
     * @param server The server to update
     * @return The server that was updated
     */
    public Optional<Server> update(Server server)
    {
        return HTTP.PUT(String.format("/v2/servers/%d.json", server.getId()), server, SERVER);
    }

    /**
     * Deletes the given server.
     * @param serverId The id of the server to delete
     * @return This object
     */
    public ServerService delete(long serverId)
    {
        HTTP.DELETE(String.format("/v2/servers/%d.json", serverId));       
        return this;
    }

    /**
     * Returns the set of metrics for the given server.
     * @param serverId The id of the server to return metrics for
     * @param name Filter metrics by name (or part of name)
     * @return The set of metrics
     */
    public Collection<Metric> metricNames(long serverId, String name)
    {
        QueryParameterList queryParams = new QueryParameterList();
        if(name != null && name.length() > 0)
            queryParams.add("name", name);
        return HTTP.GET(String.format("/v2/servers/%d/metrics.json", serverId), null, queryParams, METRICS).get();
    }

    /**
     * Returns the set of metrics for the given server.
     * @param serverId The id of the server to return metrics for
     * @return The set of metrics
     */
    public Collection<Metric> metricNames(long serverId)
    {
        return metricNames(serverId, null);
    }

    /**
     * Returns the set of metric data for the given server.
     * @param serverId The id of the server to return metric data for
     * @param queryParams The query parameters
     * @return The set of metric data
     */
    public Optional<MetricData> metricData(long serverId, List<String> queryParams)
    {
        return HTTP.GET(String.format("/v2/servers/%d/metrics/data.json", serverId), null, queryParams, METRIC_DATA);
    }

    /**
     * Returns a builder for the server filters.
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
         * Adds the labels filter to the filters.
         * <P>
         * Labels must match the format Type1:Value1;Type2:Value2;...;TypeN:ValueN
         * @param labels The list of labels to filter on
         * @return This object
         */
        public FilterBuilder labels(String labels)
        {
            filters.add("filter[labels]", labels);
            return this;
        }

        /**
         * Adds the reported filter to the filters.
         * @param reported <CODE>true</CODE> if filter by servers reported in last 10 hours
         * @return This object
         */
        public FilterBuilder reported(boolean reported)
        {
            filters.add("filter[reported]", reported);
            return this;
        }

        /**
         * Set to <CODE>true</CODE> if the servers should be sorted by health status.
         * @param sort <CODE>true</CODE> if the servers should be sorted by health status
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
