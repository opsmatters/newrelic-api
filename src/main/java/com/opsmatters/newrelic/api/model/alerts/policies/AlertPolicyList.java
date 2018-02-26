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
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

/**
 * Adds lookup functions to a list of New Relic alert policies.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class AlertPolicyList
{
    private Map<String,AlertPolicy> names = new LinkedHashMap<String,AlertPolicy>();
    private Map<Long,AlertPolicy> ids = new LinkedHashMap<Long,AlertPolicy>();

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

    /**
     * Adds a list of alert policies.
     * @param policies The alert policies to add
     */
    public void add(List<AlertPolicy> policies)
    {
        for(AlertPolicy policy : policies)
        {
            names.put(policy.getName(), policy);
            if(policy.getId() != null)
                ids.put(policy.getId(), policy);
        }
    }

    /**
     * Returns the first alert policy for the given name.
     * @param name The name of the alert policy
     * @return The first alert policy for the given name
     */
    public AlertPolicy get(String name)
    {
        return names.get(name);
    }

    /**
     * Returns the alert policy for the given id.
     * @param id The id of the alert policy
     * @return The alert policy for the given id
     */
    public AlertPolicy get(long id)
    {
        return ids.get(id);
    }

    /**
     * Returns the number of alert policies.
     * @return The number of alert policies
     */
    public int size()
    {
        return ids.size();
    }

    /**
     * Returns the policies that match the given comma-separated list of policies.
     * @param str The comma-separated list of policies (including wildcards)
     * @return The policies that match the given list
     */
    public List<AlertPolicy> list(String str)
    {
        Map<String,AlertPolicy> map = new LinkedHashMap<String,AlertPolicy>();

        if(str == null || str.length() == 0) // Select all policies by default
            str = "%";
        String[] tokens = str.split(",");
        for(String token : tokens)
        {
            token = token.trim();
            if(token.length() > 0)
            {
                Pattern pattern = Pattern.compile(token.replace("?", ".?").replace("%", ".*?"));
                for(AlertPolicy policy : names.values())
                {
                   if(pattern.matcher(policy.getName()).matches())
                        map.put(policy.getName(), policy);
                }
            }
        }

        List<AlertPolicy> ret = new ArrayList<AlertPolicy>();
        ret.addAll(map.values());
        return ret;
    }

    /**
     * Returns the policies that match the given ids.
     * @param ids The list of the policy ids
     * @return The policies that match the given ids
     */
    public List<AlertPolicy> list(List<Long> ids)
    {
        List<AlertPolicy> ret = new ArrayList<AlertPolicy>();

        if(ids != null)
        {
            for(Long id : ids)
            {
                AlertPolicy policy = get(id);
                if(policy != null)
                    ret.add(policy);
            }
        }

        return ret;
    }
}