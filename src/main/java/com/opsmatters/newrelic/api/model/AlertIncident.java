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

import com.google.gson.annotations.SerializedName;

/**
 * Represents a New Relic alert incident.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class AlertIncident extends NamedResource
{
    @SerializedName("incident_preference")
    private String incidentPreference;

    @SerializedName("opened_at")
    private Long openedAt;

    @SerializedName("closed_at")
    private Long closedAt;

    private IncidentLinks links;

    /**
     * Default constructor.
     */
    public AlertIncident()
    {
    }
  
    /**
     * Sets the incident preference of the incident.
     * @param incidentPreference The incident preference of the incident
     */
    public void setIncidentPreference(String incidentPreference)
    {
        this.incidentPreference = incidentPreference;
    }

    /**
     * Sets the incident preference of the incident.
     * @param incidentPreference The incident preference of the incident
     */
    public void setIncidentPreference(IncidentPreference incidentPreference)
    {
        setIncidentPreference(incidentPreference.name());
    }

    /**
     * Returns the incident preference of the incident.
     * @return The incident preference of the incident
     */
    public String getIncidentPreference()
    {
        return incidentPreference;
    }

    /**
     * Returns the date the incident was opened.
     * @return The date the incident was opened
     */
    public long getOpenedAt()
    {
        return openedAt;
    }

    /**
     * Returns the date the incident was closed.
     * @return The date the incident was closed
     */
    public long getClosedAt()
    {
        return closedAt;
    }

    /**
     * Returns the links of the incident.
     * @return The links of the incident
     */
    public IncidentLinks getLinks()
    {
        return links;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "AlertIncident ["+super.toString()
            +", incidentPreference="+incidentPreference
            +", openedAt="+openedAt
            +", closedAt="+closedAt
            +", links="+links
            +"]";
    }
}