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

package com.opsmatters.newrelic.api.model.channels;

import java.util.Map;
import com.google.common.collect.Maps;
import com.google.gson.annotations.SerializedName;

/**
 * Represents a New Relic Webhook configuration.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class WebhookConfiguration extends ChannelConfiguration
{
    /**
     * The type of the channel configuration.
     */
    public static final ChannelType TYPE = ChannelType.WEBHOOK;

    @SerializedName("base_url")
    private String baseUrl;

    @SerializedName("auth_username")
    private String authUsername;

    @SerializedName("auth_password")
    private String authPassword;

    @SerializedName("payload_type")
    private String payloadType;

    private Map<String,Object> payload = Maps.newHashMap();
    private Map<String,Object> headers = Maps.newHashMap();

    /**
     * Default constructor.
     */
    public WebhookConfiguration()
    {
        super(TYPE.value());
    }
   
    /**
     * Sets the base URL for the alerts.
     * @param baseUrl The base URL for the alerts
     */
    public void setBaseUrl(String baseUrl)
    {
        this.baseUrl = baseUrl;
    }

    /**
     * Returns the base URL for the alerts.
     * @return The base URL for the alerts
     */
    public String getBaseUrl()
    {
        return baseUrl;
    }

    /**
     * Sets the username for the alerts.
     * @param username The username for the alerts
     */
    public void setAuthUsername(String username)
    {
        this.authUsername = username;
    }

    /**
     * Returns the username for the alerts.
     * @return The username for the alerts
     */
    public String getAuthUsername()
    {
        return authUsername;
    }

    /**
     * Sets the password for the alerts.
     * @param password The password for the alerts
     */
    public void setAuthPassword(String password)
    {
        this.authPassword = password;
    }

    /**
     * Returns the password for the alerts.
     * @return The password for the alerts
     */
    public String getAuthPassword()
    {
        return authPassword;
    }

    /**
     * Sets the payload type for the alerts.
     * @param payloadType The payload type for the alerts
     */
    public void setPayloadType(String payloadType)
    {
        this.payloadType = payloadType;
    }

    /**
     * Returns the payload type for the alerts.
     * @return The payload type for the alerts
     */
    public String getPayloadType()
    {
        return payloadType;
    }

    /**
     * Sets the payload for the alerts.
     * @param payload The payload for the alerts
     */
    public void setPayload(Map<String,Object> payload)
    {
        this.payload = payload;
    }

    /**
     * Adds a field to the payload for the alerts.
     * @param key The key of the payload field
     * @param value The value of the payload field
     */
    public void addPayload(String key, Object value)
    {
        this.payload.put(key, value);
    }

    /**
     * Returns the payload for the alerts.
     * @return The payload for the alerts
     */
    public Map<String,Object> getPayload()
    {
        return payload;
    }

    /**
     * Sets the headers for the alerts.
     * @param headers The headers for the alerts
     */
    public void setHeaders(Map<String,Object> headers)
    {
        this.headers = headers;
    }

    /**
     * Adds a header to the alerts.
     * @param key The key of the header field
     * @param value The value of the header field
     */
    public void addHeader(String key, Object value)
    {
        this.headers.put(key, value);
    }

    /**
     * Returns the headers for the alerts.
     * @return The headers for the alerts
     */
    public Map<String,Object> getHeaders()
    {
        return headers;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "WebhookConfiguration ["+super.toString()
            +", baseUrl="+baseUrl
            +", authUsername="+authUsername
            +", authPassword="+authPassword
            +", payloadType="+payloadType
            +", payload="+payload
            +", headers="+headers
            +"]";
    }
}