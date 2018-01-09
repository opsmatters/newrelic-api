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
import java.util.Date;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import com.google.common.collect.Maps;
import com.opsmatters.newrelic.api.model.AlertViolation;

/**
 * The set of operations used for alert violations.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class AlertViolationOperations extends BaseFluent
{
    /**
     * The date format used by ISO8601.
     */
    public static final String ISO8601_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

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
     * @param startDate Retrieves violations created after this date
     * @param endDate Retrieves violations created before this date
     * @param onlyOpen Filter by open violations
     * @return The set of alert violations
     */
    public Collection<AlertViolation> list(long startDate, long endDate, boolean onlyOpen)
    {
        Map<String,Object> queryParams = Maps.newHashMap();
        if(startDate > 0L)
            queryParams.put("start_date", getUtcDateTime(startDate));
        if(endDate > 0L)
            queryParams.put("end_date", getUtcDateTime(endDate));
        queryParams.put("only_open", onlyOpen);
        return HTTP.GET("/alerts_violations.json", null, queryParams, ALERT_VIOLATIONS).get();
    }

    /**
     * Returns the given date time formatted using the ISO 8601 format.
     * @param dt The date to format (in milliseconds)
     * @return The date in ISO 8601 format
     */
    static public String getUtcDateTime(long dt)
    {
        SimpleDateFormat df = new SimpleDateFormat(ISO8601_FORMAT);
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        return df.format(new Date(dt));
    }
}
