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

package com.opsmatters.newrelic.httpclient.deserializers.channel;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Type;
import com.google.gson.*;
import com.opsmatters.newrelic.api.model.channel.AlertChannel;
import com.opsmatters.newrelic.api.model.channel.ChannelType;
import com.opsmatters.newrelic.api.model.channel.UserChannel;
import com.opsmatters.newrelic.api.model.channel.EmailChannel;
import com.opsmatters.newrelic.api.model.channel.SlackChannel;
import com.opsmatters.newrelic.api.model.channel.HipChatChannel;
import com.opsmatters.newrelic.api.model.channel.OpsGenieChannel;
import com.opsmatters.newrelic.api.model.channel.VictorOpsChannel;
import com.opsmatters.newrelic.api.model.channel.PagerDutyChannel;
import com.opsmatters.newrelic.api.model.channel.CampfireChannel;
import com.opsmatters.newrelic.api.model.channel.xMattersChannel;
import com.opsmatters.newrelic.api.model.channel.WebhookChannel;

/**
 * Deserializer class for alert channels.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class AlertChannelsDeserializer implements JsonDeserializer<Collection<AlertChannel>>
{
    private static Gson gson = new Gson();

    /**
     * Gson invokes this call-back method during deserialization when it encounters a field of the specified type.
     * @param element The Json data being deserialized
     * @param type The type of the Object to deserialize to 
     * @param context The JSON deserialization context
     * @return The alert channels 
     */
    @Override
    public Collection<AlertChannel> deserialize(JsonElement element, Type type, JsonDeserializationContext context)
        throws JsonParseException
    {
        JsonObject obj = element.getAsJsonObject();
        JsonArray channels = obj.getAsJsonArray("channels");
        List<AlertChannel> values = new ArrayList<AlertChannel>();
        if(channels != null && channels.isJsonArray())
        {
            for(JsonElement channel : channels)
            {
                if(channel.isJsonObject())
                {
                    JsonElement channelType = channel.getAsJsonObject().get("type");
                    if(channelType != null)
                    {
                        switch(ChannelType.fromValue(channelType.getAsString()))
                        {
                            case USER:
                                values.add(gson.fromJson(channel, UserChannel.class));
                            case EMAIL:
                                values.add(gson.fromJson(channel, EmailChannel.class));
                                break;
                            case SLACK:
                                values.add(gson.fromJson(channel, SlackChannel.class));
                                break;
                            case HIPCHAT:
                                values.add(gson.fromJson(channel, HipChatChannel.class));
                                break;
                            case OPSGENIE:
                                values.add(gson.fromJson(channel, OpsGenieChannel.class));
                                break;
                            case VICTOROPS:
                                values.add(gson.fromJson(channel, VictorOpsChannel.class));
                                break;
                            case PAGERDUTY:
                                values.add(gson.fromJson(channel, PagerDutyChannel.class));
                                break;
                            case CAMPFIRE:
                                values.add(gson.fromJson(channel, CampfireChannel.class));
                                break;
                            case XMATTERS:
                                values.add(gson.fromJson(channel, xMattersChannel.class));
                                break;
                            case WEBHOOK:
                                values.add(gson.fromJson(channel, WebhookChannel.class));
                                break;
                        }
                    }
                }
            }
        }
        return values;
    }
}
