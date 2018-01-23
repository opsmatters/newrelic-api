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

package com.opsmatters.newrelic.api.model.alerts.conditions;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a New Relic Infrastructure alert condition.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public abstract class InfraAlertCondition extends BaseCondition
{
    private String type;

    @SerializedName("where_clause")
    private String whereClause;

    private String filter;

    @SerializedName("critical_threshold")
    private AlertThreshold criticalThreshold;

    @SerializedName("warning_threshold")
    private AlertThreshold warningThreshold;

    @SerializedName("policy_id")
    private Long policyId;

    @SerializedName("created_at_epoch_millis")
    private Long createdAtEpochMillis;

    @SerializedName("updated_at_epoch_millis")
    private Long updatedAtEpochMillis;

    /**
     * Represents the available types for this condition.  
     */
    public enum ConditionType
    {
        HOST_NOT_REPORTING("infra_host_not_reporting"),
        PROCESS_RUNNING("infra_process_running"),
        METRIC("infra_metric");

        ConditionType(String value)
        {
            this.value = value;
        }

        public String value()
        {
            return value;
        }

        /**
         * Returns the type for the given value.
         * @param value The type value
         * @return The type for the given value
         */
        public static ConditionType fromValue(String value)
        {
            ConditionType[] types = values();
            for(ConditionType type : types)
            {
                if(type.value().equals(value))
                    return type;
            }
            return null;
        }

        private String value;
    }

    /**
     * Default constructor.
     */
    public InfraAlertCondition()
    {
    }
    
    /**
     * Sets the type of the alert condition.
     * @param type The type of the alert condition
     */
    public void setType(String type)
    {
        this.type = type;
    }

    /**
     * Returns the type of the alert condition.
     * @return The type of the alert condition
     */
    public String getType()
    {
        return type;
    }

    /**
     * Sets the where clause of the alert condition.
     * @param whereClause The where clause of the alert condition
     */
    public void setWhereClause(String whereClause)
    {
        this.whereClause = whereClause;
    }

    /**
     * Returns the where clause of the alert condition.
     * @return The where clause of the alert condition
     */
    public String getWhereClause()
    {
        return whereClause;
    }

    /**
     * Sets the filter of the alert condition.
     * @param filter The filter of the alert condition
     */
    public void setFilter(String filter)
    {
        this.filter = filter;
    }

    /**
     * Returns the filter of the alert condition.
     * @return The filter of the alert condition
     */
    public String getFilter()
    {
        return filter;
    }

    /**
     * Sets the policy id of the alert condition.
     * @param policyId The policy id of the alert condition
     */
    public void setPolicyId(long policyId)
    {
        this.policyId = policyId;
    }

    /**
     * Returns the policy id of the alert condition.
     * @return The policy id of the alert condition
     */
    public long getPolicyId()
    {
        return policyId;
    }

    /**
     * Sets the critical threshold of the alert condition.
     * @param criticalThreshold The critical threshold of the alert condition
     */
    public void setCriticalThreshold(AlertThreshold criticalThreshold)
    {
        this.criticalThreshold = criticalThreshold;
    }

    /**
     * Returns the critical threshold of the alert condition.
     * @return The critical threshold of the alert condition
     */
    public AlertThreshold getCriticalThreshold()
    {
        return criticalThreshold;
    }

    /**
     * Sets the warning threshold of the alert condition.
     * @param warningThreshold The warning threshold of the alert condition
     */
    public void setWarningThreshold(AlertThreshold warningThreshold)
    {
        this.warningThreshold = warningThreshold;
    }

    /**
     * Returns the warning threshold of the alert condition.
     * @return The warning threshold of the alert condition
     */
    public AlertThreshold getWarningThreshold()
    {
        return warningThreshold;
    }

    /**
     * Returns the date the object was created.
     * @return The date the object was created
     */
    public long getCreatedAtEpochMillis()
    {
        return createdAtEpochMillis;
    }

    /**
     * Returns the date the object was last updated.
     * @return The date the object was last updated
     */
    public long getUpdatedAtEpochMillis()
    {
        return updatedAtEpochMillis;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return super.toString()
            +", type="+type
            +", policyId="+policyId
            +", whereClause="+whereClause
            +", filter="+filter
            +", criticalThreshold="+criticalThreshold
            +", warningThreshold="+warningThreshold
            +", createdAtEpochMillis="+createdAtEpochMillis
            +", updatedAtEpochMillis="+updatedAtEpochMillis;
    }

    /**
     * Builder to make infra alert condition construction easier.
     */
    protected abstract static class Builder<T extends InfraAlertCondition, B extends Builder<T,B>>
        extends BaseCondition.Builder<T,B>
    {
        private InfraAlertCondition condition;

        /**
         * Sets the alert condition.
         * @param condition The alert condition
         * @return This object
         */
        public B condition(InfraAlertCondition condition)
        {
            this.condition = condition;
            super.condition(condition);
            return self();
        }

        /**
         * Sets the where clause of the alert condition.
         * @param whereClause The where clause of the alert condition
         * @return This object
         */
        public B whereClause(String whereClause)
        {
            condition.setWhereClause(whereClause);
            return self();
        }

        /**
         * Sets the filter of the alert condition.
         * @param filter The filter of the alert condition
         * @return This object
         */
        public B filter(String filter)
        {
            condition.setFilter(filter);
            return self();
        }

        /**
         * Sets the policy id of the alert condition.
         * @param policyId The policy id of the alert condition
         * @return This object
         */
        public B policyId(long policyId)
        {
            condition.setPolicyId(policyId);
            return self();
        }

        /**
         * Sets the critical threshold of the alert condition.
         * @param criticalThreshold The critical threshold of the alert condition
         * @return This object
         */
        public B criticalThreshold(AlertThreshold criticalThreshold)
        {
            condition.setCriticalThreshold(criticalThreshold);
            return self();
        }

        /**
         * Sets the warning threshold of the alert condition.
         * @param warningThreshold The warning threshold of the alert condition
         * @return This object
         */
        public B warningThreshold(AlertThreshold warningThreshold)
        {
            condition.setWarningThreshold(warningThreshold);
            return self();
        }
    }
}