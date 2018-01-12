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
import com.opsmatters.newrelic.api.model.policy.AlertPolicy;
import com.opsmatters.newrelic.util.QueryParameterList;

/**
 * The set of operations used for alert policies.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class AlertPolicyOperations extends BaseFluent
{
    /**
     * Constructor that takes a http context and API service.
     * @param httpContext The set of HTTP operations
     * @param apiService The set of API operations
     */
    public AlertPolicyOperations(HttpContext httpContext, NewRelicApiService apiService)
    {
        super(httpContext, apiService);
    }

    /**
     * Returns the set of alert policies.
     * @return The set of alert policies
     */
    public Collection<AlertPolicy> list()
    {
        return HTTP.GET("/alerts_policies.json", null, null, ALERT_POLICIES).get();
    }

    /**
     * Returns the set of alert policies with the given name.
     * @param name The name of the alert policy to return
     * @return The set of alert policies
     */
    public Collection<AlertPolicy> list(String name)
    {
        QueryParameterList queryParams = new QueryParameterList();
        queryParams.add("filter[name]", name);
        return HTTP.GET("/alerts_policies.json", null, queryParams, ALERT_POLICIES).get();
    }

    /**
     * Returns the alert policy with the given name and id.
     * <P>
     * This is needed because the API does not contain an operation to get a policy using the id directly, only filtering using the name.
     * @param name The name of the alert policy to return
     * @param id The id of the alert policy to return
     * @return The alert policy
     */
    public Optional<AlertPolicy> get(String name, long id)
    {
        Optional<AlertPolicy> ret = Optional.absent();
        Collection<AlertPolicy> policies = list(name);
        for(AlertPolicy policy : policies)
        {
            if(policy.getId() == id)
                ret = Optional.of(policy);
        }
        return ret;
    }
    
    /**
     * Creates the given alert policy.
     * @param policy The alert policy to create
     * @return The alert policy that was created
     */
    public Optional<AlertPolicy> create(AlertPolicy policy)
    {
        return HTTP.POST("/alerts_policies.json", policy, ALERT_POLICY);
    }

    /**
     * Updates the given alert policy.
     * @param policy The alert policy to update
     * @return The alert policy that was updated
     */
    public Optional<AlertPolicy> update(AlertPolicy policy)
    {
        return HTTP.PUT(String.format("/alerts_policies/%d.json", policy.getId()), policy, ALERT_POLICY);
    }

    /**
     * Deletes the alert policy with the given id.
     * @param id The id of the alert policy to delete
     * @return This object
     */
    public AlertPolicyOperations delete(long id)
    {
        HTTP.DELETE(String.format("/alerts_policies/%d.json", id));       
        return this;
    }
}
