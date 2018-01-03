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

import java.util.Map;
import com.google.common.base.Optional;
import com.google.common.collect.Maps;
import com.opsmatters.newrelic.api.model.AlertPolicyChannel;

/**
 * The set of operations used for alert policy channels.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class AlertPolicyChannelOperations extends BaseFluent
{
    /**
     * Constructor that takes a http context and API service.
     * @param httpContext The set of HTTP operations
     * @param apiService The set of API operations
     */
    public AlertPolicyChannelOperations(HttpContext httpContext, NewRelicApiService apiService)
    {
        super(httpContext, apiService);
    }

    /**
     * Adds the given alert channel to the alert policy with the given id.
     * @param policyId The id of the alert policy to add the channel to
     * @param id The id of the alert channel to add
     * @return The alert policy channel that was updated
     */
    public Optional<AlertPolicyChannel> update(long policyId, long id)
    {
        Map<String,Object> queryParams = Maps.newHashMap();
        queryParams.put("policy_id", policyId);
        queryParams.put("channel_ids", id);
        return HTTP.PUT("/alerts_policy_channels.json", null, null, queryParams, ALERT_POLICY_CHANNEL);
    }

    /**
     * Deletes the given alert channel from the alert policy with the given id.
     * @param policyId The id of the alert policy from which to delete the channel
     * @param id The id of the alert channel to delete
     * @return This object
     */
    public AlertPolicyChannelOperations delete(long policyId, long id)
    {
        Map<String,Object> queryParams = Maps.newHashMap();
        queryParams.put("policy_id", policyId);
        queryParams.put("channel_id", id);
        HTTP.DELETE("/alerts_policy_channels.json", null, queryParams);       
        return this;
    }
}
