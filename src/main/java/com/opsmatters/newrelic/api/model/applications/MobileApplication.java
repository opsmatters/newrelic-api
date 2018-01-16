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

import java.util.Date;
import com.google.gson.annotations.SerializedName;
import com.opsmatters.newrelic.api.model.Entity;
import com.opsmatters.newrelic.api.model.EntityType;

/**
 * Represents a New Relic mobile application.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class MobileApplication extends Entity
{
    /**
     * The type of the entity.
     */
    public static final EntityType TYPE = EntityType.MOBILE_APPLICATION;

    @SerializedName("health_status")
    private String healthStatus;

    private Boolean reporting;

    @SerializedName("last_reported_at")
    private Date lastReportedAt;

    @SerializedName("mobile_summary")
    private MobileSummary mobileSummary;

    @SerializedName("crash_summary")
    private CrashSummary crashSummary;

    /**
     * Default constructor.
     */
    public MobileApplication()
    {
        super(TYPE.value());
    }
    
    /**
     * Sets the health status of the application.
     * @param healthStatus The health status of the application
     */
    public void setHealthStatus(String healthStatus)
    {
        this.healthStatus = healthStatus;
    }

    /**
     * Returns the health status of the application.
     * @return The health status of the application
     */
    public String getHealthStatus()
    {
        return healthStatus;
    }

    /**
     * Set to <CODE>true</CODE> if the application is reporting.
     * @param reporting <CODE>true</CODE> if the application is reporting
     */
    public void setReporting(boolean reporting)
    {
        this.reporting = reporting;
    }

    /**
     * Returns <CODE>true</CODE> if the application is reporting.
     * @return <CODE>true</CODE> if the application is reporting
     */
    public boolean getReporting()
    {
        return reporting;
    }

    /**
     * Returns the date the application last reported.
     * @return The date the application last reported
     */
    public Date getLastReportedAt()
    {
        return lastReportedAt;
    }

    /**
     * Returns the mobile summary of the application.
     * @return The mobile summary of the application
     */
    public MobileSummary getMobileSummary()
    {
        return mobileSummary;
    }

    /**
     * Returns the crash summary of the application.
     * @return The crash summary of the application
     */
    public CrashSummary getCrashSummary()
    {
        return crashSummary;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "MobileApplication ["+super.toString()
            +", healthStatus="+healthStatus
            +", reporting="+reporting
            +", lastReportedAt="+lastReportedAt
            +", mobileSummary="+mobileSummary
            +", crashSummary="+crashSummary
            +"]";
    }
}