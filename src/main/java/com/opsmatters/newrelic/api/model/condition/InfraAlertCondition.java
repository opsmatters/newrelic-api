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

package com.opsmatters.newrelic.api.model.condition;

import com.opsmatters.newrelic.api.model.NamedEntity;

/**
 * Represents a New Relic Infrastructure alert condition.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class InfraAlertCondition extends NamedEntity
{
    private String type;
    private Boolean enabled;
    private String where_clause;
    private CriticalThreshold critical_threshold;
    private Long policy_id;
    private Long created_at_epoch_millis;
    private Long updated_at_epoch_millis;

    /**
     * Default constructor.
     */
    public InfraAlertCondition()
    {
    }
    
    /**
     * Constructor that takes a name.
     * @param name The name of the alert condition
     */
    public InfraAlertCondition(String name)
    {
        setName(name);
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
     * Sets the type of the alert condition.
     * @param type The type of the alert condition
     */
    public void setType(InfraAlertConditionType type)
    {
        setType(type.value());
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
     * Set to <CODE>true</CODE> if the alert condition is enabled.
     * @param enabled <CODE>true</CODE> if the alert condition is enabled
     */
    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }

    /**
     * Returns <CODE>true</CODE> if the alert condition is enabled.
     * @return <CODE>true</CODE> if the alert condition is enabled
     */
    public boolean getEnabled()
    {
        return enabled;
    }

    /**
     * Sets the where clause of the alert condition.
     * @param where_clause The where clause of the alert condition
     */
    public void setWhereClause(String where_clause)
    {
        this.where_clause = where_clause;
    }

    /**
     * Returns the where clause of the alert condition.
     * @return The where clause of the alert condition
     */
    public String getWhereClause()
    {
        return where_clause;
    }

    /**
     * Sets the policy id of the alert condition.
     * @param policy_id The policy id of the alert condition
     */
    public void setPolicyId(long policy_id)
    {
        this.policy_id = policy_id;
    }

    /**
     * Returns the policy id of the alert condition.
     * @return The policy id of the alert condition
     */
    public long getPolicyId()
    {
        return policy_id;
    }

    /**
     * Sets the critical threshold of the alert condition.
     * @param critical_threshold The critical threshold of the alert condition
     */
    public void setCriticalThreshold(CriticalThreshold critical_threshold)
    {
        this.critical_threshold = critical_threshold;
    }

    /**
     * Returns the critical threshold of the alert condition.
     * @return The critical threshold of the alert condition
     */
    public CriticalThreshold getCriticalThreshold()
    {
        return critical_threshold;
    }

    /**
     * Returns the date the object was created.
     * @return The date the object was created
     */
    public long getCreatedAtEpochMillis()
    {
        return created_at_epoch_millis;
    }

    /**
     * Returns the date the object was last updated.
     * @return The date the object was last updated
     */
    public long getUpdatedAtEpochMillis()
    {
        return updated_at_epoch_millis;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "InfraAlertCondition ["+super.toString()
            +", type="+type
            +", enabled="+enabled
            +", policy_id="+policy_id
            +", where_clause="+where_clause
            +", critical_threshold="+critical_threshold
            +", created_at_epoch_millis="+created_at_epoch_millis
            +", updated_at_epoch_millis="+updated_at_epoch_millis
            +"]";
    }

    /**
     * Returns a builder for the alert condition.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make alert condition construction easier.
     */
    public static class Builder
    {
        private InfraAlertCondition condition = new InfraAlertCondition();

        /**
         * Sets the name of the alert condition.
         * @param name The name of the alert condition
         * @return This object
         */
        public Builder name(String name)
        {
            condition.setName(name);
            return this;
        }

        /**
         * Sets the type of the alert condition.
         * @param type The type of the alert condition
         * @return This object
         */
        public Builder type(String type)
        {
            condition.setType(type);
            return this;
        }

        /**
         * Sets the type of the alert condition to "infra_metric".
         * @return This object
         */
        public Builder metricType()
        {
            condition.setType(InfraAlertConditionType.METRIC);
            return this;
        }

        /**
         * Sets the type of the alert condition to "infra_process_running".
         * @return This object
         */
        public Builder processRunningType()
        {
            condition.setType(InfraAlertConditionType.PROCESS_RUNNING);
            return this;
        }

        /**
         * Sets the type of the alert condition to "infra_host_not_reporting".
         * @return This object
         */
        public Builder hostNotReportingType()
        {
            condition.setType(InfraAlertConditionType.HOST_NOT_REPORTING);
            return this;
        }

        /**
         * Set to <CODE>true</CODE> if the alert condition is enabled.
         * @param enabled <CODE>true</CODE> if the alert condition is enabled
         * @return This object
         */
        public Builder enabled(boolean enabled)
        {
            condition.setEnabled(enabled);
            return this;
        }

        /**
         * Sets the where clause of the alert condition.
         * @param where_clause The where clause of the alert condition
         * @return This object
         */
        public Builder whereClause(String where_clause)
        {
            condition.setWhereClause(where_clause);
            return this;
        }

        /**
         * Sets the policy id of the alert condition.
         * @param policy_id The policy id of the alert condition
         * @return This object
         */
        public Builder policyId(long policy_id)
        {
            condition.setPolicyId(policy_id);
            return this;
        }

        /**
         * Sets the critical threshold of the alert condition.
         * @param critical_threshold The critical threshold of the alert condition
         * @return This object
         */
        public Builder criticalThreshold(CriticalThreshold critical_threshold)
        {
            condition.setCriticalThreshold(critical_threshold);
            return this;
        }

        /**
         * Returns the configured alert condition instance
         * @return The alert condition instance
         */
        public InfraAlertCondition build()
        {
            return condition;
        }
    }
}