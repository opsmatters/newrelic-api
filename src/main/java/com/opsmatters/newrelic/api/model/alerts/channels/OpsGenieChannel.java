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

/**
 * Represents a New Relic OpsGenie alert channel.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class OpsGenieChannel extends AlertChannel
{
    /**
     * Default constructor.
     */
    public OpsGenieChannel()
    {
        this(new OpsGenieConfiguration());
    }

    /**
     * Constructor that takes a configuration.
     * @param configuration The alert channel configuration
     */
    public OpsGenieChannel(OpsGenieConfiguration configuration)
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
        return "OpsGenieChannel ["+super.toString()+"]";
    }

    /**
     * Returns a builder for the OpsGenie channel.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make OpsGenie channel construction easier.
     */
    public static class Builder extends AlertChannel.Builder<OpsGenieChannel, Builder>
    {
        private OpsGenieConfiguration configuration = new OpsGenieConfiguration();
        private OpsGenieChannel channel = new OpsGenieChannel(configuration);

        /**
         * Default constructor.
         */
        public Builder()
        {
            channel(channel);
        }

        /**
         * Sets the recipients of the OpsGenie alerts.
         * @param recipients The recipients of the alerts
         * @return This object
         */
        public Builder recipients(String recipients)
        {
            configuration.setRecipients(recipients);
            return this;
        }

        /**
         * Sets the API key of the OpsGenie alerts.
         * @param api_key The API key of the alerts
         * @return This object
         */
        public Builder apiKey(String api_key)
        {
            configuration.setApiKey(api_key);
            return this;
        }

        /**
         * Sets the teams of the OpsGenie alerts.
         * @param teams The teams of the alerts
         * @return This object
         */
        public Builder teams(String teams)
        {
            configuration.setTeams(teams);
            return this;
        }

        /**
         * Sets the tags of the OpsGenie alerts.
         * @param tags The tags of the alerts
         * @return This object
         */
        public Builder tags(String tags)
        {
            configuration.setTags(tags);
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
         * Returns the configured OpsGenie channel instance
         * @return The OpsGenie channel instance
         */
        public OpsGenieChannel build()
        {
            return channel;
        }
    }
}