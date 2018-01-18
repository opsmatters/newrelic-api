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
import com.opsmatters.newrelic.api.model.applications.BrowserApplication;
import com.opsmatters.newrelic.util.QueryParameterList;

/**
 * The set of operations used for Browser applications.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class BrowserApplicationService extends BaseFluent
{
    /**
     * Constructor that takes a http context and API client.
     * @param httpContext The set of HTTP operations
     * @param client The client used to invoke the New Relic operations
     */
    public BrowserApplicationService(HttpContext httpContext, NewRelicClient client)
    {
        super(httpContext, client);
    }

    /**
     * Returns the set of Browser applications.
     * @return The set of applications
     */
    public Collection<BrowserApplication> list()
    {
        return HTTP.GET("/v2/browser_applications.json", BROWSER_APPLICATIONS).get();
    }

    /**
     * Returns the Browser application for the given application id.
     * @param applicationId The id for the application to return
     * @return The application
     */
    public Optional<BrowserApplication> show(long applicationId)
    {
        QueryParameterList queryParams = new QueryParameterList();
        queryParams.add("filter[ids]", new Long(applicationId));
        return Optional.of(HTTP.GET("/v2/browser_applications.json", null, queryParams, BROWSER_APPLICATIONS).get().iterator().next());
    }

    /**
     * Creates the given Browser application.
     * @param application The application to create
     * @return The application that was created
     */
    public Optional<BrowserApplication> create(BrowserApplication application)
    {
        return HTTP.POST("/v2/browser_applications.json", application, BROWSER_APPLICATION);
    }
}
