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
 * Represents a New Relic Synthetics alert condition.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class SyntheticsAlertCondition extends BaseCondition
{
    // The field names
    public static final String MONITOR_ID = "monitor_id";

    @SerializedName("monitor_id")
    private String monitorId;

    /**
     * Default constructor.
     */
    public SyntheticsAlertCondition()
    {
    }

    /**
     * Sets the monitor id of the alert condition.
     * @param monitorId The monitor id of the alert condition
     */
    public void setMonitorId(String monitorId)
    {
        this.monitorId = monitorId;
    }

    /**
     * Returns the monitor id of the alert condition.
     * @return The monitor id of the alert condition
     */
    public String getMonitorId()
    {
        return monitorId;
    }
    
    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "SyntheticsAlertCondition ["+super.toString()
            +", monitorId="+monitorId
            +"]";
    }

    /**
     * Returns a builder for the Synthetics alert condition.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make Synthetics alert condition construction easier.
     */
    public static class Builder extends BaseCondition.Builder<SyntheticsAlertCondition, Builder>
    {
        private SyntheticsAlertCondition condition = new SyntheticsAlertCondition();

        /**
         * Default constructor.
         */
        public Builder()
        {
            condition(condition);
        }

        /**
         * Sets the monitor id of the alert condition.
         * @param monitorId The monitor id of the alert condition
         * @return This object
         */
        public Builder monitorId(String monitorId)
        {
            condition.setMonitorId(monitorId);
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
        public SyntheticsAlertCondition build()
        {
            return condition;
        }
    }
}