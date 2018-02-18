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
 * Represents a New Relic external service alert condition.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public abstract class ExternalServiceAlertCondition extends MetricCondition
{
    // The field names
    public static final String TYPE = "type";
    public static final String EXTERNAL_SERVICE_URL = "external_service_url";

    private String type;

    @SerializedName("external_service_url")
    private String externalServiceUrl;

    /**
     * Represents the available types for this condition.  
     */
    public enum ConditionType
    {
        APM("apm_external_service"),
        MOBILE("mobile_external_service");

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

        /**
         * Returns <CODE>true</CODE> if the given value is contained in the list of types.
         * @param value The type value
         * @return <CODE>true</CODE> if the given value is contained in the list of types
         */
        public static boolean contains(String value)
        {
            return fromValue(value) != null;
        }

        private String value;
    }

    /**
     * Default constructor.
     */
    public ExternalServiceAlertCondition()
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
     * Sets the external service URL of the alert condition.
     * @param externalServiceUrl The external service URL of the alert condition
     */
    public void setExternalServiceUrl(String externalServiceUrl)
    {
        this.externalServiceUrl = externalServiceUrl;
    }

    /**
     * Returns the external service URL of the alert condition.
     * @return The external service URL of the alert condition
     */
    public String getExternalServiceUrl()
    {
        return externalServiceUrl;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return super.toString()
            +", type="+type
            +", externalServiceUrl="+externalServiceUrl;
    }

    /**
     * Builder to make alert condition construction easier.
     */
    protected abstract static class Builder<T extends ExternalServiceAlertCondition, B extends Builder<T,B>>
        extends MetricCondition.Builder<T,B>
    {
        private ExternalServiceAlertCondition condition;

        /**
         * Sets the alert condition.
         * @param condition The alert condition
         * @return This object
         */
        public B condition(ExternalServiceAlertCondition condition)
        {
            this.condition = condition;
            super.condition(condition);
            return self();
        }

        /**
         * Sets the external service URL of the alert condition.
         * @param externalServiceUrl The external service URL of the alert condition
         * @return This object
         */
        public B externalServiceUrl(String externalServiceUrl)
        {
            condition.setExternalServiceUrl(externalServiceUrl);
            return self();
        }
    }
}