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
import com.opsmatters.newrelic.api.model.NamedIdResource;

/**
 * Represents the base class for all New Relic alert conditions.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public abstract class BaseCondition extends NamedIdResource
{
    private Boolean enabled;

    @SerializedName("runbook_url")
    private String runbookUrl;
    
    /**
     * Default constructor.
     */
    public BaseCondition()
    {
    }
    
    /**
     * Set to <CODE>true</CODE> if the alert condition is enabled.
     * @param enabled <CODE>true</CODE> if the alert condition is enabled
     */
    public void setEnabled(Boolean enabled)
    {
        this.enabled = enabled;
    }

    /**
     * Returns <CODE>true</CODE> if the alert condition is enabled.
     * @return <CODE>true</CODE> if the alert condition is enabled
     */
    public Boolean getEnabled()
    {
        return enabled;
    }

    /**
     * Sets the runbook URL of the alert condition.
     * @param runbookUrl The runbook URL of the alert condition
     */
    public void setRunbookUrl(String runbookUrl)
    {
        this.runbookUrl = runbookUrl;
    }

    /**
     * Returns the runbook URL of the alert condition.
     * @return The runbook URL of the alert condition
     */
    public String getRunbookUrl()
    {
        return runbookUrl;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return super.toString()
            +", enabled="+enabled
            +", runbookUrl="+runbookUrl;
    }

    /**
     * Builder to make condition construction easier.
     */
    protected abstract static class Builder<T extends BaseCondition, B extends Builder<T,B>>
    {
        private BaseCondition condition;

        /**
         * Sets the alert condition.
         * @param condition The alert condition
         * @return This object
         */
        public B condition(BaseCondition condition)
        {
            this.condition = condition;
            return self();
        }

        /**
         * Sets the id of the alert condition.
         * @param id The id of the alert condition
         * @return This object
         */
        public B id(long id)
        {
            condition.setId(id);
            return self();
        }

        /**
         * Sets the name of the alert condition.
         * @param name The name of the alert condition
         * @return This object
         */
        public B name(String name)
        {
            condition.setName(name);
            return self();
        }

        /**
         * Set to <CODE>true</CODE> if the alert condition is enabled.
         * @param enabled <CODE>true</CODE> if the alert condition is enabled
         * @return This object
         */
        public B enabled(boolean enabled)
        {
            condition.setEnabled(enabled);
            return self();
        }

        /**
         * Returns this object.
         * @return This object
         */
        protected abstract B self();

        /**
         * Returns the configured alert condition instance
         * @return The alert condition instance
         */
        public abstract T build();
    }
}