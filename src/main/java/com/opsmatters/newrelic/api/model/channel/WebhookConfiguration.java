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

package com.opsmatters.newrelic.api.model.channel;

import java.util.Map;
import com.google.common.collect.Maps;

/**
 * Represents a New Relic Webhook configuration.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class WebhookConfiguration extends ChannelConfiguration
{
    private String base_url;
    private String auth_username;
    private String auth_password;
    private String payload_type;
    private Map<String,Object> payload = Maps.newHashMap();
    private Map<String,Object> headers = Maps.newHashMap();

    /**
     * Default constructor.
     */
    public WebhookConfiguration()
    {
        super("webhook");
    }
   
    /**
     * Constructor that takes a base URL.
     * @param base_url The base URL
     */
    public WebhookConfiguration(String base_url)
    {
        this();
        setBaseUrl(base_url);
    }

    /**
     * Sets the base URL for the alerts.
     * @param base_url The base URL for the alerts
     */
    public void setBaseUrl(String base_url)
    {
        this.base_url = base_url;
    }

    /**
     * Returns the base URL for the alerts.
     * @return The base URL for the alerts
     */
    public String getBaseUrl()
    {
        return base_url;
    }

    /**
     * Sets the username for the alerts.
     * @param username The username for the alerts
     */
    public void setAuthUsername(String username)
    {
        this.auth_username = username;
    }

    /**
     * Returns the username for the alerts.
     * @return The username for the alerts
     */
    public String getAuthUsername()
    {
        return auth_username;
    }

    /**
     * Sets the password for the alerts.
     * @param password The password for the alerts
     */
    public void setAuthPassword(String password)
    {
        this.auth_password = password;
    }

    /**
     * Returns the password for the alerts.
     * @return The password for the alerts
     */
    public String getAuthPassword()
    {
        return auth_password;
    }

    /**
     * Sets the payload type for the alerts.
     * @param payload_type The payload type for the alerts
     */
    public void setPayloadType(String payload_type)
    {
        this.payload_type = payload_type;
    }

    /**
     * Returns the payload type for the alerts.
     * @return The payload type for the alerts
     */
    public String getPayloadType()
    {
        return payload_type;
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
            +", base_url="+base_url
            +", auth_username="+auth_username
            +", auth_password="+auth_password
            +", payload_type="+payload_type
            +", payload="+payload
            +", headers="+headers
            +"]";
    }

    /**
     * Returns a builder for the Webhook configuration.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make Webhook configuration construction easier.
     */
    public static class Builder
    {
        private WebhookConfiguration configuration = new WebhookConfiguration();

        /**
         * Sets the base URL of the Webhook configuration.
         * @param base_url The base URL of the alerts
         * @return This object
         */
        public Builder baseUrl(String base_url)
        {
            configuration.setBaseUrl(base_url);
            return this;
        }

        /**
         * Sets the username of the Webhook configuration.
         * @param username The username of the alerts
         * @return This object
         */
        public Builder authUsername(String username)
        {
            configuration.setAuthUsername(username);
            return this;
        }

        /**
         * Sets the password of the Webhook configuration.
         * @param password The password of the alerts
         * @return This object
         */
        public Builder authPassword(String password)
        {
            configuration.setAuthPassword(password);
            return this;
        }

        /**
         * Sets the payload_type of the Webhook configuration.
         * @param payload_type The payload type of the alerts
         * @return This object
         */
        public Builder payloadType(String payload_type)
        {
            configuration.setPayloadType(payload_type);
            return this;
        }

        /**
         * Sets the payload of the Webhook configuration.
         * @param payload The payload for the alerts
         * @return This object
         */
        public Builder payload(Map<String,Object> payload)
        {
            configuration.setPayload(payload);
            return this;
        }

        /**
         * Adds a field to the payload of the Webhook configuration.
         * @param key The key of the payload field
         * @param value The value of the payload field
         * @return This object
         */
        public Builder addPayload(String key, Object value)
        {
            configuration.addPayload(key, value);
            return this;
        }

        /**
         * Sets the headers of the Webhook configuration.
         * @param headers The headers for the alerts
         * @return This object
         */
        public Builder headers(Map<String,Object> headers)
        {
            configuration.setHeaders(headers);
            return this;
        }

        /**
         * Adds a header to the Webhook configuration.
         * @param key The key of the header field
         * @param value The value of the header field
         * @return This object
         */
        public Builder addHeader(String key, Object value)
        {
            configuration.addHeader(key, value);
            return this;
        }

        /**
         * Returns the configured Webhook configuration instance
         * @return The Webhook configuration instance
         */
        public WebhookConfiguration build()
        {
            return configuration;
        }
    }
}