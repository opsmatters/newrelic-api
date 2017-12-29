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
 * Wrapper used to marshall a New Relic NRQL alert condition.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class NrqlAlertConditionWrapper
{
    private NrqlAlertCondition nrql_condition;
    
    /**
     * Constructor that takes an NRQL alert condition.
     * @param condition The alert condition
     */
    public NrqlAlertConditionWrapper(NrqlAlertCondition condition)
    {
        this.nrql_condition = condition;
    }

    /**
     * Returns the contents of the wrapper.
     * @return The alert condition
     */
    public NrqlAlertCondition getContents()
    {
        return nrql_condition;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "NrqlAlertConditionWrapper [nrql_condition="+nrql_condition+"]";
    }
}