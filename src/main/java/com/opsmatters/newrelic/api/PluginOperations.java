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
import com.opsmatters.newrelic.api.model.entities.Plugin;
import com.opsmatters.newrelic.util.QueryParameterList;

/**
 * The set of operations used for plugins.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class PluginOperations extends BaseFluent
{
    /**
     * Constructor that takes a http context and API service.
     * @param httpContext The set of HTTP operations
     * @param apiService The set of API operations
     */
    public PluginOperations(HttpContext httpContext, NewRelicApiService apiService)
    {
        super(httpContext, apiService);
    }

    /**
     * Returns the set of plugins with the given query parameters.
     * @param queryParams The query parameters
     * @return The set of plugins
     */
    public Collection<Plugin> list(List<String> queryParams)
    {
        return HTTP.GET("/plugins.json", null, queryParams, PLUGINS).get();
    }

    /**
     * Returns the set of plugins.
     * @param detailed <CODE>true</CODE> if the details of the plugin should be included
     * @return The set of plugins
     */
    public Collection<Plugin> list(boolean detailed)
    {
        QueryParameterList queryParams = new QueryParameterList();
        queryParams.add("detailed", Boolean.toString(detailed));
        return list(queryParams);
    }

    /**
     * Returns the plugin for the given plugin id.
     * @param pluginId The id for the plugin to return
     * @param detailed <CODE>true</CODE> if the details of the plugin should be included
     * @return The plugin
     */
    public Optional<Plugin> show(long pluginId, boolean detailed)
    {
        QueryParameterList queryParams = new QueryParameterList();
        queryParams.add("detailed", Boolean.toString(detailed));
        return HTTP.GET(String.format("/plugins/%d.json", pluginId), null, queryParams, PLUGIN);
    }

    /**
     * Returns a builder for the plugin filters.
     * @return The builder instance.
     */
    public static FilterBuilder filters()
    {
        return new FilterBuilder();
    }

    /**
     * Builder to make plugin filter construction easier.
     */
    public static class FilterBuilder
    {
        private QueryParameterList filters = new QueryParameterList();

        /**
         * Adds the guid filter to the filters.
         * @param guid The guid to filter on
         * @return This object
         */
        public FilterBuilder guid(String guid)
        {
            filters.add("filter[guid]", guid);
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
         * Adds the detailed filter to the filters.
         * @param detailed <CODE>true</CODE> if the details of the plugin should be included
         * @return This object
         */
        public FilterBuilder detailed(boolean detailed)
        {
            filters.add("detailed", Boolean.toString(detailed));
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
