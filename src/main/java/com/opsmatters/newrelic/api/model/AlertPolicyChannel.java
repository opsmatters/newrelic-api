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
 * Represents a New Relic alert policy channel.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class AlertPolicyChannel extends NamedEntity
{
    private List<Long> channel_ids = new ArrayList<Long>();

    /**
     * Default constructor.
     */
    public AlertPolicyChannel()
    {
    }
   
    /**
     * Returns the list of channel ids for the policy.
     * @return The list of channel ids
     */
    public List<Long> getChannelIds()
    {
        return channel_ids;
    }

    /**
     * Returns the array of channel ids for the policy.
     * @return The array of channel ids
     */
    public long[] getChannelIdArray()
    {
        long[] ret = new long[channel_ids.size()];
        for(int i = 0; i < channel_ids.size(); i++)
            ret[i] = channel_ids.get(i);
        return ret;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "AlertPolicyChannel ["+super.toString()
            +", channel_ids="+channel_ids
            +"]";
    }
}