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

import java.util.List;
import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;

/**
 * Represents a set of New Relic application host links.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class ApplicationHostLinks
{
    private Long application;

    @SerializedName("application_instances")
    private List<Long> applicationInstances = new ArrayList<Long>();
   
    /**
     * Default constructor.
     */
    public ApplicationHostLinks()
    {
    }

    /**
     * Sets the application of the application host.
     * @param application The application of the application host
     */
    public void setApplication(Long application)
    {
        this.application = application;
    }

    /**
     * Returns the application of the application host.
     * @return The application of the application host
     */
    public Long getApplication()
    {
        return application;
    }

    /**
     * Sets the list of application instances.
     * @param applicationInstances The list of application instances
     */
    public void setApplicationInstances(List<Long> applicationInstances)
    {
        this.applicationInstances.clear();
        this.applicationInstances.addAll(applicationInstances);
    }

    /**
     * Returns the list of application instances.
     * @return The list of application instances
     */
    public List<Long> getApplicationInstances()
    {
        return applicationInstances;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "ApplicationHostLinks [application="+application
            +", applicationInstances="+applicationInstances
            +"]";
    }
}