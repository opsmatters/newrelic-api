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

package com.opsmatters.newrelic.api.model.channels;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a New Relic HipChat channel configuration.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class HipChatConfiguration extends ChannelConfiguration
{
    /**
     * The type of the channel configuration.
     */
    public static final ChannelType TYPE = ChannelType.HIPCHAT;

    @SerializedName("auth_token")
    private String authToken;

    @SerializedName("room_id")
    private String roomId;

    /**
     * Default constructor.
     */
    public HipChatConfiguration()
    {
        super(TYPE.value());
    }
   
    /**
     * Sets the HipChat room id for the alerts.
     * @param roomId The room id for the alerts
     */
    public void setRoomId(String roomId)
    {
        this.roomId = roomId;
    }

    /**
     * Returns the HipChat room id for the alerts.
     * @return The room id for the alerts
     */
    public String getRoomId()
    {
        return roomId;
    }

    /**
     * Sets the HipChat auth token for the alerts.
     * @param authToken The auth token for the alerts
     */
    public void setAuthToken(String authToken)
    {
        this.authToken = authToken;
    }

    /**
     * Returns the HipChat auth token for the alerts.
     * @return The auth token for the alerts
     */
    public String getAuthToken()
    {
        return authToken;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "HipChatConfiguration ["+super.toString()
            +", roomId="+roomId
            +", authToken="+authToken
            +"]";
    }
}