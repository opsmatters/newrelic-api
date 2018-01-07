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

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.GenericType;
import com.opsmatters.newrelic.httpclient.ApiKeyHttpClientProvider;
import com.opsmatters.newrelic.httpclient.HttpClientProvider;

/**
 * The set of Java bindings for the New Relic API Service.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class NewRelicApiService
{
    private static final Logger logger = Logger.getLogger(NewRelicApiService.class.getName());
 
    /**
     * The name of the standard property used for the API key.
     */
    public static final String API_KEY_PROPERTY = "newrelic.apiKey";

    /**
     * The default hostname for New Relic.
     */
    public static final String DEFAULT_HOST = "api.newrelic.com";

    /**
     * The default port for New Relic.
     */
    public static final int DEFAULT_PORT = 443;

    private String hostname = DEFAULT_HOST;
    private int port = DEFAULT_PORT;
    protected HttpContext httpContext;
    protected HttpClientProvider provider;
    private boolean handleErrors = true;
    
    /**
     * Default constructor.
     */
    public NewRelicApiService()
    {
    }
    
    /**
     * Constructor that takes a hostname, port and provider.
     * @param hostname The hostname of the server
     * @param port The port of the server
     * @param provider The HTTP client provider
     */
    public NewRelicApiService(String hostname, int port, HttpClientProvider provider)
    {    
        this.hostname = hostname;
        this.port = port;
        this.provider = provider;
    }
    
    /**
     * Called after setting configuration properties.
     * @return This object
     */
    public NewRelicApiService initialize()
    {    
        Client client = provider.getClient();
        String protocol = provider.useSsl() ? "https" : "http";
        httpContext = new HttpContext(client, protocol, hostname, port);
        httpContext.setThrowExceptions(handleErrors);
        String className = getClass().getName();
        logger.info(className.substring(className.lastIndexOf(".")+1)+" initialized");
        return this;
    }
    
    /**
     * Sets the HTTP client provider.
     * @param provider The interface for the underlying Jersey client provider
     */
    public void setHttpClientProvider(HttpClientProvider provider)
    {
        this.provider = provider;
    }

    /**
     * Sets the name of the host to connect to.
     * <P>
     * The default hostname is "api.newrelic.com".
     * @param hostname The name of the host
     */
    public void setHostname(String hostname)
    {
        this.hostname = hostname;
    }

    /**
     * Sets the port of the host to connect to.
     * <P>
     * The default port is 443.
     * @param port The port of the host
     */
    public void setPort(int port)
    {
        this.port = port;
    }
    
    /**
     * Returns the context used to make calls and deserialize payloads.
     * @return The HTTP context
     */
    public HttpContext getHttpContext()
    {    
        return this.httpContext;
    }

    /**
     * Set to <CODE>true</CODE> if errors should be handled rather than ignored.
     * @param b <CODE>true</CODE> if errors should be handled
     */
    public void setHandleErrors(boolean b)
    {
        handleErrors = b;
    }

    /**
     * Returns <CODE>true</CODE> if errors should be handled rather than ignored.
     * @return <CODE>true</CODE> if errors should be handled
     */
    public boolean handleErrors()
    {
        return handleErrors;
    }
  
    /**
     * Returns the operations related to alert policies.
     * @return The alert policy operations
     */
    public AlertPolicyOperations alertPolicies()
    {
        checkInitialize();
        return new AlertPolicyOperations(httpContext, this);
    }

    /**
     * Returns the operations related to APM alert conditions.
     * @return The APM alert condition operations
     */
    public AlertConditionOperations alertConditions()
    {
        checkInitialize();
        return new AlertConditionOperations(httpContext, this);
    }

    /**
     * Returns the operations related to NRQL alert conditions.
     * @return The NRQL alert condition operations
     */
    public NrqlAlertConditionOperations nrqlAlertConditions()
    {
        checkInitialize();
        return new NrqlAlertConditionOperations(httpContext, this);
    }

    /**
     * Returns the operations related to external service alert conditions.
     * @return The external service alert condition operations
     */
    public ExternalServiceAlertConditionOperations externalServiceAlertConditions()
    {
        checkInitialize();
        return new ExternalServiceAlertConditionOperations(httpContext, this);
    }

    /**
     * Returns the operations related to alert channels.
     * @return The alert channel operations
     */
    public AlertChannelOperations alertChannels()
    {
        checkInitialize();
        return new AlertChannelOperations(httpContext, this);
    }
    /**
     * Returns the operations related to alert policy channels.
     * @return The alert policy channel operations
     */
    public AlertPolicyChannelOperations alertPolicyChannels()
    {
        checkInitialize();
        return new AlertPolicyChannelOperations(httpContext, this);
    }

    /**
     * Initialise the HTTP client provider and context.
     */
    protected void checkInitialize()
    {
        if(httpContext == null)
            initialize();
    }

    /**
     * Returns a builder for the NewRelicApiService.
     * @return The builder instance.
     */
    public static ServiceBuilder<?,?> builder()
    {
        return new Builder();
    }

    /**
     * Abstract Builder from which the other service Builders derive.
     */
    public abstract static class ServiceBuilder<T extends NewRelicApiService, B extends ServiceBuilder<T,B>>
    {
        protected String hostname = DEFAULT_HOST;
        protected int port = DEFAULT_PORT;
        protected HttpClientProvider provider = new ApiKeyHttpClientProvider("");

        /**
         * Sets the name of the host to connect to.
         * <P>
         * The default hostname is "api.newrelic.com".
         * @param hostname The name of the host
         * @return This object
         */
        public B hostname(String hostname)
        {
            this.hostname = hostname;
            return self();
        }

        /**
         * Sets the port of the host to connect to.
         * <P>
         * The default port is 443.
         * @param port The port of the host
         * @return This object
         */
        public B port(int port)
        {
            this.port = port;
            return self();
        }

        /**
         * Sets the API key used to authenticate the connection.
         * @param key The API key
         * @return This object
         */
        public B apiKey(String key)
        {
            this.provider = new ApiKeyHttpClientProvider(key);
            return self();
        }

        /**
         * Returns this object.
         * @return This object
         */
        protected abstract B self();

        /**
         * Returns the configured API service instance
         * @return The API service instance
         */
        public abstract T build();
    }

    /**
     * Builder to make NewRelicApiService construction easier.
     */
    public static class Builder extends ServiceBuilder<NewRelicApiService, Builder>
    {
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
         * Returns the configured API service instance
         * @return The API service instance
         */
        @Override
        public NewRelicApiService build()
        {
            return new NewRelicApiService(hostname, port, provider);
        }
    }
}