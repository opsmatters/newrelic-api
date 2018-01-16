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

package com.opsmatters.newrelic.api.model.alerts.channels;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a New Relic PagerDuty channel configuration.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class PagerDutyConfiguration extends ChannelConfiguration
{
    /**
     * The type of the channel configuration.
     */
    public static final ChannelType TYPE = ChannelType.PAGERDUTY;

    @SerializedName("service_key")
    private String serviceKey;

    /**
     * Default constructor.
     */
    public PagerDutyConfiguration()
    {
        super(TYPE.value());
    }
   
    /**
     * Sets the service key for the alerts.
     * @param serviceKey The service key for the alerts
     */
    public void setServiceKey(String serviceKey)
    {
        this.serviceKey = serviceKey;
    }

    /**
     * Returns the service key for the alerts.
     * @return The service key for the alerts
     */
    public String getServiceKey()
    {
        return serviceKey;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "PagerDutyConfiguration ["+super.toString()
            +", serviceKey="+serviceKey
            +"]";
    }
}