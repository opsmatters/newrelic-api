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
import com.opsmatters.newrelic.api.NewRelicClient;
import com.opsmatters.newrelic.api.model.alerts.AlertViolation;
import com.opsmatters.newrelic.api.util.QueryParameterList;
import com.opsmatters.newrelic.api.util.Utils;

/**
 * The set of operations used for alert violations.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class AlertViolationService extends BaseFluent
{
    /**
     * Constructor that takes a http context and API client.
     * @param httpContext The set of HTTP operations
     * @param client The client used to invoke the New Relic operations
     */
    public AlertViolationService(HttpContext httpContext, NewRelicClient client)
    {
        super(httpContext, client);
    }

    /**
     * Returns the set of alert violations with the given query parameters.
     * @param queryParams The query parameters
     * @return The set of alert violations
     */
    public Collection<AlertViolation> list(List<String> queryParams)
    {
        return HTTP.GET("/v2/alerts_violations.json", null, queryParams, ALERT_VIOLATIONS).get();
    }

    /**
     * Returns the set of alert violations.
     * @param startDate Retrieves violations created after this date (in milliseconds)
     * @param endDate Retrieves violations created before this date (in milliseconds)
     * @param onlyOpen Filter by open violations
     * @return The set of alert violations
     */
    public Collection<AlertViolation> list(long startDate, long endDate, boolean onlyOpen)
    {
        return list(filters().startDate(startDate).endDate(endDate).onlyOpen(onlyOpen).build());
    }

    /**
     * Returns a builder for the alert violation filters.
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
         * Adds the startDate filter to the filters.
         * @param startDate The start date to filter on
         * @return This object
         */
        public FilterBuilder startDate(long startDate)
        {
            if(startDate > 0L)
                filters.add("start_date", Utils.getUtcDateTime(startDate));
            return this;
        }

        /**
         * Adds the endDate filter to the filters.
         * @param endDate The end date to filter on
         * @return This object
         */
        public FilterBuilder endDate(long endDate)
        {
            if(endDate > 0L)
                filters.add("end_date", Utils.getUtcDateTime(endDate));
            return this;
        }

        /**
         * Adds the onlyOpen filter to the filters.
         * @param onlyOpen <CODE>true</CODE> if only open incidents should be included
         * @return This object
         */
        public FilterBuilder onlyOpen(boolean onlyOpen)
        {
            filters.add("only_open", onlyOpen);
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
