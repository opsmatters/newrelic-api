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

import com.google.gson.annotations.SerializedName;
import com.opsmatters.newrelic.api.model.NamedResource;
import com.opsmatters.newrelic.api.model.entities.AlertEntity;

/**
 * Represents a New Relic alert violation.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class AlertViolation extends NamedResource
{
    @SerializedName("label")
    private String label;

    private Integer duration;

    @SerializedName("policy_name")
    private String policyName;

    @SerializedName("condition_name")
    private String conditionName;

    private String priority;

    @SerializedName("opened_at")
    private Long openedAt;

    @SerializedName("closed_at")
    private Long closedAt;

    private AlertEntity entity;

    private ViolationLinks links;

    /**
     * Default constructor.
     */
    public AlertViolation()
    {
    }

    /**
     * Sets the label of the violation.
     * @param label The label of the violation
     */
    public void setLabel(String label)
    {
        this.label = label;
    }

    /**
     * Returns the label of the violation.
     * @return The label of the violation
     */
    public String getLabel()
    {
        return label;
    }

    /**
     * Sets the duration of the violation.
     * @param duration The duration of the violation
     */
    public void setDuration(int duration)
    {
        this.duration = duration;
    }

    /**
     * Returns the duration of the violation.
     * @return The duration of the violation
     */
    public int getDuration()
    {
        return duration;
    }

    /**
     * Sets the policy name of the violation.
     * @param policyName The policy name of the violation
     */
    public void setPolicyName(String policyName)
    {
        this.policyName = policyName;
    }

    /**
     * Returns the policy name of the violation.
     * @return The policy name of the violation
     */
    public String getPolicyName()
    {
        return policyName;
    }

    /**
     * Sets the condition name of the violation.
     * @param conditionName The condition name of the violation
     */
    public void setConditionName(String conditionName)
    {
        this.conditionName = conditionName;
    }

    /**
     * Returns the condition name of the violation.
     * @return The condition name of the violation
     */
    public String getConditionName()
    {
        return conditionName;
    }

    /**
     * Sets the priority of the violation.
     * @param priority The priority of the violation
     */
    public void setPriority(String priority)
    {
        this.priority = priority;
    }

    /**
     * Sets the priority of the violation.
     * @param priority The priority of the violation
     */
    public void setPriority(Priority priority)
    {
        setPriority(priority.value());
    }

    /**
     * Returns the priority of the violation.
     * @return The priority of the violation
     */
    public String getPriority()
    {
        return priority;
    }
   
    /**
     * Returns the date the violation was opened.
     * @return The date the violation was opened
     */
    public long getOpenedAt()
    {
        return openedAt;
    }

    /**
     * Returns the date the violation was closed.
     * @return The date the violation was closed
     */
    public long getClosedAt()
    {
        return closedAt;
    }

    /**
     * Returns the entity of the violation.
     * @return The entity of the violation
     */
    public AlertEntity getEntity()
    {
        return entity;
    }

    /**
     * Returns the links of the violation.
     * @return The links of the violation
     */
    public ViolationLinks getLinks()
    {
        return links;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "AlertViolation ["+super.toString()
            +", label="+label
            +", duration="+duration
            +", policyName="+policyName
            +", conditionName="+conditionName
            +", priority="+priority
            +", openedAt="+openedAt
            +", closedAt="+closedAt
            +", entity="+entity
            +", links="+links
            +"]";
    }
}