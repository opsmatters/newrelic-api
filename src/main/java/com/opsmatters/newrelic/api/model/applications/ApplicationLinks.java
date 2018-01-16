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
 * Represents a set of New Relic application links.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class ApplicationLinks
{
    private List<Long> servers = new ArrayList<Long>();

    @SerializedName("application_hosts")
    private List<Long> applicationHosts = new ArrayList<Long>();

    @SerializedName("application_instances")
    private List<Long> applicationInstances = new ArrayList<Long>();

    @SerializedName("alert_policy")
    private Long alertPolicy;
    
    /**
     * Default constructor.
     */
    public ApplicationLinks()
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
     * Sets the list of application hosts.
     * @param applicationHosts The list of application hosts
     */
    public void setApplicationHosts(List<Long> applicationHosts)
    {
        this.applicationHosts.clear();
        this.applicationHosts.addAll(applicationHosts);
    }

    /**
     * Returns the list of application hosts.
     * @return The list of application hosts
     */
    public List<Long> getApplicationHosts()
    {
        return applicationHosts;
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
     * Sets the alert policy of the application.
     * @param alertPolicy The alert policy of the application
     */
    public void setAlertPolicy(long alertPolicy)
    {
        this.alertPolicy = alertPolicy;
    }

    /**
     * Returns the alert policy of the application.
     * @return The alert policy of the application
     */
    public long getAlertPolicy()
    {
        return alertPolicy;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "ApplicationLinks [servers="+servers
            +", applicationHosts="+applicationHosts
            +", applicationInstances="+applicationInstances
            +", alertPolicy="+alertPolicy
            +"]";
    }
}