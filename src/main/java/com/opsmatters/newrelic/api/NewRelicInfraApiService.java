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

import java.util.logging.Logger;
import com.opsmatters.newrelic.httpclient.HttpClientProvider;

/**
 * The set of Java bindings for the New Relic Infrastructure API Service.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class NewRelicInfraApiService extends NewRelicApiService
{
    private static final Logger logger = Logger.getLogger(NewRelicInfraApiService.class.getName());
 
    /**
     * The default hostname for New Relic Infrastructure.
     */
    public static final String DEFAULT_HOST = "infra-api.newrelic.com";

    /**
     * Default constructor.
     */
    public NewRelicInfraApiService()
    {
        setHostname(DEFAULT_HOST);
    }
    
    /**
     * Constructor that takes a hostname, port and provider.
     * @param hostname The hostname of the server
     * @param port The port of the server
     * @param provider The HTTP client provider
     */
    public NewRelicInfraApiService(String hostname, int port, HttpClientProvider provider)
    {    
        super(hostname, port, provider);
    }
    
    /**
     * Sets the name of the host to connect to.
     * <P>
     * The default hostname is "infra-api.newrelic.com".
     * @param hostname The name of the host
     */
    public void setHostname(String hostname)
    {
        super.setHostname(hostname);
    }

    /**
     * Returns the operations related to infra alert conditions.
     * @return The infra alert condition operations
     */
    public InfraAlertConditionOperations infraAlertConditions()
    {
        checkInitialize();
        return new InfraAlertConditionOperations(httpContext, this);
    }

    /**
     * Returns a builder for the NewRelicInfraApiService.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make NewRelicInfraApiService construction easier.
     */
    public static class Builder extends NewRelicApiService.ServiceBuilder<NewRelicInfraApiService, Builder>
    {
        /**
         * Default constructor.
         */
        public Builder()
        {
            hostname(DEFAULT_HOST);
        }

        /**
         * Sets the name of the host to connect to.
         * <P>
         * The default hostname is "infra-api.newrelic.com".
         * @param hostname The name of the host
         * @return This object
         */
        public Builder hostname(String hostname)
        {
            super.hostname(hostname);
            return this;
        }

        /**
         * Returns this object.
         * @return This object
         */
        @Override
        protected Builder self()
        {
            return this;
        }

        /**
         * Returns the configured infra API service instance
         * @return The infra API service instance
         */
        @Override
        public NewRelicInfraApiService build()
        {
            return new NewRelicInfraApiService(hostname, port, provider);
        }
    }
}