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
import com.opsmatters.newrelic.api.httpclient.ApiKeyHttpClientProvider;
import com.opsmatters.newrelic.api.httpclient.HttpClientProvider;
import com.opsmatters.newrelic.api.services.InfraAlertConditionService;

/**
 * Client used to invoke New Relic operations using the Infrastructure API.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class NewRelicInfraApi extends NewRelicClient
{
    private static final Logger logger = Logger.getLogger(NewRelicInfraApi.class.getName());
 
    /**
     * The default hostname for New Relic Infrastructure.
     */
    public static final String DEFAULT_HOST = "infra-api.newrelic.com";

    /**
     * Default constructor.
     */
    public NewRelicInfraApi()
    {
        setHostname(DEFAULT_HOST);
    }
    
    /**
     * Constructor that takes a hostname, port and provider.
     * @param hostname The hostname of the server
     * @param port The port of the server
     * @param provider The HTTP client provider
     */
    public NewRelicInfraApi(String hostname, int port, HttpClientProvider provider)
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
     * @return The infra alert condition service
     */
    public InfraAlertConditionService infraAlertConditions()
    {
        checkInitialize();
        return new InfraAlertConditionService(httpContext, this);
    }

    /**
     * Returns a builder for the NewRelicInfraApi.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make NewRelicInfraApi construction easier.
     */
    public static class Builder
    {
        private String hostname = DEFAULT_HOST;
        private int port = DEFAULT_PORT;
        private HttpClientProvider provider = new ApiKeyHttpClientProvider("");

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
            this.hostname = hostname;
            return this;
        }

        /**
         * Sets the port of the host to connect to.
         * <P>
         * The default port is 443.
         * @param port The port of the host
         * @return This object
         */
        public Builder port(int port)
        {
            this.port = port;
            return this;
        }

        /**
         * Sets the API key used to authenticate the connection.
         * @param key The API key
         * @return This object
         */
        public Builder apiKey(String key)
        {
            this.provider = new ApiKeyHttpClientProvider(key);
            return this;
        }

        /**
         * Returns the configured infra API client instance
         * @return The infra API client instance
         */
        public NewRelicInfraApi build()
        {
            return new NewRelicInfraApi(hostname, port, provider);
        }
    }
}