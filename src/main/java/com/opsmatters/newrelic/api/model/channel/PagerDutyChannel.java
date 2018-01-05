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

/**
 * Represents a New Relic PagerDuty alert channel.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class PagerDutyChannel extends AlertChannel
{
    /**
     * Default constructor.
     */
    public PagerDutyChannel()
    {
        this(new PagerDutyConfiguration());
    }

    /**
     * Constructor that takes a configuration.
     * @param configuration The alert channel configuration
     */
    public PagerDutyChannel(PagerDutyConfiguration configuration)
    {
        setConfiguration(configuration);
        setType(configuration.getType());
    }
   
    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "PagerDutyChannel ["+super.toString()+"]";
    }

    /**
     * Returns a builder for the PagerDuty channel.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make PagerDuty channel construction easier.
     */
    public static class Builder extends AlertChannel.Builder<PagerDutyChannel, Builder>
    {
        private PagerDutyConfiguration configuration = new PagerDutyConfiguration();
        private PagerDutyChannel channel = new PagerDutyChannel(configuration);

        /**
         * Default constructor.
         */
        public Builder()
        {
            channel(channel);
        }

        /**
         * Sets the service key of the PagerDuty alerts.
         * @param service_key The service key of the alerts
         * @return This object
         */
        public Builder serviceKey(String service_key)
        {
            configuration.setServiceKey(service_key);
            return this;
        }

        /**
         * Returns this object.
         * @return This object
         */
        @Override
        protected Builder self()
        {
            return this;
        }

        /**
         * Returns the configured PagerDuty channel instance
         * @return The PagerDuty channel instance
         */
        public PagerDutyChannel build()
        {
            return channel;
        }
    }
}