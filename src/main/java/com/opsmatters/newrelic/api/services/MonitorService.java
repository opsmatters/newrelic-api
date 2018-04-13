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
import java.util.ArrayList;
import java.util.Collection;
import javax.ws.rs.core.Response;
import com.google.common.base.Optional;
import com.opsmatters.newrelic.api.NewRelicClient;
import com.opsmatters.newrelic.api.model.synthetics.Monitor;
import com.opsmatters.newrelic.api.model.synthetics.Script;
import com.opsmatters.newrelic.api.model.labels.Label;
import com.opsmatters.newrelic.api.util.QueryParameterList;

/**
 * The set of operations used for Synthetics monitors.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class MonitorService extends BaseFluent
{
    /**
     * Constructor that takes a http context and API client.
     * @param httpContext The set of HTTP operations
     * @param client The client used to invoke the New Relic operations
     */
    public MonitorService(HttpContext httpContext, NewRelicClient client)
    {
        super(httpContext, client);
    }

    /**
     * Returns the set of monitors.
     * @param queryParams The query parameters
     * @return The set of monitors
     */
    public Collection<Monitor> list(List<String> queryParams)
    {
        return HTTP.GET("/v3/monitors", null, queryParams, MONITORS).get();
    }

    /**
     * Returns the set of monitors.
     * @param offset The monitor count offset. Defaults to 0.
     * @param limit The number of results per page, maximum 100. Defaults to 20.
     * @return The set of monitors
     */
    public Collection<Monitor> list(int offset, int limit)
    {
        return list(filters().offset(offset).limit(limit).build());
    }

    /**
     * Returns the set of monitors.
     * <P> Defaults to page size of 20 monitors with 0 offset.
     * @return The set of monitors
     */
    public Collection<Monitor> list()
    {
        return list(0, -1);
    }

    /**
     * Returns the set of monitors with the given type and where the name contains the given (partial) name.
     * @param name The name of the monitors to return. Can be a partial name. A null value returns all monitors.
     * @param type The type of the monitors to return. A null value returns all monitors.
     * @param offset The monitor count offset. Defaults to 0.
     * @param limit The number of results per page, maximum 100. Defaults to 20.
     * @return The set of monitors
     */
    public Collection<Monitor> list(String name, String type, int offset, int limit)
    {
        List<Monitor> ret = new ArrayList<Monitor>();
        Collection<Monitor> monitors = list(offset, limit);
        for(Monitor monitor : monitors)
        {
            if((name == null || monitor.getName().toLowerCase().indexOf(name) != -1)
                && (type == null || monitor.getType().equals(type)))
            {
                ret.add(monitor);
            }
        }
        return ret;
    }

    /**
     * Returns the set of monitors with the given type and where the name contains the given (partial) name.
     * <P> Defaults to page size of 20 monitors with 0 offset.
     * @param name The name of the monitors to return. Can be a partial name. A null value returns all monitors.
     * @param type The type of the monitors to return. A null value returns all monitors.
     * @return The set of monitors
     */
    public Collection<Monitor> list(String name, String type)
    {
        return list(name, type, 0, -1);
    }

    /**
     * Returns the set of monitors for the given label.
     * @param label The label to use to select the monitors to return
     * @return The set of monitors for the given label
     */
    public Collection<Monitor> list(Label label)
    {
        return HTTP.GET(String.format("/v1/monitors/labels/%s", label.getKey()), MONITORS).get();
    }

    /**
     * Returns the monitor for the given monitor id.
     * @param monitorId The id for the monitor to return
     * @return The monitor
     */
    public Optional<Monitor> show(String monitorId)
    {
        return HTTP.GET(String.format("/v3/monitors/%s", monitorId), MONITOR);
    }

    /**
     * Returns the script for the given monitor id.
     * @param monitorId The id for the monitor to return
     * @return The script
     */
    public Optional<Script> showScript(String monitorId)
    {
        return HTTP.GET(String.format("/v3/monitors/%s/script", monitorId), SCRIPT);
    }

    /**
     * Creates the given monitor.
     * @param monitor The monitor to create
     * @return This object
     */
    public Optional<Monitor> create(Monitor monitor)
    {
        Response obj = HTTP.POST("/v3/monitors", monitor).get();
        String location = obj.getStringHeaders().getFirst("location");
        if(location != null)
            monitor.setId(location.substring(location.lastIndexOf("/")+1));
        return Optional.of(monitor);
    }

    /**
     * Updates the given monitor.
     * @param monitor The monitor to update
     * @return The monitor that was updated
     */
    public Optional<Monitor> update(Monitor monitor)
    {
        HTTP.PUT(String.format("/v3/monitors/%s", monitor.getId()), monitor);
        return Optional.of(monitor);
    }

    /**
     * Updates the given monitor to add a script.
     * @param monitorId The id of the monitor to update
     * @param script The script to add
     * @return The monitor that was updated
     */
    public Optional<Script> updateScript(String monitorId, Script script)
    {
        HTTP.PUT(String.format("/v3/monitors/%s/script", monitorId), script);
        return Optional.of(script);
    }

    /**
     * Patches the given monitor.
     * @param monitor The monitor to patch
     * @return The monitor that was patched
     */
    public Optional<Monitor> patch(Monitor monitor)
    {
        HTTP.PATCH(String.format("/v3/monitors/%s", monitor.getId()), monitor);
        return Optional.of(monitor);
    }

    /**
     * Adds the given label to the monitor with the given id.
     * @param monitorId The id of the monitor to update
     * @param label The label to add
     * @return The label that was added
     */
    public Optional<Label> createLabel(String monitorId, Label label)
    {
        HTTP.POST(String.format("/v1/monitors/%s/labels", monitorId), label.getKey());
        return Optional.of(label);
    }

    /**
     * Deletes the monitor with the given id.
     * @param monitorId The id of the monitor to delete
     * @return This object
     */
    public MonitorService delete(String monitorId)
    {
        HTTP.DELETE(String.format("/v3/monitors/%s", monitorId));       
        return this;
    }

    /**
     * Deletes the given label from the monitor with the given id.
     * @param monitorId The id of the monitor with the label
     * @param label The label to delete
     * @return This object
     */
    public MonitorService deleteLabel(String monitorId, Label label)
    {
        HTTP.DELETE(String.format("/v1/monitors/%s/labels/%s", monitorId, label.getKey()));
        return this;
    }

    /**
     * Returns a builder for the monitor filters.
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
         * Adds the offset filter to the filters.
         * @param offset The offset to filter on
         * @return This object
         */
        public FilterBuilder offset(int offset)
        {
            if(offset >= 0)
                filters.add("offset", offset);
            return this;
        }

        /**
         * Adds the limit filter to the filters.
         * @param limit The limit to filter on
         * @return This object
         */
        public FilterBuilder limit(int limit)
        {
            if(limit >= 0)
                filters.add("limit", limit);
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
}
