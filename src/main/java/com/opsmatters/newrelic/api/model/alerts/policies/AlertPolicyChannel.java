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
import com.google.gson.annotations.SerializedName;
import com.opsmatters.newrelic.api.model.IdResource;

/**
 * Represents a New Relic alert policy channel.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class AlertPolicyChannel extends IdResource
{
    // The field names
    public static final String CHANNELS = "channels";
    public static final String CHANNEL_IDS = "channel_ids";

    @SerializedName("channel_ids")
    private List<Long> channelIds = new ArrayList<Long>();

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
        return channelIds;
    }

    /**
     * Sets the list of channel ids for the policy.
     * @param channelIds The list of channel ids
     */
    public void setChannelIds(List<Long> channelIds)
    {
        this.channelIds = channelIds;
    }

    /**
     * Returns the array of channel ids for the policy.
     * @return The array of channel ids
     */
    public long[] getChannelIdArray()
    {
        long[] ret = new long[channelIds.size()];
        for(int i = 0; i < channelIds.size(); i++)
            ret[i] = channelIds.get(i);
        return ret;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "AlertPolicyChannel ["+super.toString()
            +", channelIds="+channelIds
            +"]";
    }

    /**
     * Returns a builder for the alert policy channel.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make alert policy channel construction easier.
     */
    public static class Builder
    {
        private AlertPolicyChannel policyChannel = new AlertPolicyChannel();

        /**
         * Sets the id of the alert policy channel.
         * @param id The id of the alert policy channel
         * @return This object
         */
        public Builder id(long id)
        {
            policyChannel.setId(id);
            return this;
        }

        /**
         * Sets the channel ids of the alert policy channel.
         * @param channelIds The channel ids of the alert policy channel
         * @return This object
         */
        public Builder channelIds(List<Long> channelIds)
        {
            policyChannel.setChannelIds(channelIds);
            return this;
        }

        /**
         * Returns the configured alert policy channel instance
         * @return The alert policy channel instance
         */
        public AlertPolicyChannel build()
        {
            return policyChannel;
        }
    }
}