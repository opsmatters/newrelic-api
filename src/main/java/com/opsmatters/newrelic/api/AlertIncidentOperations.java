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
import com.google.common.collect.Maps;
import com.opsmatters.newrelic.api.model.AlertIncident;

/**
 * The set of operations used for alert incidents.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class AlertIncidentOperations extends BaseFluent
{
    /**
     * Constructor that takes a http context and API service.
     * @param httpContext The set of HTTP operations
     * @param apiService The set of API operations
     */
    public AlertIncidentOperations(HttpContext httpContext, NewRelicApiService apiService)
    {
        super(httpContext, apiService);
    }

    /**
     * Returns the set of alert incidents.
     * @param onlyOpen Filter by open incidents
     * @return The set of alert incidents
     */
    public Collection<AlertIncident> list(boolean onlyOpen)
    {
        Map<String,Object> queryParams = Maps.newHashMap();
        queryParams.put("only_open", onlyOpen);
        return HTTP.GET("/alerts_incidents.json", null, queryParams, ALERT_INCIDENTS).get();
    }
}