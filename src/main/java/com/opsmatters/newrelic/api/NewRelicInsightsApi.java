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
import com.opsmatters.newrelic.api.httpclient.QueryKeyHttpClientProvider;
import com.opsmatters.newrelic.api.httpclient.HttpClientProvider;
import com.opsmatters.newrelic.api.services.QueryService;

/**
 * Client used to invoke New Relic operations using the Insights API.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class NewRelicInsightsApi extends NewRelicClient
{
    private static final Logger logger = Logger.getLogger(NewRelicInsightsApi.class.getName());
 
    /**
     * The default hostname for New Relic Insights.
     */
    public static final String DEFAULT_HOST = "insights-api.newrelic.com";

    /**
     * Default constructor.
     */
    public NewRelicInsightsApi()
    {
        setHostname(DEFAULT_HOST);
    }
    
    /**
     * Constructor that takes a hostname, port and provider.
     * @param hostname The hostname of the server
     * @param port The port of the server
     * @param provider The HTTP client provider
     */
    public NewRelicInsightsApi(String hostname, int port, HttpClientProvider provider)
    {    
        super(hostname, port, provider);
    }
    
    /**
     * Sets the name of the host to connect to.
     * <P>
     * The default hostname is "insights-api.newrelic.com".
     * @param hostname The name of the host
     */
    public void setHostname(String hostname)
    {
        super.setHostname(hostname);
    }

    /**
     * Returns the operations related to Insights queries.
     * @return The Insights query service
     */
    public QueryService queries()
    {
        checkInitialize();
        return new QueryService(httpContext, this);
    }

    /**
     * Returns a builder for the NewRelicInsightsApi.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make NewRelicInsightsApi construction easier.
     */
    public static class Builder
    {
        private String hostname = DEFAULT_HOST;
        private int port = DEFAULT_PORT;
        private HttpClientProvider provider = new QueryKeyHttpClientProvider("");

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
         * The default hostname is "insights-api.newrelic.com".
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
         * Sets the Query key used to authenticate the connection.
         * @param key The Query key
         * @return This object
         */
        public Builder queryKey(String key)
        {
            this.provider = new QueryKeyHttpClientProvider(key);
            return this;
        }

        /**
         * Returns the configured Insights API client instance
         * @return The Insights API client instance
         */
        public NewRelicInsightsApi build()
        {
            return new NewRelicInsightsApi(hostname, port, provider);
        }
    }
}