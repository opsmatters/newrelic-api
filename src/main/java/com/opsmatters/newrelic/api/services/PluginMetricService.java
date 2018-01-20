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

import com.google.common.base.Optional;
import com.opsmatters.newrelic.api.NewRelicClient;
import com.opsmatters.newrelic.api.model.plugins.PluginData;
import com.opsmatters.newrelic.api.model.Status;

/**
 * The set of operations used for sending plugin metric data.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class PluginMetricService extends BaseFluent
{
    /**
     * Constructor that takes a http context and API client.
     * @param httpContext The set of HTTP operations
     * @param client The client used to invoke the New Relic operations
     */
    public PluginMetricService(HttpContext httpContext, NewRelicClient client)
    {
        super(httpContext, client);
    }

    /**
     * Sends the given metric plugin data to New Relic.
     * @param data The metric data to send
     * @return The status returned by the server
     */
    public Optional<Status> metricData(PluginData data)
    {
        return HTTP.POST("/v1/metrics", data, STATUS);
    }
}
