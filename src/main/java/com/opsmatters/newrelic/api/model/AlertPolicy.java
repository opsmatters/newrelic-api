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

package com.opsmatters.newrelic.api.model;

/**
 * Represents a New Relic alert policy.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class AlertPolicy extends BaseObject
{
    private String incident_preference;
    private Long created_at;
    private Long updated_at;

    /**
     * Default constructor.
     */
    public AlertPolicy()
    {
    }
   
    /**
     * Constructor that takes a name.
     * @param name The name of the policy
     */
    public AlertPolicy(String name)
    {
        setName(name);
    }

    /**
     * Sets the incident preference of the policy.
     * @param incident_preference The incident preference of the policy
     */
    public void setIncidentPreference(String incident_preference)
    {
        this.incident_preference = incident_preference;
    }

    /**
     * Returns the incident preference of the policy.
     * @return The incident preference of the policy
     */
    public String getIncidentPreference()
    {
        return incident_preference;
    }

    /**
     * Returns the date the policy was created.
     * @return The date the policy was created
     */
    public long getCreatedAt()
    {
        return created_at;
    }

    /**
     * Returns the date the policy was last updated.
     * @return The date the policy was last updated
     */
    public long getUpdatedAt()
    {
        return updated_at;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "AlertPolicy ["+super.toString()
            +", incident_preference="+incident_preference
            +", created_at="+created_at
            +", updated_at="+updated_at
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
         * Sets the incident_preference of the alert policy.
         * @param incident_preference The incident_preference of the alert policy
         * @return This object
         */
        public Builder incidentPreference(String incident_preference)
        {
            policy.setIncidentPreference(incident_preference);
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