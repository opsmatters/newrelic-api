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

import java.util.List;
import java.util.ArrayList;

/**
 * Wrapper used to marshall a set of New Relic alert policies.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class AlertPoliciesWrapper
{
    private List<AlertPolicy> policies = new ArrayList<AlertPolicy>();
    
    /**
     * Default constructor.
     */
    public AlertPoliciesWrapper()
    {
    }

    /**
     * Returns the contents of the wrapper.
     * @return The set of alert policies
     */
    public List<AlertPolicy> getContents()
    {
        return policies;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "AlertPoliciesWrapper [policies="+policies+"]";
    }
}