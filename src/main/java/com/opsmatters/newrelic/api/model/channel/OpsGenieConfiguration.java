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

/**
 * Represents a New Relic OpsGenie channel configuration.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class OpsGenieConfiguration extends ChannelConfiguration
{
    private String api_key;
    private String teams;
    private String tags;
    private String recipients;

    /**
     * Default constructor.
     */
    public OpsGenieConfiguration()
    {
        super("opsgenie");
    }
   
    /**
     * Constructor that takes a list of recipients.
     * @param recipients The list of recipients
     */
    public OpsGenieConfiguration(String recipients)
    {
        this();
        setRecipients(recipients);
    }

    /**
     * Sets the recipients of the alerts.
     * @param recipients The recipients of the alerts
     */
    public void setRecipients(String recipients)
    {
        this.recipients = recipients;
    }

    /**
     * Returns the recipients of the alerts.
     * @return The recipients of the alerts
     */
    public String getRecipients()
    {
        return recipients;
    }

    /**
     * Sets the API key for the alerts.
     * @param api_key The API key for the alerts
     */
    public void setApiKey(String api_key)
    {
        this.api_key = api_key;
    }

    /**
     * Returns the API key for the alerts.
     * @return The API key for the alerts
     */
    public String getApiKey()
    {
        return api_key;
    }

    /**
     * Sets the teams for the alerts.
     * @param teams The teams for the alerts
     */
    public void setTeams(String teams)
    {
        this.teams = teams;
    }

    /**
     * Returns the teams for the alerts.
     * @return The teams for the alerts
     */
    public String getTeams()
    {
        return teams;
    }

    /**
     * Sets the tags for the alerts.
     * @param tags The tags for the alerts
     */
    public void setTags(String tags)
    {
        this.tags = tags;
    }

    /**
     * Returns the tags for the alerts.
     * @return The tags for the alerts
     */
    public String getTags()
    {
        return tags;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "OpsGenieConfiguration ["+super.toString()
            +", recipients="+recipients
            +", api_key="+api_key
            +", teams="+teams
            +", tags="+tags
            +"]";
    }

    /**
     * Returns a builder for the OpsGenie configuration.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make OpsGenie configuration construction easier.
     */
    public static class Builder
    {
        private OpsGenieConfiguration configuration = new OpsGenieConfiguration();

        /**
         * Sets the recipients of the OpsGenie configuration.
         * @param recipients The recipients of the alerts
         * @return This object
         */
        public Builder recipients(String recipients)
        {
            configuration.setRecipients(recipients);
            return this;
        }

        /**
         * Sets the API key of the OpsGenie configuration.
         * @param api_key The API key of the alerts
         * @return This object
         */
        public Builder apiKey(String api_key)
        {
            configuration.setApiKey(api_key);
            return this;
        }

        /**
         * Sets the teams of the OpsGenie configuration.
         * @param teams The teams of the alerts
         * @return This object
         */
        public Builder teams(String teams)
        {
            configuration.setTeams(teams);
            return this;
        }

        /**
         * Sets the tags of the OpsGenie configuration.
         * @param tags The tags of the alerts
         * @return This object
         */
        public Builder tags(String tags)
        {
            configuration.setTags(tags);
            return this;
        }

        /**
         * Returns the configured OpsGenie configuration instance
         * @return The OpsGenie configuration instance
         */
        public OpsGenieConfiguration build()
        {
            return configuration;
        }
    }
}