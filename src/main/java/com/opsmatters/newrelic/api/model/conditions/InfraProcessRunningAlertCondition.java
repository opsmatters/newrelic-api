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

package com.opsmatters.newrelic.api.model.conditions;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a New Relic Infrastructure Process Not Running alert condition.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class InfraProcessRunningAlertCondition extends InfraAlertCondition
{
    /**
     * The type of the alert condition.
     */
    public static final ConditionType TYPE = ConditionType.PROCESS_RUNNING;

    private String comparison;

    @SerializedName("process_where_clause")
    private String processWhereClause;

    /**
     * Default constructor.
     */
    public InfraProcessRunningAlertCondition()
    {
        setType(TYPE.value());
    }
    
    /**
     * Sets the comparison of the alert condition.
     * @param comparison The comparison of the alert condition
     */
    public void setComparison(String comparison)
    {
        this.comparison = comparison;
    }

    /**
     * Sets the comparison of the alert condition.
     * @param comparison The comparison of the alert condition
     */
    public void setComparison(Operator comparison)
    {
        setComparison(comparison.value());
    }

    /**
     * Returns the comparison of the alert condition.
     * @return The comparison of the alert condition
     */
    public String getComparison()
    {
        return comparison;
    }

    /**
     * Sets the process where clause of the alert condition.
     * @param processWhereClause The process where clause of the alert condition
     */
    public void setProcessWhereClause(String processWhereClause)
    {
        this.processWhereClause = processWhereClause;
    }

    /**
     * Returns the process where clause of the alert condition.
     * @return The process where clause of the alert condition
     */
    public String getProcessWhereClause()
    {
        return processWhereClause;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "InfraProcessRunningAlertCondition ["+super.toString()
            +", comparison="+comparison
            +", processWhereClause="+processWhereClause
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
    public static class Builder extends InfraAlertCondition.Builder<InfraProcessRunningAlertCondition, Builder>
    {
        private InfraProcessRunningAlertCondition condition = new InfraProcessRunningAlertCondition();

        /**
         * Default constructor.
         */
        public Builder()
        {
            condition(condition);
        }

        /**
         * Sets the comparison of the alert condition.
         * @param comparison The comparison of the alert condition
         * @return This object
         */
        public Builder comparison(String comparison)
        {
            condition.setComparison(comparison);
            return this;
        }

        /**
         * Sets the comparison of the alert condition to "above".
         * @return This object
         */
        public Builder aboveComparison()
        {
            condition.setComparison(Operator.ABOVE);
            return this;
        }

        /**
         * Sets the comparison of the alert condition to "below".
         * @return This object
         */
        public Builder belowComparison()
        {
            condition.setComparison(Operator.BELOW);
            return this;
        }

        /**
         * Sets the comparison of the alert condition to "equal".
         * @return This object
         */
        public Builder equalComparison()
        {
            condition.setComparison(Operator.EQUAL);
            return this;
        }

        /**
         * Sets the process where clause of the alert condition.
         * @param processWhereClause The process where clause of the alert condition
         * @return This object
         */
        public Builder processWhereClause(String processWhereClause)
        {
            condition.setProcessWhereClause(processWhereClause);
            return this;
        }

        /**
         * Returns this object.
         * @return This object
         */
        @Override
        protected Builder self()
        {
            return this;
        }

        /**
         * Returns the configured alert condition instance
         * @return The alert condition instance
         */
        @Override
        public InfraProcessRunningAlertCondition build()
        {
            return condition;
        }
    }
}