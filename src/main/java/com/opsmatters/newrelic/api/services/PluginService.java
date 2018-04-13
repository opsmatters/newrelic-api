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
import com.google.common.base.Optional;
import com.opsmatters.newrelic.api.NewRelicClient;
import com.opsmatters.newrelic.api.model.plugins.Plugin;
import com.opsmatters.newrelic.api.util.QueryParameterList;

/**
 * The set of operations used for plugins.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class PluginService extends BaseFluent
{
    /**
     * Constructor that takes a http context and API client.
     * @param httpContext The set of HTTP operations
     * @param client The client used to invoke the New Relic operations
     */
    public PluginService(HttpContext httpContext, NewRelicClient client)
    {
        super(httpContext, client);
    }

    /**
     * Returns the set of plugins with the given query parameters.
     * @param queryParams The query parameters
     * @return The set of plugins
     */
    public Collection<Plugin> list(List<String> queryParams)
    {
        return HTTP.GET("/v2/plugins.json", null, queryParams, PLUGINS).get();
    }

    /**
     * Returns the set of plugins.
     * @param detailed <CODE>true</CODE> if the details of the plugin should be included
     * @return The set of plugins
     */
    public Collection<Plugin> list(boolean detailed)
    {
        return list(filters().detailed(detailed).build());
    }

    /**
     * Returns the set of plugins for the given name.
     * @param name The name of the plugins
     * @param detailed <CODE>true</CODE> if the details of the plugin should be included
     * @return The set of plugins
     */
    public Collection<Plugin> list(String name, boolean detailed)
    {
        List<Plugin> ret = new ArrayList<Plugin>();
        Collection<Plugin> plugins = list(detailed);
        for(Plugin plugin : plugins)
        {
            if(name == null || plugin.getName().equals(name))
                ret.add(plugin);
        }
        return ret;
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
        return HTTP.GET(String.format("/v2/plugins/%d.json", pluginId), null, queryParams, PLUGIN);
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
     * Builder to make filter construction easier.
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
         * Adds the page filter to the filters.
         * @param page The page to filter on
         * @return This object
         */
        public FilterBuilder page(int page)
        {
            if(page >= 0)
                filters.add("page", page);
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
