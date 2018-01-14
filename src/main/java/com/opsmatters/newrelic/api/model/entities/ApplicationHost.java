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

package com.opsmatters.newrelic.api.model.entities;

import java.util.Date;
import com.google.gson.annotations.SerializedName;

/**
 * Represents a New Relic application host.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class ApplicationHost extends Entity
{
    /**
     * The type of the entity.
     */
    public static final EntityType TYPE = EntityType.HOST;

    @SerializedName("application_name")
    private String applicationName;

    private String host;

    private String language;

    @SerializedName("health_status")
    private String healthStatus;

    @SerializedName("application_summary")
    private ApplicationSummary applicationSummary;

    private ApplicationHostLinks links;

    /**
     * Default constructor.
     */
    public ApplicationHost()
    {
        super(TYPE.value());
    }

    /**
     * Sets the name of the application.
     * @param name The name of the application
     */
    public void setApplicationName(String name)
    {
        this.applicationName = name;
    }

    /**
     * Returns the name of the application.
     * @return The name of the application
     */
    public String getApplicationName()
    {
        return applicationName;
    }

    /**
     * Sets the name of the host.
     * @param host The name of the host
     */
    public void setHost(String host)
    {
        this.host = host;
    }

    /**
     * Returns the name of the host.
     * @return The name of the host
     */
    public String getHost()
    {
        return host;
    }
    
    /**
     * Sets the language of the application host.
     * @param language The language of the application host
     */
    public void setLanguage(String language)
    {
        this.language = language;
    }

    /**
     * Returns the language of the application host.
     * @return The language of the application host
     */
    public String getLanguage()
    {
        return language;
    }

    /**
     * Sets the health status of the application host.
     * @param healthStatus The health status of the application host
     */
    public void setHealthStatus(String healthStatus)
    {
        this.healthStatus = healthStatus;
    }

    /**
     * Returns the health status of the application host.
     * @return The health status of the application host
     */
    public String getHealthStatus()
    {
        return healthStatus;
    }

    /**
     * Returns the application summary of the application host.
     * @return The application summary of the application host
     */
    public ApplicationSummary getApplicationSummary()
    {
        return applicationSummary;
    }

    /**
     * Returns the links of the application host.
     * @return The links of the application host
     */
    public ApplicationHostLinks getLinks()
    {
        return links;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "ApplicationHost ["+super.toString()
            +", applicationName="+applicationName
            +", host="+host
            +", language="+language
            +", healthStatus="+healthStatus
            +", applicationSummary="+applicationSummary
            +", links="+links
            +"]";
    }
}