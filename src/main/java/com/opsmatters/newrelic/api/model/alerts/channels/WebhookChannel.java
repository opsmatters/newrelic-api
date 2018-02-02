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

package com.opsmatters.newrelic.api.model.alerts.channels;

import java.util.List;
import java.util.Map;

/**
 * Represents a New Relic Webhook alert channel.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class WebhookChannel extends AlertChannel<WebhookConfiguration>
{
    /**
     * Default constructor.
     */
    public WebhookChannel()
    {
        this(new WebhookConfiguration());
    }

    /**
     * Constructor that takes a configuration.
     * @param configuration The alert channel configuration
     */
    public WebhookChannel(WebhookConfiguration configuration)
    {
        setConfiguration(configuration);
        setType(configuration.getType());
    }
   
    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "WebhookChannel ["+super.toString()+"]";
    }

    /**
     * Returns a builder for the Webhook channel.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make Webhook channel construction easier.
     */
    public static class Builder extends AlertChannel.Builder<WebhookChannel, Builder>
    {
        private WebhookConfiguration configuration = new WebhookConfiguration();
        private WebhookChannel channel = new WebhookChannel(configuration);

        /**
         * Default constructor.
         */
        public Builder()
        {
            channel(channel);
        }

        /**
         * Sets the base URL of the Webhook alerts.
         * @param base_url The base URL of the alerts
         * @return This object
         */
        public Builder baseUrl(String base_url)
        {
            configuration.setBaseUrl(base_url);
            return this;
        }

        /**
         * Sets the username of the Webhook alerts.
         * @param username The username of the alerts
         * @return This object
         */
        public Builder authUsername(String username)
        {
            configuration.setAuthUsername(username);
            return this;
        }

        /**
         * Sets the password of the Webhook alerts.
         * @param password The password of the alerts
         * @return This object
         */
        public Builder authPassword(String password)
        {
            configuration.setAuthPassword(password);
            return this;
        }

        /**
         * Sets the payload_type of the Webhook alerts.
         * @param payload_type The payload type of the alerts
         * @return This object
         */
        public Builder payloadType(String payload_type)
        {
            configuration.setPayloadType(payload_type);
            return this;
        }

        /**
         * Sets the payload of the Webhook alerts.
         * @param payload The payload for the alerts
         * @return This object
         */
        public Builder payload(Map<String,Object> payload)
        {
            configuration.setPayload(payload);
            return this;
        }

        /**
         * Adds a field to the payload of the Webhook alerts.
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
         * Sets the headers of the Webhook alerts.
         * @param headers The headers for the alerts
         * @return This object
         */
        public Builder headers(Map<String,Object> headers)
        {
            configuration.setHeaders(headers);
            return this;
        }

        /**
         * Adds a header to the Webhook alerts.
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
         * Returns this object.
         * @return This object
         */
        @Override
        protected Builder self()
        {
            return this;
        }

        /**
         * Returns the configured Webhook channel instance
         * @return The Webhook channel instance
         */
        public WebhookChannel build()
        {
            return channel;
        }
    }
}