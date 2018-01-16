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

package com.opsmatters.newrelic.api.model.applications;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a set of New Relic application instance links.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class ApplicationInstanceLinks
{
    private Long application;

    @SerializedName("application_host")
    private Long applicationHost;

    private Long server;
  
    /**
     * Default constructor.
     */
    public ApplicationInstanceLinks()
    {
    }

    /**
     * Sets the application of the application instance.
     * @param application The application of the application instance
     */
    public void setApplication(long application)
    {
        this.application = application;
    }

    /**
     * Returns the application of the application instance.
     * @return The application of the application instance
     */
    public long getApplication()
    {
        return application;
    }

    /**
     * Sets the application host of the application instance.
     * @param applicationHost The application host of the application instance
     */
    public void setApplicationHost(long applicationHost)
    {
        this.applicationHost = applicationHost;
    }

    /**
     * Returns the application host of the application instance.
     * @return The application host of the application instance
     */
    public long getApplicationHost()
    {
        return applicationHost;
    }

    /**
     * Sets the server of the application instance.
     * @param server The server of the application instance
     */
    public void setServer(long server)
    {
        this.server = server;
    }

    /**
     * Returns the server of the application instance.
     * @return The server of the application instance
     */
    public long getServer()
    {
        return server;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "ApplicationInstanceLinks [application="+application
            +", applicationHost="+applicationHost
            +", server="+server
            +"]";
    }
}