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

import java.util.Collection;
import com.google.common.base.Optional;
import com.opsmatters.newrelic.api.NewRelicClient;
import com.opsmatters.newrelic.api.model.insights.QueryData;
import com.opsmatters.newrelic.api.util.QueryParameterList;

/**
 * The set of operations used for Insights queries.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class QueryService extends BaseFluent
{
    /**
     * Constructor that takes a http context and API client.
     * @param httpContext The set of HTTP operations
     * @param client The client used to invoke the New Relic operations
     */
    public QueryService(HttpContext httpContext, NewRelicClient client)
    {
        super(httpContext, client);
    }

    /**
     * Returns the set of data for a query.
     * @param accountId The id of the account to query
     * @param query The NRQL query to execute
     * @return The query data
     */
    public Optional<QueryData> list(long accountId, String query)
    {
        QueryParameterList queryParams = new QueryParameterList();
        queryParams.add("nrql", encode(query));
        return HTTP.GET(String.format("/v1/accounts/%d/query", accountId), null, queryParams, QUERY_DATA);
    }
}
