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

package com.opsmatters.newrelic.api.model.channel;

import java.util.List;
import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;

/**
 * Represents a set of New Relic channel links.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class ChannelLinks
{
    @SerializedName("policy_ids")
    private List<Integer> policyIds = new ArrayList<Integer>();
    
    /**
     * Default constructor.
     */
    public ChannelLinks()
    {
    }

    /**
     * Sets the list of policy ids.
     * @param policyIds The list of policy ids
     */
    public void setPolicyIds(List<Integer> policyIds)
    {
        this.policyIds.clear();
        this.policyIds.addAll(policyIds);
    }

    /**
     * Returns the list of policy ids.
     * @return The list of policy ids
     */
    public List<Integer> getPolicyIds()
    {
        return policyIds;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "ChannelLinks [policyIds="+policyIds+"]";
    }
}