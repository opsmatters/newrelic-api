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
 * Represents a New Relic alert condition.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public abstract class AlertCondition extends MetricCondition
{
    private String type;

    @SerializedName("gc_metric")
    private String gcMetric;

    @SerializedName("condition_scope")
    private String conditionScope;

    @SerializedName("violation_close_timer")
    private Integer violationCloseTimer;

    @SerializedName("user_defined")
    private UserDefined userDefined;

    /**
     * Represents the available types for this condition.  
     */
    public enum ConditionType
    {
        APM_APP("apm_app_metric"),
        APM_KEY_TRANSACTION("apm_kt_metric"),
        APM_JVM("apm_jvm_metric"),
        SERVERS("servers_metric"),
        BROWSER("browser_metric"),
        MOBILE("mobile_metric");

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
     * Represents a condition scope.  
     */
    public enum ConditionScope
    {
        INSTANCE("instance"),
        APPLICATION("application");

        ConditionScope(String value)
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
     * Represents a violation close timer duration (in hours).  
     */
    public enum ViolationCloseTimerInterval
    {
        HOURS_1(1),
        HOURS_2(2),
        HOURS_4(4),
        HOURS_8(8),
        HOURS_12(24),
        HOURS_24(24);

        ViolationCloseTimerInterval(int value)
        {
            this.value = value;
        }

        public int value()
        {
            return value;
        }

        private int value;
    }

    /**
     * Default constructor.
     */
    public AlertCondition()
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
     * Sets the gc metric of the alert condition.
     * @param gcMetric The gc metric of the alert condition
     */
    public void setGcMetric(String gcMetric)
    {
        this.gcMetric = gcMetric;
    }

    /**
     * Returns the gc metric of the alert condition.
     * @return The gc metric of the alert condition
     */
    public String getGcMetric()
    {
        return gcMetric;
    }

    /**
     * Sets the condition scope of the alert condition.
     * @param conditionScope The condition scope of the alert condition
     */
    public void setConditionScope(String conditionScope)
    {
        this.conditionScope = conditionScope;
    }

    /**
     * Sets the condition scope of the alert condition.
     * @param conditionScope The condition scope of the alert condition
     */
    public void setConditionScope(ConditionScope conditionScope)
    {
        setConditionScope(conditionScope.value());
    }

    /**
     * Returns the condition scope of the alert condition.
     * @return The condition scope of the alert condition
     */
    public String getConditionScope()
    {
        return conditionScope;
    }

    /**
     * Sets the violation close timer of the alert condition (in hours).
     * @param violationCloseTimer The violation close timer of the alert condition
     */
    public void setViolationCloseTimer(int violationCloseTimer)
    {
        this.violationCloseTimer = violationCloseTimer;
    }

    /**
     * Sets the violation close timer of the alert condition (in hours).
     * @param violationCloseTimer The violation close timer of the alert condition
     */
    public void setViolationCloseTimer(ViolationCloseTimerInterval violationCloseTimer)
    {
        setViolationCloseTimer(violationCloseTimer.value());
    }

    /**
     * Returns the violation close timer of the alert condition (in hours).
     * @return The violation close timer of the alert condition
     */
    public int getViolationCloseTimer()
    {
        return violationCloseTimer;
    }

    /**
     * Sets the user defined of the alert condition.
     * @param userDefined The user defined of the alert condition
     */
    public void setUserDefined(UserDefined userDefined)
    {
        this.userDefined = userDefined;
    }

    /**
     * Returns the user defined of the alert condition.
     * @return The user defined of the alert condition
     */
    public UserDefined getUserDefined()
    {
        return userDefined;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return super.toString()
            +", type="+type
            +", gcMetric="+gcMetric
            +", conditionScope="+conditionScope
            +", violationCloseTimer="+violationCloseTimer
            +", userDefined="+userDefined;
    }

    /**
     * Builder to make alert condition construction easier.
     */
    protected abstract static class Builder<T extends AlertCondition, B extends Builder<T,B>>
        extends MetricCondition.Builder<T,B>
    {
        private AlertCondition condition;

        /**
         * Sets the alert condition.
         * @param condition The alert condition
         * @return This object
         */
        public B condition(AlertCondition condition)
        {
            this.condition = condition;
            super.condition(condition);
            return self();
        }

        /**
         * Sets the gc metric of the alert condition.
         * @param gcMetric The gc metric of the alert condition
         * @return This object
         */
        public B gcMetric(String gcMetric)
        {
            condition.setGcMetric(gcMetric);
            return self();
        }

        /**
         * Sets the condition scope of the alert condition.
         * @param conditionScope The condition scope of the alert condition
         * @return This object
         */
        public B conditionScope(String conditionScope)
        {
            condition.setConditionScope(conditionScope);
            return self();
        }

        /**
         * Sets the condition scope of the alert condition to "instance".
         * @return This object
         */
        public B instanceConditionScope()
        {
            condition.setConditionScope(ConditionScope.INSTANCE);
            return self();
        }

        /**
         * Sets the condition scope of the alert condition to "application".
         * @return This object
         */
        public B applicationConditionScope()
        {
            condition.setConditionScope(ConditionScope.APPLICATION);
            return self();
        }

        /**
         * Sets the violation close timer of the alert condition (in hours).
         * @param violationCloseTimer The violation close timer of the alert condition
         * @return This object
         */
        public B violationCloseTimer(int violationCloseTimer)
        {
            condition.setViolationCloseTimer(violationCloseTimer);
            return self();
        }

        /**
         * Sets the violation close timer of the alert condition (in hours).
         * @param violationCloseTimer The violation close timer of the alert condition
         * @return This object
         */
        public B violationCloseTimer(ViolationCloseTimerInterval violationCloseTimer)
        {
            condition.setViolationCloseTimer(violationCloseTimer);
            return self();
        }
    }
}