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
 * Represents a New Relic key transaction.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class KeyTransaction extends Entity
{
    /**
     * The type of the entity.
     */
    public static final EntityType TYPE = EntityType.KEY_TRANSACTION;

    @SerializedName("transaction_name")
    private String transactionName;

    @SerializedName("health_status")
    private String healthStatus;

    private Boolean reporting;

    @SerializedName("last_reported_at")
    private Date lastReportedAt;

    @SerializedName("application_summary")
    private ApplicationSummary applicationSummary;

    @SerializedName("end_user_summary")
    private EndUserSummary endUserSummary;

    private KeyTransactionLinks links;

    /**
     * Default constructor.
     */
    public KeyTransaction()
    {
        super(TYPE.value());
    }
    
    /**
     * Sets the name of the transaction.
     * @param name The name of the transaction
     */
    public void setTransactionName(String name)
    {
        this.transactionName = name;
    }

    /**
     * Returns the name of the transaction.
     * @return The name of the transaction
     */
    public String getTransactionName()
    {
        return transactionName;
    }

    /**
     * Sets the health status of the transaction.
     * @param healthStatus The health status of the transaction
     */
    public void setHealthStatus(String healthStatus)
    {
        this.healthStatus = healthStatus;
    }

    /**
     * Returns the health status of the transaction.
     * @return The health status of the transaction
     */
    public String getHealthStatus()
    {
        return healthStatus;
    }

    /**
     * Set to <CODE>true</CODE> if the transaction is reporting.
     * @param reporting <CODE>true</CODE> if the transaction is reporting
     */
    public void setReporting(boolean reporting)
    {
        this.reporting = reporting;
    }

    /**
     * Returns <CODE>true</CODE> if the transaction is reporting.
     * @return <CODE>true</CODE> if the transaction is reporting
     */
    public boolean getReporting()
    {
        return reporting;
    }

    /**
     * Returns the date the transaction last reported.
     * @return The date the transaction last reported
     */
    public Date getLastReportedAt()
    {
        return lastReportedAt;
    }

    /**
     * Returns the application summary of the transaction.
     * @return The application summary of the transaction
     */
    public ApplicationSummary getApplicationSummary()
    {
        return applicationSummary;
    }

    /**
     * Returns the end user summary of the transaction.
     * @return The end user summary of the transaction
     */
    public EndUserSummary getEndUserSummary()
    {
        return endUserSummary;
    }

    /**
     * Returns the links of the transaction.
     * @return The links of the transaction
     */
    public KeyTransactionLinks getLinks()
    {
        return links;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "KeyTransaction ["+super.toString()
            +", transactionName="+transactionName
            +", healthStatus="+healthStatus
            +", reporting="+reporting
            +", lastReportedAt="+lastReportedAt
            +", applicationSummary="+applicationSummary
            +", endUserSummary="+endUserSummary
            +", links="+links
            +"]";
    }
}