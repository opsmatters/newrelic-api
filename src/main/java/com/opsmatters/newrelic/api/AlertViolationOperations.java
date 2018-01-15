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
import java.util.Date;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import com.opsmatters.newrelic.api.model.alerts.AlertViolation;
import com.opsmatters.newrelic.util.QueryParameterList;
import com.opsmatters.newrelic.util.Utils;

/**
 * The set of operations used for alert violations.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class AlertViolationOperations extends BaseFluent
{
    /**
     * Constructor that takes a http context and API service.
     * @param httpContext The set of HTTP operations
     * @param apiService The set of API operations
     */
    public AlertViolationOperations(HttpContext httpContext, NewRelicApiService apiService)
    {
        super(httpContext, apiService);
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
        QueryParameterList queryParams = new QueryParameterList();
        if(startDate > 0L)
            queryParams.add("start_date", Utils.getUtcDateTime(startDate));
        if(endDate > 0L)
            queryParams.add("end_date", Utils.getUtcDateTime(endDate));
        queryParams.add("only_open", onlyOpen);
        return HTTP.GET("/alerts_violations.json", null, queryParams, ALERT_VIOLATIONS).get();
    }
}
