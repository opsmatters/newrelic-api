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

package com.opsmatters.newrelic.api.model.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a New Relic end user summary.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class EndUserSummary
{
    @SerializedName("response_time")
    private Float responseTime;

    private Float throughput;

    @SerializedName("apdex_target")
    private Float apdexTarget;

    @SerializedName("apdex_score")
    private Float apdexScore;

    /**
     * Default constructor.
     */
    public EndUserSummary()
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
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "EndUserSummary [responseTime="+responseTime
            +", throughput="+throughput
            +", apdexTarget="+apdexTarget
            +", apdexScore="+apdexScore
            +"]";
    }
}