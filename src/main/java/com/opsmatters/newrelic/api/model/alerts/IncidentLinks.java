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

package com.opsmatters.newrelic.api.model.alerts;

import java.util.List;
import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;

/**
 * Represents a set of New Relic incident links.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class IncidentLinks
{
    @SerializedName("policy_id")
    private Long policyId;

    private List<Long> violations = new ArrayList<Long>();
    
    /**
     * Default constructor.
     */
    public IncidentLinks()
    {
    }

    /**
     * Sets the policy id of the incident.
     * @param policyId The policy id of the incident
     */
    public void setPolicyId(Long policyId)
    {
        this.policyId = policyId;
    }

    /**
     * Returns the policy id of the incident.
     * @return The policy id of the incident
     */
    public Long getPolicyId()
    {
        return policyId;
    }

    /**
     * Sets the list of violations.
     * @param violations The list of violations
     */
    public void setViolations(List<Long> violations)
    {
        this.violations.clear();
        this.violations.addAll(violations);
    }

    /**
     * Returns the list of violations.
     * @return The list of violations
     */
    public List<Long> getViolations()
    {
        return violations;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "IncidentLinks [policyId="+policyId
            +", violations="+violations
            +"]";
    }
}