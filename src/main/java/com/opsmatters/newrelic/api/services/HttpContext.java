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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import com.google.gson.Gson;
import com.google.common.base.Optional;
import com.opsmatters.newrelic.api.model.Errors;

/**
 * Represents the set of HTTP operations to be used with the API calls.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class HttpContext
{
    private static final Logger logger = Logger.getLogger(HttpContext.class.getName());
    
    private Client client;
    private String protocol = "http";
    private String hostname;
    private int port;
    private Gson gson = new Gson();
    private boolean throwExceptions = false;
    private String uriPrefix = "";

    private static final GenericType<Errors> ERRORS = new GenericType<Errors>(){};
    
    /**
     * Constructor that takes a client, protocol, hostname and port.
     * @param client The Jersey client
     * @param protocol The protocol used to connect to the server
     * @param hostname The hostname of the server
     * @param port The port of the server
     */
    public HttpContext(Client client, String protocol, String hostname, int port)
    {
        this.protocol = protocol;
        this.hostname = hostname;
        this.port = port;
        this.client = client;
    }
    
    /**
     * Build the URL from the protocol://hostname:port + uriPrefix + relativePath
     * @param relativePath The path of the resource (should always start with a "/")
     * @return The URL to call
     */
    String buildUrl(String relativePath)
    {
        return String.format("%s://%s:%s%s%s", this.protocol, this.hostname, this.port, this.uriPrefix, relativePath);
    }
    
    /**
     * Build the URI from the protocol://hostname:port + uriPrefix + relativePath.
     * @param relativePath The path of the resource (should always start with a "/")
     * @return The URI to call
     */
    URI buildUri(String relativePath)
    {    
        URI uri = null;
        
        try
        {    
            uri = new URI(buildUrl(relativePath));   
        }
        catch (URISyntaxException e)
        {           
            logger.severe("Problem constructing URI: "+e.getClass().getName()+e.getMessage());
        }

        return uri;
    }
    
    /**
     * Execute a GET call against the partial URL and deserialize the results.
     * @param <T> The type parameter used for the return object
     * @param partialUrl The partial URL to build
     * @param returnType The expected return type
     * @return The return type
     */
    public <T> Optional<T> GET(String partialUrl, GenericType<T> returnType)
    {    
        URI uri = buildUri(partialUrl);   
        return executeGetRequest(uri, returnType);
    }

    /**
     * Execute a GET call against the partial URL and deserialize the results.
     * @param <T> The type parameter used for the return object
     * @param partialUrl The partial URL to build
     * @param returnType The expected return type
     * @param headers A set of headers to add to the request
     * @param queryParams A set of query parameters to add to the request
     * @return The return type
     */
    public <T> Optional<T> GET(String partialUrl, Map<String, Object> headers, 
        List<String> queryParams, GenericType<T> returnType)
    {
        URI uri = buildUri(partialUrl);
        return executeGetRequest(uri, headers, queryParams, returnType);
    }
    
    /**
     * Execute a PUT call against the partial URL.
     * @param partialUrl The partial URL to build
     * @param payload The object to use for the PUT
     */
    public void PUT(String partialUrl, Object payload)
    {    
        URI uri = buildUri(partialUrl);   
        executePutRequest(uri, payload);
    }

    /**
     * Execute a PUT call against the partial URL.
     * @param partialUrl The partial URL to build
     * @param payload The object to use for the PUT
     * @param headers A set of headers to add to the request
     * @param queryParams A set of query parameters to add to the request
     */
    public void PUT(String partialUrl, Object payload, Map<String, Object> headers, 
        List<String> queryParams)
    {
        URI uri = buildUri(partialUrl);
        executePutRequest(uri, payload, headers, queryParams);
    }

    /**
     * Execute a PUT call against the partial URL.
     * @param <T> The type parameter used for the return object
     * @param partialUrl The partial URL to build
     * @param payload The object to use for the PUT
     * @param returnType The expected return type
     * @return The return type
     */
    public <T> Optional<T> PUT(String partialUrl, Object payload, GenericType<T> returnType)
    {    
        URI uri = buildUri(partialUrl);   
        return executePutRequest(uri, payload, null, null, returnType);
    }

    /**
     * Execute a PUT call against the partial URL.
     * @param <T> The type parameter used for the return object
     * @param partialUrl The partial URL to build
     * @param payload The object to use for the PUT
     * @param headers A set of headers to add to the request
     * @param queryParams A set of query parameters to add to the request
     * @param returnType The expected return type
     * @return The return type
     */
    public <T> Optional<T> PUT(String partialUrl, Object payload, 
        Map<String, Object> headers, List<String> queryParams, GenericType<T> returnType)
    {
        URI uri = buildUri(partialUrl);
        return executePutRequest(uri, payload, headers, queryParams, returnType);
    }

    /**
     * Execute a POST call against the partial URL.
     * @param partialUrl The partial URL to build
     * @param payload The object to use for the POST
     * @return The response to the POST
     */
    public Optional<Response> POST(String partialUrl, Object payload)
    {    
        URI uri = buildUri(partialUrl);   
        return executePostRequest(uri, payload);
    }

    /**
     * Execute a POST call against the partial URL.
     * @param partialUrl The partial URL to build
     * @param payload The object to use for the POST
     * @param headers A set of headers to add to the request
     * @return The response to the POST
     */
    public Optional<Response> POST(String partialUrl, Object payload, Map<String, Object> headers)
    {
        URI uri = buildUri(partialUrl);
        return executePostRequest(uri, payload, headers);
    }

    /**
     * Execute a POST call against the partial URL.
     * @param <T> The type parameter used for the return object
     * @param partialUrl The partial URL to build
     * @param payload The object to use for the POST
     * @param returnType The expected return type
     * @return The return type
     */
    public <T> Optional<T> POST(String partialUrl, Object payload, GenericType<T> returnType)
    {
        URI uri = buildUri(partialUrl);
        return executePostRequest(uri, payload, returnType);
    }

    /**
     * Execute a POST call against the partial URL.
     * @param <T> The type parameter used for the return object
     * @param partialUrl The partial URL to build
     * @param payload The object to use for the POST
     * @param headers A set of headers to add to the request
     * @param returnType The expected return type
     * @return The return type
     */
    public <T> Optional<T> POST(String partialUrl, Object payload, 
        Map<String, Object> headers, GenericType<T> returnType)
    {
        URI uri = buildUri(partialUrl);
        return executePostRequest(uri, payload, headers, returnType);
    }

    /**
     * Execute a PATCH call against the partial URL.
     * @param partialUrl The partial URL to build
     * @param payload The object to use for the PATCH
     */
    public void PATCH(String partialUrl, Object payload)
    {    
        URI uri = buildUri(partialUrl);   
        executePatchRequest(uri, payload);
    }

    /**
     * Execute a PATCH call against the partial URL.
     * @param partialUrl The partial URL to build
     * @param payload The object to use for the PATCH
     * @param headers A set of headers to add to the request
     * @param queryParams A set of query parameters to add to the request
     */
    public void PATCH(String partialUrl, Object payload, Map<String, Object> headers, 
        List<String> queryParams)
    {
        URI uri = buildUri(partialUrl);
        executePatchRequest(uri, payload, headers, queryParams);
    }

    /**
     * Execute a PATCH call against the partial URL.
     * @param <T> The type parameter used for the return object
     * @param partialUrl The partial URL to build
     * @param payload The object to use for the PATCH
     * @param returnType The expected return type
     * @return The return type
     */
    public <T> Optional<T> PATCH(String partialUrl, Object payload, GenericType<T> returnType)
    {    
        URI uri = buildUri(partialUrl);   
        return executePatchRequest(uri, payload, null, null, returnType);
    }

    /**
     * Execute a PATCH call against the partial URL.
     * @param <T> The type parameter used for the return object
     * @param partialUrl The partial URL to build
     * @param payload The object to use for the PATCH
     * @param headers A set of headers to add to the request
     * @param queryParams A set of query parameters to add to the request
     * @param returnType The expected return type
     * @return The return type
     */
    public <T> Optional<T> PATCH(String partialUrl, Object payload, 
        Map<String, Object> headers, List<String> queryParams, GenericType<T> returnType)
    {
        URI uri = buildUri(partialUrl);
        return executePatchRequest(uri, payload, headers, queryParams, returnType);
    }
    
    /**
     * Execute a DELETE call against the partial URL.
     * @param partialUrl The partial URL to build
     */
    public void DELETE(String partialUrl)
    {    
        URI uri = buildUri(partialUrl);   
        executeDeleteRequest(uri);
    }

    /**
     * Execute a DELETE call against the partial URL.
     * @param partialUrl The partial URL to build
     * @param headers A set of headers to add to the request
     * @param queryParams A set of query parameters to add to the request
     */
    public void DELETE(String partialUrl, Map<String, Object> headers, 
        List<String> queryParams)
    {
        URI uri = buildUri(partialUrl);
        executeDeleteRequest(uri, headers, queryParams);
    }
    
    /**
     * Execute a GET request and return the result.
     * @param <T> The type parameter used for the return object
     * @param uri The URI to call
     * @param returnType The type to marshall the result back into
     * @return The return type
     */
    protected <T> Optional<T> executeGetRequest(URI uri, GenericType<T> returnType)
    {
        return executeGetRequest(uri, null, null, returnType);
    }

    /**
     * Execute a GET request and return the result.
     * @param <T> The type parameter used for the return object
     * @param uri The URI to call
     * @param returnType The type to marshall the result back into
     * @param headers A set of headers to add to the request
     * @param queryParams A set of query parameters to add to the request
     * @return The return type
     */
    protected <T> Optional<T> executeGetRequest(URI uri, Map<String, Object> headers, 
        List<String> queryParams, GenericType<T> returnType)
    {
        WebTarget target = this.client.target(uri);
        target = applyQueryParams(target, queryParams);
        Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
        applyHeaders(invocation, headers);
        Response response = invocation.get();
        handleResponseError("GET", uri, response);
        logResponse(uri, response);
        return extractEntityFromResponse(response, returnType);
    }

    /**
     * Execute a PUT request.
     * @param uri The URI to call
     * @param obj The object to use for the PUT
     */
    protected void executePutRequest(URI uri, Object obj)
    {
        executePutRequest(uri, obj, null, null);
    }

    /**
     * Execute a PUT request.
     * @param uri The URI to call
     * @param obj The object to use for the PUT
     * @param headers A set of headers to add to the request
     * @param queryParams A set of query parameters to add to the request
     */
    protected void executePutRequest(URI uri, Object obj, Map<String, Object> headers, 
        List<String> queryParams)
    {
        WebTarget target = this.client.target(uri);
        target = applyQueryParams(target, queryParams);
        Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
        applyHeaders(invocation, headers);
        if(obj == null)
            obj = Entity.text("");
        Response response = invocation.put(Entity.entity(obj, MediaType.APPLICATION_JSON));
        handleResponseError("PUT", uri, response);
        logResponse(uri, response);
    }

    /**
     * Execute a PUT request with a return object.
     * @param <T> The type parameter used for the return object
     * @param uri The URI to call
     * @param obj The object to use for the PUT
     * @param headers A set of headers to add to the request
     * @param queryParams A set of query parameters to add to the request
     * @param returnType The type to marshall the result back into
     * @return The return type
     */
    protected <T> Optional<T> executePutRequest(URI uri, Object obj, Map<String, Object> headers, 
        List<String> queryParams, GenericType<T> returnType)
    {
        WebTarget target = this.client.target(uri);
        target = applyQueryParams(target, queryParams);
        Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
        applyHeaders(invocation, headers);
        if(obj == null)
            obj = Entity.text("");
        Response response = invocation.put(Entity.entity(obj, MediaType.APPLICATION_JSON));
        handleResponseError("PUT", uri, response);
        logResponse(uri, response);
        return extractEntityFromResponse(response, returnType);
    }
    
    /**
     * Execute a POST request.
     * @param uri The URI to call
     * @param obj The object to use for the POST
     * @return The response to the POST
     */
    protected Optional<Response> executePostRequest(URI uri, Object obj)
    {    
        return executePostRequest(uri, obj, (Map<String, Object>)null);
    }

    /**
     * Execute a POST request.
     * @param uri The URI to call
     * @param obj The object to use for the POST
     * @param headers A set of headers to add to the request
     * @return The response to the POST
     */
    protected Optional<Response> executePostRequest(URI uri, Object obj, Map<String, Object> headers)
    {
        Invocation.Builder invocation = this.client.target(uri).request(MediaType.APPLICATION_JSON);
        applyHeaders(invocation, headers);
        Response response = invocation.post(Entity.entity(obj, MediaType.APPLICATION_JSON));
        handleResponseError("POST", uri, response);
        logResponse(uri, response);
        return Optional.of(response);
    }

    /**
     * Execute a POST request with a return object.
     * @param <T> The type parameter used for the return object
     * @param uri The URI to call
     * @param obj The object to use for the POST
     * @param returnType The type to marshall the result back into
     * @return The return type
     */
    protected <T> Optional<T> executePostRequest(URI uri, Object obj, GenericType<T> returnType)
    {
        return executePostRequest(uri, obj, null, returnType);
    }

    /**
     * Execute a POST request with a return object.
     * @param <T> The type parameter used for the return object
     * @param uri The URI to call
     * @param obj The object to use for the POST
     * @param headers A set of headers to add to the request
     * @param returnType The type to marshall the result back into
     * @return The return type
     */
    protected <T> Optional<T> executePostRequest(URI uri, Object obj, Map<String, Object> headers, GenericType<T> returnType)
    {
        Invocation.Builder invocation = this.client.target(uri).request(MediaType.APPLICATION_JSON);
        applyHeaders(invocation, headers);
        Response response = invocation.post(Entity.entity(obj, MediaType.APPLICATION_JSON));
        handleResponseError("POST", uri, response);
        logResponse(uri, response);
        return extractEntityFromResponse(response, returnType);
    }

    /**
     * Execute a PATCH request.
     * @param uri The URI to call
     * @param obj The object to use for the PATCH
     */
    protected void executePatchRequest(URI uri, Object obj)
    {
        executePatchRequest(uri, obj, null, null);
    }

    /**
     * Execute a PATCH request.
     * @param uri The URI to call
     * @param obj The object to use for the PATCH
     * @param headers A set of headers to add to the request
     * @param queryParams A set of query parameters to add to the request
     */
    protected void executePatchRequest(URI uri, Object obj, Map<String, Object> headers, 
        List<String> queryParams)
    {
        WebTarget target = this.client.target(uri);
        target = applyQueryParams(target, queryParams);
        Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
        applyHeaders(invocation, headers);
        if(obj == null)
            obj = Entity.text("");
        Response response = invocation.method("PATCH", Entity.entity(obj, MediaType.APPLICATION_JSON));
        handleResponseError("PATCH", uri, response);
        logResponse(uri, response);
    }

    /**
     * Execute a PATCH request with a return object.
     * @param <T> The type parameter used for the return object
     * @param uri The URI to call
     * @param obj The object to use for the PATCH
     * @param headers A set of headers to add to the request
     * @param queryParams A set of query parameters to add to the request
     * @param returnType The type to marshall the result back into
     * @return The return type
     */
    protected <T> Optional<T> executePatchRequest(URI uri, Object obj, Map<String, Object> headers, 
        List<String> queryParams, GenericType<T> returnType)
    {
        WebTarget target = this.client.target(uri);
        target = applyQueryParams(target, queryParams);
        Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
        applyHeaders(invocation, headers);
        if(obj == null)
            obj = Entity.text("");
        Response response = invocation.method("PATCH", Entity.entity(obj, MediaType.APPLICATION_JSON));
        handleResponseError("PATCH", uri, response);
        logResponse(uri, response);
        return extractEntityFromResponse(response, returnType);
    }

    /**
     * Execute a DELETE request.
     * @param uri The URI to call
     */
    protected void executeDeleteRequest(URI uri)
    {
        executeDeleteRequest(uri, null, null);
    }

    /**
     * Execute a DELETE request.
     * @param uri The URI to call
     * @param headers A set of headers to add to the request
     * @param queryParams A set of query parameters to add to the request
     */
    protected void executeDeleteRequest(URI uri, Map<String, Object> headers, 
        List<String> queryParams)
    {
        WebTarget target = this.client.target(uri);
        target = applyQueryParams(target, queryParams);
        Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
        applyHeaders(invocation, headers);
        Response response = invocation.delete();
        handleResponseError("DELETE", uri, response);
        logResponse(uri, response);
    }

    /**
     * Extract the entity from the HTTP response.
     * @param <T> The type parameter used for the return object
     * @param response The HTTP response to extract the entity from
     * @param returnType The type to marshall the result back into
     * @return The extracted entity
     */
    private <T> Optional<T> extractEntityFromResponse(Response response, GenericType<T> returnType)
    {
        if(response.hasEntity() && (response.getStatus() == 200 || response.getStatus() == 201))
            return Optional.of(response.readEntity(returnType));
        return Optional.absent();
    }

    /**
     * Add the given set of headers to the web target.
     * @param builder The invocation to add the headers to
     * @param headers The headers to add
     */
    private void applyHeaders(Invocation.Builder builder, Map<String, Object> headers)
    {
        if(headers != null)
        {
            for (Map.Entry<String, Object> e : headers.entrySet())
            {
                builder.header(e.getKey(), e.getValue());
            }
        }
    }

    /**
     * Add the given set of query parameters to the web target.
     * @param target The web target to add the parameters to
     * @param queryParams The query parameters to add
     * @return The updated target
     */
    private WebTarget applyQueryParams(WebTarget target, List<String> queryParams)
    {
        if(queryParams != null)
        {
            for(int i = 0; i < queryParams.size(); i += 2)
            {
                target = target.queryParam(queryParams.get(i), queryParams.get(i+1));
            }
        }

        return target;
    }

    /**
     * Log a HTTP error response.
     * @param uri The URI used for the HTTP call
     * @param response The HTTP call response
     */
    private void logResponse(URI uri, Response response)
    {
        if(logger.isLoggable(Level.FINE))
            logger.fine(uri.toString()+" => "+response.getStatus());
        if(response.getStatus() > 300)
            logger.warning(response.toString());
    }

    /**
     * Handle HTTP error responses if {@link #throwExceptions() throwExceptions} returns <CODE>true</CODE>.
     * @param method The HTTP method type
     * @param uri The URI used for the HTTP call
     * @param response The HTTP call response
     */
    private void handleResponseError(String method, URI uri, Response response)
    {
        if(throwExceptions 
             && response.getStatus() != 200 
             && response.getStatus() != 201 
             && response.getStatus() != 204)
        {
            Errors errors = null;
            if(response.hasEntity())
                errors = response.readEntity(ERRORS);
            throw new RuntimeException(method+" returned response "
                +response.getStatus()+" "+response.getStatusInfo().getReasonPhrase()
                +(errors != null ? " ("+errors+")" : ""));
        }
    }

    /**
     * Set to <CODE>true</CODE> if an exception should be thrown on an error HTTP response.
     * @param throwExceptions <CODE>true</CODE> if an exception should be thrown on an error HTTP response
     */
    public void setThrowExceptions(boolean throwExceptions)
    {
        this.throwExceptions = throwExceptions;
    }

    /**
     * Returns <CODE>true</CODE> if an exception should be thrown on an error HTTP response.
     * @return <CODE>true</CODE> if an exception should be thrown on an error HTTP response
     */
    public boolean throwExceptions()
    {
        return throwExceptions;
    }

    /**
     * Sets the uri prefix for resources used by the client.
     * @param uriPrefix The uri prefix for the client
     */
    public void setUriPrefix(String uriPrefix)
    {
        this.uriPrefix = uriPrefix;
    }

    /**
     * Returns the uri prefix for resources used by the client.
     * @return The uri prefix for the client
     */
    public String getUriPrefix()
    {
        return uriPrefix;
    }
}
