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

package com.opsmatters.newrelic.api.model.servers;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a set of New Relic server links.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class ServerLinks
{
    @SerializedName("alert_policy")
    private Long alertPolicy;
    
    /**
     * Default constructor.
     */
    public ServerLinks()
    {
    }

    /**
     * Sets the alert policy of the server.
     * @param alertPolicy The alert policy of the server
     */
    public void setAlertPolicy(Long alertPolicy)
    {
        this.alertPolicy = alertPolicy;
    }

    /**
     * Returns the alert policy of the server.
     * @return The alert policy of the server
     */
    public Long getAlertPolicy()
    {
        return alertPolicy;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "ServerLinks [alertPolicy="+alertPolicy+"]";
    }
}