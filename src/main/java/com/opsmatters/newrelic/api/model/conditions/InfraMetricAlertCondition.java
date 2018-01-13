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
 * Represents a New Relic Infrastructure Metric alert condition.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class InfraMetricAlertCondition extends InfraAlertCondition
{
    /**
     * The type of the alert condition.
     */
    public static final ConditionType TYPE = ConditionType.METRIC;

    @SerializedName("event_type")
    private String eventType;

    private String comparison;

    @SerializedName("select_value")
    private String selectValue;

    @SerializedName("integration_provider")
    private String integrationProvider;

    /**
     * Represents an infrastructure alert event type.  
     */
    public enum EventType
    {
        SYSTEM("SystemSample"),
        STORAGE("StorageSample"),
        NETWORK("NetworkSample"),
        PROCESS("ProcessSample");

        EventType(String value)
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
    public InfraMetricAlertCondition()
    {
        setType(TYPE.value());
    }
    
    /**
     * Sets the event type of the alert condition.
     * @param eventType The event type of the alert condition
     */
    public void setEventType(String eventType)
    {
        this.eventType = eventType;
    }

    /**
     * Sets the event type of the alert condition.
     * @param eventType The event type of the alert condition
     */
    public void setEventType(EventType eventType)
    {
        setEventType(eventType.value());
    }

    /**
     * Returns the event type of the alert condition.
     * @return The event type of the alert condition
     */
    public String getEventType()
    {
        return eventType;
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
     * Sets the select value of the alert condition.
     * @param selectValue The select value of the alert condition
     */
    public void setSelectValue(String selectValue)
    {
        this.selectValue = selectValue;
    }

    /**
     * Returns the select value of the alert condition.
     * @return The select value of the alert condition
     */
    public String getSelectValue()
    {
        return selectValue;
    }

    /**
     * Sets the integration provider of the alert condition.
     * @param integrationProvider The integration provider of the alert condition
     */
    public void setIntegrationProvider(String integrationProvider)
    {
        this.integrationProvider = integrationProvider;
    }

    /**
     * Returns the integration provider of the alert condition.
     * @return The integration provider of the alert condition
     */
    public String getIntegrationProvider()
    {
        return integrationProvider;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "InfraMetricAlertCondition ["+super.toString()
            +", eventType="+eventType
            +", comparison="+comparison
            +", selectValue="+selectValue
            +", integrationProvider="+integrationProvider
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
    public static class Builder extends InfraAlertCondition.Builder<InfraMetricAlertCondition, Builder>
    {
        private InfraMetricAlertCondition condition = new InfraMetricAlertCondition();

        /**
         * Default constructor.
         */
        public Builder()
        {
            condition(condition);
        }

        /**
         * Sets the event type of the alert condition.
         * @param eventType The event type of the alert condition
         * @return This object
         */
        public Builder eventType(String eventType)
        {
            condition.setEventType(eventType);
            return this;
        }

        /**
         * Sets the event type of the alert condition to "SystemSample".
         * @return This object
         */
        public Builder systemEventType()
        {
            condition.setEventType(EventType.SYSTEM);
            return this;
        }

        /**
         * Sets the event type of the alert condition to "StorageSample".
         * @return This object
         */
        public Builder storageEventType()
        {
            condition.setEventType(EventType.STORAGE);
            return this;
        }

        /**
         * Sets the event type of the alert condition to "NetworkSample".
         * @return This object
         */
        public Builder networkEventType()
        {
            condition.setEventType(EventType.NETWORK);
            return this;
        }

        /**
         * Sets the event type of the alert condition to "ProcessSample".
         * @return This object
         */
        public Builder processEventType()
        {
            condition.setEventType(EventType.PROCESS);
            return this;
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
         * Sets the select value of the alert condition.
         * @param selectValue The select value of the alert condition
         * @return This object
         */
        public Builder selectValue(String selectValue)
        {
            condition.setSelectValue(selectValue);
            return this;
        }

        /**
         * Sets the integration provider of the alert condition.
         * @param integrationProvider The integration provider of the alert condition
         * @return This object
         */
        public Builder integrationProvider(String integrationProvider)
        {
            condition.setIntegrationProvider(integrationProvider);
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
        public InfraMetricAlertCondition build()
        {
            return condition;
        }
    }
}