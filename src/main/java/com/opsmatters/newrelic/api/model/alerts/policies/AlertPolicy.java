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

package com.opsmatters.newrelic.api.model.alerts.policies;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import com.opsmatters.newrelic.api.model.NamedIdResource;
import com.opsmatters.newrelic.api.model.alerts.IncidentPreference;

/**
 * Represents a New Relic alert policy.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class AlertPolicy extends NamedIdResource
{
    // The field names
    public static final String NAME = "name";
    public static final String INCIDENT_PREFERENCE = "incident_preference";

    @SerializedName("incident_preference")
    private String incidentPreference;

    @SerializedName("created_at")
    private Long createdAt;

    @SerializedName("updated_at")
    private Long updatedAt;

    // Added to facilitate adding channels to alert policies
    private transient AlertPolicyChannel channels;

    /**
     * Default constructor.
     */
    public AlertPolicy()
    {
    }
   
    /**
     * Sets the incident preference of the policy.
     * @param incidentPreference The incident preference of the policy
     */
    public void setIncidentPreference(String incidentPreference)
    {
        this.incidentPreference = incidentPreference;
    }

    /**
     * Sets the incident preference of the policy.
     * @param incidentPreference The incident preference of the policy
     */
    public void setIncidentPreference(IncidentPreference incidentPreference)
    {
        setIncidentPreference(incidentPreference.name());
    }

    /**
     * Returns the incident preference of the policy.
     * @return The incident preference of the policy
     */
    public String getIncidentPreference()
    {
        return incidentPreference;
    }

    /**
     * Returns the date the policy was created.
     * @return The date the policy was created
     */
    public Long getCreatedAt()
    {
        return createdAt;
    }

    /**
     * Returns the date the policy was last updated.
     * @return The date the policy was last updated
     */
    public Long getUpdatedAt()
    {
        return updatedAt;
    }

    /**
     * Sets the channels for the policy.
     * @param channels The channels for the policy
     */
    public void setChannels(AlertPolicyChannel channels)
    {
        this.channels = channels;
    }

    /**
     * Sets the channels for the policy.
     * @param channelIds The channels for the policy
     */
    public void setChannelIds(List<Long> channelIds)
    {
        setChannels(AlertPolicyChannel.builder().channelIds(channelIds).build());
    }

    /**
     * Returns the channels for the policy.
     * @return The channels for the policy
     */
    public AlertPolicyChannel getChannels()
    {
        return channels;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "AlertPolicy ["+super.toString()
            +", incidentPreference="+incidentPreference
            +", createdAt="+createdAt
            +", updatedAt="+updatedAt
            +", channels="+channels
            +"]";
    }

    /**
     * Returns a builder for the alert policy.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make alert policy construction easier.
     */
    public static class Builder
    {
        private AlertPolicy policy = new AlertPolicy();

        /**
         * Sets the id of the alert policy.
         * @param id The id of the alert policy
         * @return This object
         */
        public Builder id(long id)
        {
            policy.setId(id);
            return this;
        }

        /**
         * Sets the name of the alert policy.
         * @param name The name of the alert policy
         * @return This object
         */
        public Builder name(String name)
        {
            policy.setName(name);
            return this;
        }

        /**
         * Sets the incident preference of the alert policy.
         * @param incidentPreference The incident preference of the alert policy
         * @return This object
         */
        public Builder incidentPreference(String incidentPreference)
        {
            policy.setIncidentPreference(incidentPreference);
            return this;
        }

        /**
         * Sets the incident preference of the alert policy to "PER_POLICY".
         * @return This object
         */
        public Builder perPolicyIncidentPreference()
        {
            policy.setIncidentPreference(IncidentPreference.PER_POLICY);
            return this;
        }

        /**
         * Sets the incident preference of the alert policy to "PER_CONDITION".
         * @return This object
         */
        public Builder perConditionIncidentPreference()
        {
            policy.setIncidentPreference(IncidentPreference.PER_CONDITION);
            return this;
        }

        /**
         * Sets the incident preference of the alert policy to "PER_CONDITION_AND_TARGET".
         * @return This object
         */
        public Builder perConditionAndTargetIncidentPreference()
        {
            policy.setIncidentPreference(IncidentPreference.PER_CONDITION_AND_TARGET);
            return this;
        }

        /**
         * Returns the configured alert policy instance
         * @return The alert policy instance
         */
        public AlertPolicy build()
        {
            return policy;
        }
    }
}