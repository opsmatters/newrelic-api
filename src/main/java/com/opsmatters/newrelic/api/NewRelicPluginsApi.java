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
import com.opsmatters.newrelic.api.httpclient.LicenseKeyHttpClientProvider;
import com.opsmatters.newrelic.api.httpclient.HttpClientProvider;
import com.opsmatters.newrelic.api.services.PluginMetricService;

/**
 * Client used to invoke New Relic operations using the Plugins API.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class NewRelicPluginsApi extends NewRelicClient
{
    private static final Logger logger = Logger.getLogger(NewRelicPluginsApi.class.getName());
 
    /**
     * The default hostname for New Relic Plugins.
     */
    public static final String DEFAULT_HOST = "platform-api.newrelic.com";

    /**
     * Default constructor.
     */
    public NewRelicPluginsApi()
    {
        setHostname(DEFAULT_HOST);
    }
    
    /**
     * Constructor that takes a hostname, port and provider.
     * @param hostname The hostname of the server
     * @param port The port of the server
     * @param provider The HTTP client provider
     */
    public NewRelicPluginsApi(String hostname, int port, HttpClientProvider provider)
    {    
        super(hostname, port, provider);
    }
    
    /**
     * Sets the name of the host to connect to.
     * <P>
     * The default hostname is "platform-api.newrelic.com".
     * @param hostname The name of the host
     */
    public void setHostname(String hostname)
    {
        super.setHostname(hostname);
    }

    /**
     * Returns the uri prefix for resources used by the client.
     * <P>
     * Defaults to "/platform" for Plugins.
     * @return The uri prefix for the client
     */
    @Override
    public String getUriPrefix()
    {
        return "/platform";
    }

    /**
     * Returns the operations related to Plugins metrics.
     * @return The Plugins metric service
     */
    public PluginMetricService metrics()
    {
        checkInitialize();
        return new PluginMetricService(httpContext, this);
    }

    /**
     * Returns a builder for the NewRelicPluginsApi.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make NewRelicPluginsApi construction easier.
     */
    public static class Builder
    {
        private String hostname = DEFAULT_HOST;
        private int port = DEFAULT_PORT;
        private HttpClientProvider provider = new LicenseKeyHttpClientProvider("");

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
         * The default hostname is "platform-api.newrelic.com".
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
         * Sets the license key used to authenticate the connection.
         * @param key The license key
         * @return This object
         */
        public Builder licenseKey(String key)
        {
            this.provider = new LicenseKeyHttpClientProvider(key);
            return this;
        }

        /**
         * Returns the configured Plugins API client instance
         * @return The Plugins API client instance
         */
        public NewRelicPluginsApi build()
        {
            return new NewRelicPluginsApi(hostname, port, provider);
        }
    }
}