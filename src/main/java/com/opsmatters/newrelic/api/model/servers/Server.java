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

import java.util.Date;
import com.google.gson.annotations.SerializedName;
import com.opsmatters.newrelic.api.model.Entity;
import com.opsmatters.newrelic.api.model.EntityType;

/**
 * Represents a New Relic server.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class Server extends Entity
{
    /**
     * The type of the entity.
     */
    public static final EntityType TYPE = EntityType.SERVER;

    @SerializedName("account_id")
    private Long accountId;

    private String host;

    @SerializedName("health_status")
    private String healthStatus;

    private Boolean reporting;

    @SerializedName("last_reported_at")
    private Date lastReportedAt;

    private ServerSummary summary;

    private ServerLinks links;

    /**
     * Default constructor.
     */
    public Server()
    {
        super(TYPE.value());
    }

    /**
     * Returns the id of the account for the server.
     * @return The id of the account for the server
     */
    public Long getAccountId()
    {
        return accountId;
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
     * Sets the health status of the server.
     * @param healthStatus The health status of the server
     */
    public void setHealthStatus(String healthStatus)
    {
        this.healthStatus = healthStatus;
    }

    /**
     * Returns the health status of the server.
     * @return The health status of the server
     */
    public String getHealthStatus()
    {
        return healthStatus;
    }

    /**
     * Set to <CODE>true</CODE> if the server is reporting.
     * @param reporting <CODE>true</CODE> if the server is reporting
     */
    public void setReporting(Boolean reporting)
    {
        this.reporting = reporting;
    }

    /**
     * Returns <CODE>true</CODE> if the server is reporting.
     * @return <CODE>true</CODE> if the server is reporting
     */
    public Boolean getReporting()
    {
        return reporting;
    }

    /**
     * Returns the date the server last reported.
     * @return The date the server last reported
     */
    public Date getLastReportedAt()
    {
        return lastReportedAt;
    }

    /**
     * Returns the summary of the server.
     * @return The summary of the server
     */
    public ServerSummary getSummary()
    {
        return summary;
    }

    /**
     * Returns the links of the server.
     * @return The links of the server
     */
    public ServerLinks getLinks()
    {
        return links;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "Server ["+super.toString()
            +", accountId="+accountId
            +", host="+host
            +", healthStatus="+healthStatus
            +", reporting="+reporting
            +", lastReportedAt="+lastReportedAt
            +", summary="+summary
            +", links="+links
            +"]";
    }

    /**
     * Returns a builder for the server.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make server construction easier.
     */
    public static class Builder extends Entity.Builder<Server, Builder>
    {
        private Server server = new Server();

        /**
         * Default constructor.
         */
        public Builder()
        {
            entity(server);
        }

        /**
         * Sets the name of the server.
         * @param name The name of the server
         * @return This object
         */
        public Builder name(String name)
        {
            server.setName(name);
            return this;
        }

        /**
         * Returns this object.
         * @return This object
         */
        @Override
        protected Builder self()
        {
            return this;
        }

        /**
         * Returns the configured server instance
         * @return The server instance
         */
        @Override
        public Server build()
        {
            return server;
        }
    }
}