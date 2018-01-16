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
 * Represents a New Relic application settings.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class ApplicationSettings
{
    @SerializedName("app_apdex_threshold")
    private Float appApdexThreshold;

    @SerializedName("end_user_apdex_threshold")
    private Float endUserApdexThreshold;

    @SerializedName("enable_real_user_monitoring")
    private Boolean enableRealUserMonitoring;

    @SerializedName("use_server_side_config")
    private Boolean useServerSideConfig;

    /**
     * Default constructor.
     */
    public ApplicationSettings()
    {
    }

    /**
     * Sets the app apdex threshold of the application.
     * @param appApdexThreshold The app apdex threshold of the application
     */
    public void setAppApdexThreshold(float appApdexThreshold)
    {
        this.appApdexThreshold = appApdexThreshold;
    }
    
    /**
     * Returns the app apdex threshold of the application.
     * @return The app apdex threshold of the application
     */
    public float getAppApdexThreshold()
    {
        return appApdexThreshold;
    }

    /**
     * Sets the end user apdex threshold of the application.
     * @param endUserApdexThreshold The end user apdex threshold of the application
     */
    public void setEndUserApdexThreshold(float endUserApdexThreshold)
    {
        this.endUserApdexThreshold = endUserApdexThreshold;
    }

    /**
     * Returns the end user apdex threshold of the application.
     * @return The end user apdex threshold of the application
     */
    public float getEndUserApdexThreshold()
    {
        return endUserApdexThreshold;
    }

    /**
     * Set to <CODE>true</CODE> if real user monitoring is enabled for the application.
     * @param enableRealUserMonitoring <CODE>true</CODE> if real user monitoring is enabled for the application
     */
    public void setEnableRealUserMonitoring(boolean enableRealUserMonitoring)
    {
        this.enableRealUserMonitoring = enableRealUserMonitoring;
    }

    /**
     * Returns <CODE>true</CODE> if real user monitoring is enabled for the application.
     * @return <CODE>true</CODE> if real user monitoring is enabled for the application
     */
    public boolean getEnableRealUserMonitoring()
    {
        return enableRealUserMonitoring;
    }

    /**
     * Set to <CODE>true</CODE> if the application uses server side config.
     * @param useServerSideConfig <CODE>true</CODE> if the application uses server side config
     */
    public void setUseServerSideConfig(boolean useServerSideConfig)
    {
        this.useServerSideConfig = useServerSideConfig;
    }

    /**
     * Returns <CODE>true</CODE> if the application uses server side config.
     * @return <CODE>true</CODE> if the application uses server side config
     */
    public boolean getUseServerSideConfig()
    {
        return useServerSideConfig;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "ApplicationSettings [appApdexThreshold="+appApdexThreshold
            +", endUserApdexThreshold="+endUserApdexThreshold
            +", enableRealUserMonitoring="+enableRealUserMonitoring
            +", useServerSideConfig="+useServerSideConfig
            +"]";
    }
}