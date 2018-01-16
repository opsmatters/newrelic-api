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

/**
 * Represents a New Relic Mobile external service alert condition.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class MobileExternalServiceAlertCondition extends ExternalServiceAlertCondition
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
        RESPONSE_TIME_AVERAGE("response_time_average"),
        RESPONSE_TIME_MINIMUM("response_time_minimum"),
        RESPONSE_TIME_MAXIMUM("response_time_maximum"),
        THROUGHPUT("throughput"),
        NETWORK_FAILURE_PERCENTAGE("network_failure_percentage"),
        HTTP_STATUS_ERROR_PERCENTAGE("http_status_error_percentage");

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
    public MobileExternalServiceAlertCondition()
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
        return "MobileExternalServiceAlertCondition ["+super.toString()+"]";
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
    public static class Builder extends ExternalServiceAlertCondition.Builder<MobileExternalServiceAlertCondition, Builder>
    {
        private MobileExternalServiceAlertCondition condition = new MobileExternalServiceAlertCondition();

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
        public MobileExternalServiceAlertCondition build()
        {
            return condition;
        }
    }
}