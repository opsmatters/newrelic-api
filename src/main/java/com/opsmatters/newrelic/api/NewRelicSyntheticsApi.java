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
import com.opsmatters.newrelic.httpclient.ApiKeyHttpClientProvider;
import com.opsmatters.newrelic.httpclient.HttpClientProvider;
import com.opsmatters.newrelic.api.services.MonitorService;
import com.opsmatters.newrelic.api.services.LocationService;

/**
 * Client used to invoke New Relic operations using the Synthetics API.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class NewRelicSyntheticsApi extends NewRelicClient
{
    private static final Logger logger = Logger.getLogger(NewRelicSyntheticsApi.class.getName());
 
    /**
     * The default hostname for New Relic Synthetics.
     */
    public static final String DEFAULT_HOST = "synthetics.newrelic.com";

    /**
     * Default constructor.
     */
    public NewRelicSyntheticsApi()
    {
        setHostname(DEFAULT_HOST);
    }
    
    /**
     * Constructor that takes a hostname, port and provider.
     * @param hostname The hostname of the server
     * @param port The port of the server
     * @param provider The HTTP client provider
     */
    public NewRelicSyntheticsApi(String hostname, int port, HttpClientProvider provider)
    {    
        super(hostname, port, provider);
    }
    
    /**
     * Sets the name of the host to connect to.
     * <P>
     * The default hostname is "synthetics.newrelic.com".
     * @param hostname The name of the host
     */
    public void setHostname(String hostname)
    {
        super.setHostname(hostname);
    }

    /**
     * Returns the uri prefix for resources used by the client.
     * <P>
     * Defaults to "synthetics/api" for Synthetics.
     * @return The uri prefix for the client
     */
    @Override
    public String getUriPrefix()
    {
        return "synthetics/api";
    }

    /**
     * Returns the operations related to Synthetics monitors.
     * @return The Synthetics monitor service
     */
    public MonitorService monitors()
    {
        checkInitialize();
        return new MonitorService(httpContext, this);
    }

    /**
     * Returns the operations related to Synthetics locations.
     * @return The Synthetics location service
     */
    public LocationService locations()
    {
        checkInitialize();
        return new LocationService(httpContext, this);
    }

    /**
     * Returns a builder for the NewRelicSyntheticsApi.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make NewRelicSyntheticsApi construction easier.
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
         * The default hostname is "synthetics.newrelic.com".
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
         * Returns the configured synthetics API client instance
         * @return The synthetics API client instance
         */
        public NewRelicSyntheticsApi build()
        {
            return new NewRelicSyntheticsApi(hostname, port, provider);
        }
    }
}