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

package com.opsmatters.newrelic.api.model.labels;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a set of New Relic label links.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class LabelLinks
{
    private List<Long> servers = new ArrayList<Long>();

    private List<Long> applications = new ArrayList<Long>();
   
    /**
     * Default constructor.
     */
    public LabelLinks()
    {
    }

    /**
     * Sets the list of servers.
     * @param servers The list of servers
     */
    public void setServers(List<Long> servers)
    {
        this.servers.clear();
        this.servers.addAll(servers);
    }

    /**
     * Returns the list of servers.
     * @return The list of servers
     */
    public List<Long> getServers()
    {
        return servers;
    }

    /**
     * Sets the list of applications.
     * @param applications The list of applications
     */
    public void setApplications(List<Long> applications)
    {
        this.applications.clear();
        this.applications.addAll(applications);
    }

    /**
     * Returns the list of applications.
     * @return The list of applications
     */
    public List<Long> getApplications()
    {
        return applications;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "LabelLinks [servers="+servers
            +", applications="+applications
            +"]";
    }
}