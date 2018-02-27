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

package com.opsmatters.newrelic.api.model.alerts.policies;

import java.util.List;
import com.opsmatters.newrelic.api.util.ResourceList;

/**
 * Adds lookup functions to a list of New Relic alert policies.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class AlertPolicyList extends ResourceList<AlertPolicy>
{
    /**
     * Default constructor.
     */
    public AlertPolicyList()
    {
    }

    /**
     * Constructor that takes a list of alert policies.
     * @param policies The alert policies for the list
     */
    public AlertPolicyList(List<AlertPolicy> policies)
    {
        add(policies);
    }
}