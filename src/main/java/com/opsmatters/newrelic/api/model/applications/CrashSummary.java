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
 * Represents a New Relic mobile crash summary.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class CrashSummary
{
    @SerializedName("supports_crash_data")
    private Boolean supportsCrashData;

    @SerializedName("unresolved_crash_count")
    private Integer unresolvedCrashCount;

    @SerializedName("crash_count")
    private Integer crashCount;

    @SerializedName("crash_rate")
    private Float crashRate;

    /**
     * Default constructor.
     */
    public CrashSummary()
    {
    }

    /**
     * Returns <CODE>true</CODE> of the application supports crash data.
     * @return <CODE>true</CODE> of the application supports crash data
     */
    public boolean getSupportsCrashData()
    {
        return supportsCrashData;
    }

    /**
     * Returns the unresolved crash count of the application.
     * @return The unresolved crash count of the application
     */
    public int getUnresolvedCrashCount()
    {
        return unresolvedCrashCount;
    }

    /**
     * Returns the crash count of the application.
     * @return The crash count of the application
     */
    public int getCrashCount()
    {
        return crashCount;
    }

    /**
     * Returns the crash rate of the application.
     * @return The crash rate of the application
     */
    public float getCrashRate()
    {
        return crashRate;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "CrashSummary [supportsCrashData="+supportsCrashData
            +", unresolvedCrashCount="+unresolvedCrashCount
            +", crashCount="+crashCount
            +", crashRate="+crashRate
            +"]";
    }
}