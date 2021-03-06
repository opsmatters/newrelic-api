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
import com.opsmatters.newrelic.api.model.alerts.policies.AlertPolicy;
import com.opsmatters.newrelic.api.util.QueryParameterList;

/**
 * The set of operations used for alert policies.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class AlertPolicyService extends BaseFluent
{
    /**
     * Constructor that takes a http context and API client.
     * @param httpContext The set of HTTP operations
     * @param client The client used to invoke the New Relic operations
     */
    public AlertPolicyService(HttpContext httpContext, NewRelicClient client)
    {
        super(httpContext, client);
    }

    /**
     * Returns the set of alert policies.
     * @param queryParams The query parameters
     * @return The set of alert policies
     */
    public Collection<AlertPolicy> list(List<String> queryParams)
    {
        return HTTP.GET("/v2/alerts_policies.json", null, queryParams, ALERT_POLICIES).get();
    }

    /**
     * Returns the set of alert policies.
     * @return The set of alert policies
     */
    public Collection<AlertPolicy> list()
    {
        QueryParameterList queryParams = null;
        return list(queryParams);
    }

    /**
     * Returns the set of alert policies with the given name.
     * @param name The name of the alert policy to return
     * @return The set of alert policies
     */
    public Collection<AlertPolicy> list(String name)
    {
        return list(filters().name(name).build());
    }

    /**
     * Returns the alert policy with the given name and id.
     * <P>
     * This is needed because the API does not contain an operation to get a policy using the id directly, only filtering using the name.
     * @param name The name of the alert policy to return
     * @param policyId The id of the alert policy to return
     * @return The alert policy
     */
    public Optional<AlertPolicy> show(String name, long policyId)
    {
        Optional<AlertPolicy> ret = Optional.absent();
        Collection<AlertPolicy> policies = list(name);
        for(AlertPolicy policy : policies)
        {
            if(policy.getId() == policyId)
                ret = Optional.of(policy);
        }
        return ret;
    }

    /**
     * Returns the alert policy with the given id.
     * <P>
     * This is needed because the API does not contain an operation to get a policy using the id directly, only filtering using the name.
     * @param policyId The id of the alert policy to return
     * @return The alert policy
     */
    public Optional<AlertPolicy> show(long policyId)
    {
        return show(null, policyId);
    }
    
    /**
     * Creates the given alert policy.
     * @param policy The alert policy to create
     * @return The alert policy that was created
     */
    public Optional<AlertPolicy> create(AlertPolicy policy)
    {
        return HTTP.POST("/v2/alerts_policies.json", policy, ALERT_POLICY);
    }

    /**
     * Updates the given alert policy.
     * @param policy The alert policy to update
     * @return The alert policy that was updated
     */
    public Optional<AlertPolicy> update(AlertPolicy policy)
    {
        return HTTP.PUT(String.format("/v2/alerts_policies/%d.json", policy.getId()), policy, ALERT_POLICY);
    }

    /**
     * Deletes the alert policy with the given id.
     * @param policyId The id of the alert policy to delete
     * @return This object
     */
    public AlertPolicyService delete(long policyId)
    {
        HTTP.DELETE(String.format("/v2/alerts_policies/%d.json", policyId));       
        return this;
    }

    /**
     * Returns a builder for the alert policy filters.
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
            if(name != null)
                filters.add("filter[name]", name);
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
