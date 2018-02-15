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

package com.opsmatters.newrelic.api.httpclient.filters;

import java.io.IOException;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;

/**
 * Filter to attach a Query key used for authentication.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class QueryKeyFilter implements ClientRequestFilter
{ 
    private String querykey;

    /**
     * Constructor that takes a Query key.
     * @param querykey The Query key
     */
    public QueryKeyFilter(String querykey)
    {
        this.querykey = querykey;
    }
   
    /**
     * Adds the Query key to the client request.
     * @param request The client request
     */
    public void filter(ClientRequestContext request) throws IOException
    {
        if(!request.getHeaders().containsKey("X-Query-Key"))
            request.getHeaders().add("X-Query-Key", this.querykey);
    } 
}
