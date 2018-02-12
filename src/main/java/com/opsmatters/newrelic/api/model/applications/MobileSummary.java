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
 * Represents a New Relic mobile application summary.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class MobileSummary
{
    @SerializedName("active_users")
    private Integer activeUsers;

    @SerializedName("launch_count")
    private Integer launchCount;

    private Float throughput;

    @SerializedName("response_time")
    private Float responseTime;

    @SerializedName("calls_per_session")
    private Float callsPerSession;

    @SerializedName("interaction_time")
    private Float interactionTime;

    @SerializedName("failed_call_rate")
    private Float failedCallRate;

    @SerializedName("remote_error_rate")
    private Float remoteErrorRate;

    /**
     * Default constructor.
     */
    public MobileSummary()
    {
    }

    /**
     * Returns the active users of the application.
     * @return The active users of the application
     */
    public Integer getActiveUsers()
    {
        return activeUsers;
    }

    /**
     * Returns the launch count of the application.
     * @return The launch count of the application
     */
    public Integer getLaunchCount()
    {
        return launchCount;
    }
    
    /**
     * Returns the response time of the application.
     * @return The response time of the application
     */
    public Float getResponseTime()
    {
        return responseTime;
    }

    /**
     * Returns the throughput of the application.
     * @return The throughput of the application
     */
    public Float getThroughput()
    {
        return throughput;
    }

    /**
     * Returns the calls per session of the application.
     * @return The calls per session of the application
     */
    public Float getCallsPerSession()
    {
        return callsPerSession;
    }

    /**
     * Returns the interaction time of the application.
     * @return The interaction time of the application
     */
    public Float getInteractionTime()
    {
        return interactionTime;
    }

    /**
     * Returns the failed call rate of the application.
     * @return The failed call rate of the application
     */
    public Float getFailedCallRate()
    {
        return failedCallRate;
    }

    /**
     * Returns the remote error rate of the application.
     * @return The remote error rate of the application
     */
    public Float getRemoteErrorRate()
    {
        return remoteErrorRate;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "MobileSummary [responseTime="+responseTime
            +", activeUsers="+activeUsers
            +", launchCount="+launchCount
            +", throughput="+throughput
            +", callsPerSession="+callsPerSession
            +", interactionTime="+interactionTime
            +", failedCallRate="+failedCallRate
            +", remoteErrorRate="+remoteErrorRate
            +"]";
    }
}