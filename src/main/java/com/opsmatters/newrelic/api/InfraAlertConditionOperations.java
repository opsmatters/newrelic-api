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
import com.google.common.base.Optional;
import com.google.common.collect.Maps;
import com.opsmatters.newrelic.api.model.condition.InfraAlertCondition;

/**
 * The set of operations used for infrastructure alert conditions.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class InfraAlertConditionOperations extends BaseFluent
{
    /**
     * Constructor that takes a http context and API service.
     * @param httpContext The set of HTTP operations
     * @param apiService The set of API operations
     */
    public InfraAlertConditionOperations(HttpContext httpContext, NewRelicApiService apiService)
    {
        super(httpContext, apiService);
    }

    /**
     * Returns the set of alert conditions for the given policy id.
     * @param policyId The id of the alert policy to return the conditions for
     * @return The set of alert conditions
     */
    public Collection<InfraAlertCondition> all(long policyId)
    {
        Map<String,Object> queryParams = Maps.newHashMap();
        queryParams.put("policy_id", policyId);
        return HTTP.GET("/alerts/conditions", null, queryParams, INFRA_ALERT_CONDITIONS).get();
    }

    /**
     * Returns the infrastructure alert condition with the given id.
     * @param id The id of the alert condition to return
     * @return The alert condition
     */
    public Optional<InfraAlertCondition> get(long id)
    {
        return Optional.of(HTTP.GET(String.format("/alerts/conditions/%d", id), null, null, INFRA_ALERT_CONDITION).get());
    }
   
    /**
     * Creates the given infrastructure alert condition.
     * @param condition The alert condition to create
     * @return The alert condition that was created
     */
    public Optional<InfraAlertCondition> create(InfraAlertCondition condition)
    {
        return Optional.of(HTTP.POST("/alerts/conditions", condition, INFRA_ALERT_CONDITION).get());
    }
    
    /**
     * Deletes the infrastructure alert condition with the given id.
     * @param id The id of the alert condition to delete
     * @return This object
     */
    public InfraAlertConditionOperations delete(long id)
    {
        HTTP.DELETE(String.format("/alerts/conditions/%d", id));       
        return this;
    }
}
