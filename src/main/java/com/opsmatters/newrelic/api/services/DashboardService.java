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
import com.google.common.base.Optional;
import com.opsmatters.newrelic.api.NewRelicClient;
import com.opsmatters.newrelic.api.model.insights.Dashboard;
import com.opsmatters.newrelic.api.util.QueryParameterList;
import com.opsmatters.newrelic.api.util.Utils;

/**
 * The set of operations used for dashboards.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class DashboardService extends BaseFluent
{
    /**
     * Constructor that takes a http context and API client.
     * @param httpContext The set of HTTP operations
     * @param client The client used to invoke the New Relic operations
     */
    public DashboardService(HttpContext httpContext, NewRelicClient client)
    {
        super(httpContext, client);
    }

    /**
     * Returns the set of dashboards with the given query parameters.
     * @param queryParams The query parameters
     * @return The set of dashboards
     */
    public Collection<Dashboard> list(List<String> queryParams)
    {
        return HTTP.GET("/v2/dashboards.json", null, queryParams, DASHBOARDS).get();
    }

    /**
     * Returns the set of dashboards for the given title.
     * @param title The dashboard title
     * @return The set of dashboards
     */
    public Collection<Dashboard> list(String title)
    {
        return list(filters().title(title).build());
    }

    /**
     * Returns the set of dashboards.
     * @return The set of dashboards
     */
    public Collection<Dashboard> list()
    {
        QueryParameterList queryParams = null;
        return list(queryParams);
    }

    /**
     * Returns the dashboard with the given id.
     * @param dashboardId The id of the dashboard to return
     * @return The dashboard
     */
    public Optional<Dashboard> show(long dashboardId)
    {
        return HTTP.GET(String.format("/v2/dashboards/%d.json", dashboardId), DASHBOARD);
    }
    
    /**
     * Creates the given dashboard.
     * @param dashboard The dashboard to create
     * @return The dashboard that was created
     */
    public Optional<Dashboard> create(Dashboard dashboard)
    {
        return HTTP.POST("/v2/dashboards.json", dashboard, DASHBOARD);
    }

    /**
     * Updates the given dashboard.
     * @param dashboard The dashboard to update
     * @return The dashboard that was updated
     */
    public Optional<Dashboard> update(Dashboard dashboard)
    {
        return HTTP.PUT(String.format("/v2/dashboards/%d.json", dashboard.getId()), dashboard, DASHBOARD);
    }

    /**
     * Deletes the dashboard with the given id.
     * @param dashboardId The id of the dashboard to delete
     * @return This object
     */
    public DashboardService delete(long dashboardId)
    {
        HTTP.DELETE(String.format("/v2/dashboards/%d.json", dashboardId));       
        return this;
    }

    /**
     * Returns a builder for the dashboard filters.
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
         * Adds the title filter to the filters.
         * @param title The title to filter on
         * @return This object
         */
        public FilterBuilder title(String title)
        {
            if(title != null)
                filters.add("filter[title]", title);
            return this;
        }

        /**
         * Adds the owner email filter to the filters.
         * @param ownerEmail The owner email to filter on
         * @return This object
         */
        public FilterBuilder ownerEmail(String ownerEmail)
        {
            if(ownerEmail != null)
                filters.add("filter[owner_email]", ownerEmail);
            return this;
        }

        /**
         * Adds the category filter to the filters.
         * @param category The category to filter on
         * @return This object
         */
        public FilterBuilder category(String category)
        {
            if(category != null)
                filters.add("filter[category]", category);
            return this;
        }

        /**
         * Adds the created before parameter to the parameters.
         * @param dt The created before date parameter to add
         * @return This object
         */
        public FilterBuilder createdBefore(long dt)
        {
            if(dt > 0L)
                filters.add("filter[created_before]", Utils.getUtcDateTime(dt));
            return this;
        }

        /**
         * Adds the created after parameter to the parameters.
         * @param dt The created after date parameter to add
         * @return This object
         */
        public FilterBuilder createdAfter(long dt)
        {
            if(dt > 0L)
                filters.add("filter[created_after]", Utils.getUtcDateTime(dt));
            return this;
        }

        /**
         * Adds the updated before parameter to the parameters.
         * @param dt The updated before date parameter to add
         * @return This object
         */
        public FilterBuilder updatedBefore(long dt)
        {
            if(dt > 0L)
                filters.add("filter[updated_before]", Utils.getUtcDateTime(dt));
            return this;
        }

        /**
         * Adds the updated after parameter to the parameters.
         * @param dt The updated after date parameter to add
         * @return This object
         */
        public FilterBuilder updatedAfter(long dt)
        {
            if(dt > 0L)
                filters.add("filter[updated_after]", Utils.getUtcDateTime(dt));
            return this;
        }

        /**
         * Adds the given sort to the filters.
         * @param sort The sort to use with the results
         * @return This object
         */
        public FilterBuilder sort(String sort)
        {
            if(sort != null)
                filters.add("sort", sort);
            return this;
        }

        /**
         * Adds the "name" sort to the filters.
         * @return This object
         */
        public FilterBuilder nameSort()
        {
            filters.add("sort", "name");
            return this;
        }

        /**
         * Adds the "recently viewed" sort to the filters.
         * @return This object
         */
        public FilterBuilder recentlyViewedSort()
        {
            filters.add("sort", "recently viewed");
            return this;
        }

        /**
         * Adds the "last edited" sort to the filters.
         * @return This object
         */
        public FilterBuilder lastEditedSort()
        {
            filters.add("sort", "last edited");
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
