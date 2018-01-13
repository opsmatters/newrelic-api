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

/**
 * Represents a New Relic Mobile alert condition.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class MobileAlertCondition extends AlertCondition
{
    /**
     * The type of the alert condition.
     */
    public static final ConditionType TYPE = ConditionType.MOBILE;

    /**
     * Represents the metric types for this condition type.  
     */
    public enum Metric
    {
        DATABASE("database"),
        IMAGES("images"),
        JSON("json"),
        NETWORK("network"),
        VIEW_LOADING("view_loading"),
        NETWORK_ERROR_PERCENTAGE("network_error_percentage"),
        STATUS_ERROR_PERCENTAGE("status_error_percentage"),
        MOBILE_CRASH_RATE("mobile_crash_rate"),
        USER_DEFINED("user_defined");

        Metric(String value)
        {
            this.value = value;
        }

        public String value()
        {
            return value;
        }

        private String value;
    }

    /**
     * Default constructor.
     */
    public MobileAlertCondition()
    {
        setType(TYPE.value());
    }
    
    /**
     * Sets the metric of the alert condition.
     * @param metric The metric of the alert condition
     */
    public void setMetric(Metric metric)
    {
        setMetric(metric.value());
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "MobileAlertCondition ["+super.toString()+"]";
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
    public static class Builder extends AlertCondition.Builder<MobileAlertCondition, Builder>
    {
        private MobileAlertCondition condition = new MobileAlertCondition();

        /**
         * Default constructor.
         */
        public Builder()
        {
            condition(condition);
        }

        /**
         * Sets the metric of the alert condition.
         * @param metric The metric of the alert condition
         * @return This object
         */
        public Builder metric(Metric metric)
        {
            condition.setMetric(metric);
            return this;
        }

        /**
         * Sets the user defined of the alert condition.
         * @param userDefined The user defined of the alert condition
         * @return This object
         */
        public Builder userDefined(UserDefined userDefined)
        {
            condition.setUserDefined(userDefined);
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
        public MobileAlertCondition build()
        {
            return condition;
        }
    }
}