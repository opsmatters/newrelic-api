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

package com.opsmatters.newrelic.api.model.plugins;

/**
 * Represents a New Relic plugin agent data.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class Agent
{
    private String host;

    private Integer pid;

    private String version;

    /**
     * Default constructor.
     */
    public Agent()
    {
    }

    /**
     * Sets the hostname for the agent.
     * @param host The hostname for the agent
     */
    public void setHost(String host)
    {
        this.host = host;
    }

    /**
     * Returns the hostname for the agent.
     * @return The hostname for the agent
     */
    public String getHost()
    {
        return host;
    }

    /**
     * Sets the pid for the agent.
     * @param pid The pid for the agent
     */
    public void setPid(int pid)
    {
        this.pid = pid;
    }

    /**
     * Returns the pid for the agent.
     * @return The pid for the agent
     */
    public int getPid()
    {
        return pid;
    }

    /**
     * Sets the version for the agent.
     * @param version The version for the agent
     */
    public void setVersion(String version)
    {
        this.version = version;
    }

    /**
     * Returns the version for the agent.
     * @return The version for the agent
     */
    public String getVersion()
    {
        return version;
    }
    
    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "Agent [host="+host
            +", pid="+pid
            +", version="+version
            +"]";
    }
}