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
 * Represents a New Relic application instance.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class ApplicationInstance extends Entity
{
    /**
     * The type of the entity.
     */
    public static final EntityType TYPE = EntityType.INSTANCE;

    @SerializedName("application_name")
    private String applicationName;

    private String host;

    private Integer port;

    private String language;

    @SerializedName("health_status")
    private String healthStatus;

    @SerializedName("application_summary")
    private ApplicationSummary applicationSummary;

    private ApplicationInstanceLinks links;

    /**
     * Default constructor.
     */
    public ApplicationInstance()
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
     * Sets the port of the host.
     * @param port The port of the host
     */
    public void setPort(int port)
    {
        this.port = port;
    }

    /**
     * Returns the port of the host.
     * @return The port of the host
     */
    public int getPort()
    {
        return port;
    }
    
    /**
     * Sets the language of the application instance.
     * @param language The language of the application instance
     */
    public void setLanguage(String language)
    {
        this.language = language;
    }

    /**
     * Returns the language of the application instance.
     * @return The language of the application instance
     */
    public String getLanguage()
    {
        return language;
    }

    /**
     * Sets the health status of the application instance.
     * @param healthStatus The health status of the application instance
     */
    public void setHealthStatus(String healthStatus)
    {
        this.healthStatus = healthStatus;
    }

    /**
     * Returns the health status of the application instance.
     * @return The health status of the application instance
     */
    public String getHealthStatus()
    {
        return healthStatus;
    }

    /**
     * Returns the application summary of the application instance.
     * @return The application summary of the application instance
     */
    public ApplicationSummary getApplicationSummary()
    {
        return applicationSummary;
    }

    /**
     * Returns the links of the application instance.
     * @return The links of the application instance
     */
    public ApplicationInstanceLinks getLinks()
    {
        return links;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "ApplicationInstance ["+super.toString()
            +", applicationName="+applicationName
            +", host="+host
            +", port="+port
            +", language="+language
            +", healthStatus="+healthStatus
            +", applicationSummary="+applicationSummary
            +", links="+links
            +"]";
    }
}