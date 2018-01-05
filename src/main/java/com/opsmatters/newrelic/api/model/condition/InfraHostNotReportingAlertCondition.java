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

import com.google.gson.annotations.SerializedName;

/**
 * Represents a New Relic Infrastructure Host Not Reporting alert condition.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class InfraHostNotReportingAlertCondition extends InfraAlertCondition
{
    /**
     * The type of the alert condition.
     */
    public static final ConditionType TYPE = ConditionType.HOST_NOT_REPORTING;

    /**
     * Default constructor.
     */
    public InfraHostNotReportingAlertCondition()
    {
        setType(TYPE.value());
    }
    
    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "InfraHostNotReportingAlertCondition ["+super.toString()+"]";
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
    public static class Builder extends InfraAlertCondition.Builder<InfraHostNotReportingAlertCondition, Builder>
    {
        private InfraHostNotReportingAlertCondition condition = new InfraHostNotReportingAlertCondition();

        /**
         * Default constructor.
         */
        public Builder()
        {
            condition(condition);
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
        public InfraHostNotReportingAlertCondition build()
        {
            return condition;
        }
    }
}