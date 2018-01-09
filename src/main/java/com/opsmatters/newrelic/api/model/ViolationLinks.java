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

package com.opsmatters.newrelic.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a set of New Relic violation links.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class ViolationLinks
{
    @SerializedName("policy_id")
    private Long policyId;

    @SerializedName("condition_id")
    private Long conditionId;
    
    /**
     * Default constructor.
     */
    public ViolationLinks()
    {
    }

    /**
     * Sets the policy id of the violation.
     * @param policyId The policy id of the violation
     */
    public void setPolicyId(long policyId)
    {
        this.policyId = policyId;
    }

    /**
     * Returns the policy id of the violation.
     * @return The policy id of the violation
     */
    public long getPolicyId()
    {
        return policyId;
    }

    /**
     * Sets the condition id of the violation.
     * @param conditionId The condition id of the violation
     */
    public void setConditionId(long conditionId)
    {
        this.conditionId = conditionId;
    }

    /**
     * Returns the condition id of the violation.
     * @return The condition id of the violation
     */
    public long getConditionId()
    {
        return conditionId;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "ViolationLinks [policyId="+policyId
            +", conditionId="+conditionId
            +"]";
    }
}