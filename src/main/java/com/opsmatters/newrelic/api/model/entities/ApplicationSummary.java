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

import com.google.gson.annotations.SerializedName;

/**
 * Represents a New Relic application summary.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class ApplicationSummary
{
    @SerializedName("response_time")
    private Float responseTime;

    private Float throughput;

    @SerializedName("error_rate")
    private Float errorRate;

    @SerializedName("apdex_target")
    private Float apdexTarget;

    @SerializedName("apdex_score")
    private Float apdexScore;

    @SerializedName("host_count")
    private Integer hostCount;

    @SerializedName("instance_count")
    private Integer instanceCount;

    @SerializedName("concurrent_instance_count")
    private Integer concurrentInstanceCount;

    /**
     * Default constructor.
     */
    public ApplicationSummary()
    {
    }
    
    /**
     * Returns the response time of the application.
     * @return The responseTime of the application
     */
    public float getResponseTime()
    {
        return responseTime;
    }

    /**
     * Returns the throughput of the application.
     * @return The throughput of the application
     */
    public float getThroughput()
    {
        return throughput;
    }

    /**
     * Returns the error rate of the application.
     * @return The error rate of the application
     */
    public float getErrorRate()
    {
        return errorRate;
    }

    /**
     * Returns the apdex target of the application.
     * @return The apdex target of the application
     */
    public float getApdexTarget()
    {
        return apdexTarget;
    }

    /**
     * Returns the apdex score of the application.
     * @return The apdex score of the application
     */
    public float getApdexScore()
    {
        return apdexScore;
    }

    /**
     * Returns the host count of the application.
     * @return The host count of the application
     */
    public int getHostCount()
    {
        return hostCount;
    }

    /**
     * Returns the instance count of the application.
     * @return The instance count of the application
     */
    public int getInstanceCount()
    {
        return instanceCount;
    }

    /**
     * Returns the concurrent instance count of the application.
     * @return The concurrent instance count of the application
     */
    public int getConcurrentInstanceCount()
    {
        return concurrentInstanceCount;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "ApplicationSummary [responseTime="+responseTime
            +", throughput="+throughput
            +", errorRate="+errorRate
            +", apdexTarget="+apdexTarget
            +", apdexScore="+apdexScore
            +", hostCount="+hostCount
            +", instanceCount="+instanceCount
            +", concurrentInstanceCount="+concurrentInstanceCount
            +"]";
    }
}