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

package com.opsmatters.newrelic.api.model;

/**
 * Represents a New Relic alert condition.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public abstract class BaseCondition extends BaseObject
{
    private Boolean enabled;
    private String runbook_url;
    
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
     * Sets the runbook URL of the alert condition.
     * @param runbook_url The runbook URL of the alert condition
     */
    public void setRunbookUrl(String runbook_url)
    {
        this.runbook_url = runbook_url;
    }

    /**
     * Returns the runbook URL of the alert condition.
     * @return The runbook URL of the alert condition
     */
    public String getRunbookUrl()
    {
        return runbook_url;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return super.toString()
            +", enabled="+enabled
            +", runbook_url="+runbook_url;
    }
}