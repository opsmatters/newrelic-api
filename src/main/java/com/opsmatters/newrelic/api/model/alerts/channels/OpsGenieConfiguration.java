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

import com.google.gson.annotations.SerializedName;

/**
 * Represents a New Relic OpsGenie channel configuration.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class OpsGenieConfiguration extends ChannelConfiguration
{
    // The field names
    public static final String API_KEY = "api_key";
    public static final String TEAMS = "teams";
    public static final String TAGS = "tags";
    public static final String RECIPIENTS = "recipients";

    /**
     * The type of the channel configuration.
     */
    public static final ChannelType TYPE = ChannelType.OPSGENIE;

    @SerializedName("api_key")
    private String apiKey;

    private String teams;
    private String tags;
    private String recipients;

    /**
     * Default constructor.
     */
    public OpsGenieConfiguration()
    {
        super(TYPE.value());
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
     * @param apiKey The API key for the alerts
     */
    public void setApiKey(String apiKey)
    {
        this.apiKey = apiKey;
    }

    /**
     * Returns the API key for the alerts.
     * @return The API key for the alerts
     */
    public String getApiKey()
    {
        return apiKey;
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
            +", apiKey="+apiKey
            +", teams="+teams
            +", tags="+tags
            +"]";
    }
}