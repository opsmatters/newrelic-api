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
import com.opsmatters.newrelic.api.model.deployments.Deployment;
import com.opsmatters.newrelic.api.util.QueryParameterList;

/**
 * The set of operations used for deployments.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class DeploymentService extends BaseFluent
{
    /**
     * Constructor that takes a http context and API client.
     * @param httpContext The set of HTTP operations
     * @param client The client used to invoke the New Relic operations
     */
    public DeploymentService(HttpContext httpContext, NewRelicClient client)
    {
        super(httpContext, client);
    }

    /**
     * Returns the set of deployments.
     * @param applicationId The application id for the deployments
     * @param queryParams The query parameters
     * @return The set of deployments
     */
    public Collection<Deployment> list(long applicationId, List<String> queryParams)
    {
        return HTTP.GET(String.format("/v2/applications/%d/deployments.json", applicationId), null, queryParams, DEPLOYMENTS).get();
    }

    /**
     * Returns the set of deployments.
     * @param applicationId The application id for the deployments
     * @return The set of deployments
     */
    public Collection<Deployment> list(long applicationId)
    {
        return list(applicationId, null);
    }

    /**
     * Returns the deployment with the given id.
     * <P>
     * This is needed because the API does not contain an operation to get a deployment using the id directly.
     * @param applicationId The application id for the deployments
     * @param deploymentId The id of the deployment to return
     * @return The deployment
     */
    public Optional<Deployment> show(long applicationId, long deploymentId)
    {
        Optional<Deployment> ret = Optional.absent();
        Collection<Deployment> deployments = list(applicationId);
        for(Deployment deployment : deployments)
        {
            if(deployment.getId() == deploymentId)
                ret = Optional.of(deployment);
        }
        return ret;
    }
    
    /**
     * Creates the given deployment.
     * @param applicationId The application id for the deployments
     * @param deployment The deployment to create
     * @return The deployment that was created
     */
    public Optional<Deployment> create(long applicationId, Deployment deployment)
    {
        return HTTP.POST(String.format("/v2/applications/%d/deployments.json", applicationId), deployment, DEPLOYMENT);
    }

    /**
     * Deletes the deployment with the given id.
     * @param applicationId The application id for the deployments
     * @param deploymentId The id of the deployment to delete
     * @return This object
     */
    public DeploymentService delete(long applicationId, long deploymentId)
    {
        HTTP.DELETE(String.format("/v2/applications/%d/deployments/%d.json", applicationId, deploymentId));       
        return this;
    }

    /**
     * Returns a builder for the deployment filters.
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