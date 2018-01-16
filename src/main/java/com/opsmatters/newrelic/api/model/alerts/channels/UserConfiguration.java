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
 * Represents a New Relic User channel configuration.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class UserConfiguration extends ChannelConfiguration
{
    /**
     * The type of the channel configuration.
     */
    public static final ChannelType TYPE = ChannelType.USER;

    @SerializedName("user_id")
    private String userId;

    /**
     * Default constructor.
     */
    public UserConfiguration()
    {
        super(TYPE.value());
    }
   
    /**
     * Sets the user id of the alerts.
     * @param userId The user id of the alerts
     */
    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    /**
     * Returns the user id of the alerts.
     * @return The userId of the alerts
     */
    public String getUserId()
    {
        return userId;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "UserConfiguration ["+super.toString()
            +", userId="+userId
            +"]";
    }
}