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
 * Represents a New Relic application.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class Application extends Entity
{
    /**
     * The type of the entity.
     */
    public static final EntityType TYPE = EntityType.APPLICATION;

    private String language;

    @SerializedName("health_status")
    private String healthStatus;

    private Boolean reporting;

    @SerializedName("last_reported_at")
    private Date lastReportedAt;

    @SerializedName("application_summary")
    private ApplicationSummary applicationSummary;

    @SerializedName("end_user_summary")
    private EndUserSummary endUserSummary;

    private ApplicationSettings settings;

    private ApplicationLinks links;

    /**
     * Default constructor.
     */
    public Application()
    {
        super(TYPE.value());
    }
    
    /**
     * Sets the language of the application.
     * @param language The language of the application
     */
    public void setLanguage(String language)
    {
        this.language = language;
    }

    /**
     * Returns the language of the application.
     * @return The language of the application
     */
    public String getLanguage()
    {
        return language;
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
     * Returns the application summary of the application.
     * @return The application summary of the application
     */
    public ApplicationSummary getApplicationSummary()
    {
        return applicationSummary;
    }

    /**
     * Returns the end user summary of the application.
     * @return The end user summary of the application
     */
    public EndUserSummary getEndUserSummary()
    {
        return endUserSummary;
    }

    /**
     * Sets the settings of the application.
     * @param settings The settings of the application
     */
    public void setSettings(ApplicationSettings settings)
    {
        this.settings = settings;
    }

    /**
     * Returns the settings of the application.
     * @return The settings of the application
     */
    public ApplicationSettings getSettings()
    {
        return settings;
    }

    /**
     * Returns the links of the application.
     * @return The links of the application
     */
    public ApplicationLinks getLinks()
    {
        return links;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "Application ["+super.toString()
            +", language="+language
            +", healthStatus="+healthStatus
            +", reporting="+reporting
            +", lastReportedAt="+lastReportedAt
            +", applicationSummary="+applicationSummary
            +", endUserSummary="+endUserSummary
            +", settings="+settings
            +", links="+links
            +"]";
    }

    /**
     * Returns a builder for the application.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make application construction easier.
     */
    public static class Builder extends Entity.Builder<Application, Builder>
    {
        private Application application = new Application();
        private ApplicationSettings settings = new ApplicationSettings();

        /**
         * Default constructor.
         */
        public Builder()
        {
            entity(application);
            application.setSettings(settings);
        }

        /**
         * Sets the app apdex threshold of the application.
         * @param appApdexThreshold The app apdex threshold of the application
         * @return This object
         */
        public Builder appApdexThreshold(float appApdexThreshold)
        {
            settings.setAppApdexThreshold(appApdexThreshold);
            return this;
        }

        /**
         * Sets the end user apdex threshold of the application.
         * @param endUserApdexThreshold The end user apdex threshold of the application
         * @return This object
         */
        public Builder endUserApdexThreshold(float endUserApdexThreshold)
        {
            settings.setEndUserApdexThreshold(endUserApdexThreshold);
            return this;
        }

        /**
         * Set to <CODE>true</CODE> if real user monitoring is enabled for the application.
         * @param enableRealUserMonitoring <CODE>true</CODE> if real user monitoring is enabled for the application
         * @return This object
         */
        public Builder enableRealUserMonitoring(boolean enableRealUserMonitoring)
        {
            settings.setEnableRealUserMonitoring(enableRealUserMonitoring);
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
         * Returns the configured application instance
         * @return The application instance
         */
        @Override
        public Application build()
        {
            return application;
        }
    }
}