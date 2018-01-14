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
import com.google.common.base.Optional;
import com.opsmatters.newrelic.api.model.conditions.NrqlAlertCondition;
import com.opsmatters.newrelic.util.QueryParameterList;

/**
 * The set of operations used for NRQL alert conditions.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class NrqlAlertConditionOperations extends BaseFluent
{
    /**
     * Constructor that takes a http context and API service.
     * @param httpContext The set of HTTP operations
     * @param apiService The set of API operations
     */
    public NrqlAlertConditionOperations(HttpContext httpContext, NewRelicApiService apiService)
    {
        super(httpContext, apiService);
    }

    /**
     * Returns the set of alert conditions for the given policy id.
     * @param policyId The id of the alert policy to return the conditions for
     * @return The set of alert conditions
     */
    public Collection<NrqlAlertCondition> list(long policyId)
    {
        QueryParameterList queryParams = new QueryParameterList();
        queryParams.add("policy_id", new Long(policyId));
        return HTTP.GET("/alerts_nrql_conditions.json", null, queryParams, NRQL_ALERT_CONDITIONS).get();
    }

    /**
     * Returns the NRQL alert condition with the given id.
     * <P>
     * This is needed because the API does not contain an operation to get a condition using the id directly.
     * @param policyId The id of the policy the condition belongs to
     * @param conditionId The id of the alert condition to return
     * @return The alert condition
     */
    public Optional<NrqlAlertCondition> show(long policyId, long conditionId)
    {
        Optional<NrqlAlertCondition> ret = Optional.absent();
        Collection<NrqlAlertCondition> conditions = list(policyId);
        for(NrqlAlertCondition condition : conditions)
        {
            if(condition.getId() == conditionId)
                ret = Optional.of(condition);
        }
        return ret;
    }
   
    /**
     * Creates the given NRQL alert condition.
     * @param policyId The id of the policy to add the alert condition to
     * @param condition The alert condition to create
     * @return The alert condition that was created
     */
    public Optional<NrqlAlertCondition> create(long policyId, NrqlAlertCondition condition)
    {
        return HTTP.POST(String.format("/alerts_nrql_conditions/policies/%d.json", policyId), condition, NRQL_ALERT_CONDITION);
    }

    /**
     * Updates the given NRQL alert condition.
     * @param condition The alert condition to update
     * @return The alert condition that was updated
     */
    public Optional<NrqlAlertCondition> update(NrqlAlertCondition condition)
    {
        return HTTP.PUT(String.format("/alerts_nrql_conditions/%d.json", condition.getId()), condition, NRQL_ALERT_CONDITION);
    }

    /**
     * Deletes the NRQL alert condition with the given id.
     * @param conditionId The id of the alert condition to delete
     * @return This object
     */
    public NrqlAlertConditionOperations delete(long conditionId)
    {
        HTTP.DELETE(String.format("/alerts_nrql_conditions/%d.json", conditionId));       
        return this;
    }
}
