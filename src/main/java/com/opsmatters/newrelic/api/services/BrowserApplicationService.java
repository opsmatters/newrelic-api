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
import java.util.Collection;
import java.util.ArrayList;
import com.google.common.base.Optional;
import com.opsmatters.newrelic.api.NewRelicClient;
import com.opsmatters.newrelic.api.model.applications.BrowserApplication;
import com.opsmatters.newrelic.api.util.QueryParameterList;

/**
 * The set of operations used for Browser applications.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class BrowserApplicationService extends BaseFluent
{
    /**
     * Constructor that takes a http context and API client.
     * @param httpContext The set of HTTP operations
     * @param client The client used to invoke the New Relic operations
     */
    public BrowserApplicationService(HttpContext httpContext, NewRelicClient client)
    {
        super(httpContext, client);
    }

    /**
     * Returns the set of Browser applications with the given query parameters.
     * @param queryParams The query parameters
     * @return The set of applications
     */
    public Collection<BrowserApplication> list(List<String> queryParams)
    {
        return HTTP.GET("/v2/browser_applications.json", null, queryParams, BROWSER_APPLICATIONS).get();
    }

    /**
     * Returns the set of Browser applications.
     * @return The set of applications
     */
    public Collection<BrowserApplication> list()
    {
        List<String> queryParams = null;
        return list(queryParams);

    }

    /**
     * Returns the set of Browser applications for the given name.
     * @param name The name of the applications
     * @return The set of applications
     */
    public Collection<BrowserApplication> list(String name)
    {
        List<BrowserApplication> ret = new ArrayList<BrowserApplication>();
        Collection<BrowserApplication> applications = list();
        for(BrowserApplication application : applications)
        {
            if(name == null || application.getName().equals(name))
                ret.add(application);
        }
        return ret;
    }

    /**
     * Returns the Browser application for the given application id.
     * @param applicationId The id for the application to return
     * @return The application
     */
    public Optional<BrowserApplication> show(long applicationId)
    {
        QueryParameterList queryParams = new QueryParameterList();
        queryParams.add("filter[ids]", new Long(applicationId));
        return Optional.of(HTTP.GET("/v2/browser_applications.json", null, queryParams, BROWSER_APPLICATIONS).get().iterator().next());
    }

    /**
     * Creates the given Browser application.
     * @param application The application to create
     * @return The application that was created
     */
    public Optional<BrowserApplication> create(BrowserApplication application)
    {
        return HTTP.POST("/v2/browser_applications.json", application, BROWSER_APPLICATION);
    }

    /**
     * Returns a builder for the Browser application filters.
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
