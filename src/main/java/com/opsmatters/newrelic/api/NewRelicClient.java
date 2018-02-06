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
import javax.ws.rs.client.Client;
import com.opsmatters.newrelic.api.services.HttpContext;
import com.opsmatters.newrelic.httpclient.HttpClientProvider;

/**
 * Client used to invoke New Relic operations using the REST API.
 * 
 * @author Gerald Curley (opsmatters)
 */
public abstract class NewRelicClient
{
    private static final Logger logger = Logger.getLogger(NewRelicClient.class.getName());
 
    /**
     * The default port for New Relic.
     */
    public static final int DEFAULT_PORT = 443;

    private String hostname = "";
    private int port = DEFAULT_PORT;
    protected HttpContext httpContext;
    protected HttpClientProvider provider;
    private boolean handleErrors = true;
    
    /**
     * Default constructor.
     */
    public NewRelicClient()
    {
    }
    
    /**
     * Constructor that takes a hostname, port and provider.
     * @param hostname The hostname of the server
     * @param port The port of the server
     * @param provider The HTTP client provider
     */
    public NewRelicClient(String hostname, int port, HttpClientProvider provider)
    {    
        this.hostname = hostname;
        this.port = port;
        this.provider = provider;
    }
    
    /**
     * Called after setting configuration properties.
     * @return This object
     */
    public NewRelicClient initialize()
    {    
        Client client = provider.getClient();
        String protocol = provider.useSsl() ? "https" : "http";
        httpContext = new HttpContext(client, protocol, hostname, port);
        httpContext.setUriPrefix(getUriPrefix());
        httpContext.setThrowExceptions(handleErrors);
        String className = getClass().getName();
        logger.fine(className.substring(className.lastIndexOf(".")+1)+" initialized");
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
     * Returns the uri prefix for resources used by the client.
     * <P>
     * Defaults to an empty prefix. Where used, should always start with a "/".
     * @return The uri prefix for the client
     */
    public String getUriPrefix()
    {
        return "";
    }
  
    /**
     * Initialise the HTTP client provider and context.
     */
    protected void checkInitialize()
    {
        if(httpContext == null)
            initialize();
    }
}