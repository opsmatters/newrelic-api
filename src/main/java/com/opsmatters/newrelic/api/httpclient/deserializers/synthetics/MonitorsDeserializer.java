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

package com.opsmatters.newrelic.api.httpclient.deserializers.synthetics;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Type;
import com.google.gson.*;
import com.opsmatters.newrelic.api.model.synthetics.Monitor;
import com.opsmatters.newrelic.api.model.synthetics.SimpleMonitor;
import com.opsmatters.newrelic.api.model.synthetics.BrowserMonitor;
import com.opsmatters.newrelic.api.model.synthetics.ScriptBrowserMonitor;
import com.opsmatters.newrelic.api.model.synthetics.ScriptApiMonitor;

/**
 * Deserializer class for Synthetics monitors.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class MonitorsDeserializer implements JsonDeserializer<Collection<Monitor>>
{
    private static Gson gson = new Gson();

    /**
     * Gson invokes this call-back method during deserialization when it encounters a field of the specified type.
     * @param element The Json data being deserialized
     * @param type The type of the Object to deserialize to 
     * @param context The JSON deserialization context
     * @return The monitors 
     */
    @Override
    public Collection<Monitor> deserialize(JsonElement element, Type type, JsonDeserializationContext context)
        throws JsonParseException
    {
        JsonObject obj = element.getAsJsonObject();
        JsonArray monitors = obj.getAsJsonArray("monitors");
        List<Monitor> values = new ArrayList<Monitor>();
        if(monitors != null && monitors.isJsonArray())
        {
            for(JsonElement monitor : monitors)
            {
                if(monitor.isJsonObject())
                {
                    JsonElement monitorType = monitor.getAsJsonObject().get("type");
                    if(monitorType != null)
                    {
                        switch(Monitor.MonitorType.valueOf(monitorType.getAsString()))
                        {
                            case SIMPLE:
                                values.add(gson.fromJson(monitor, SimpleMonitor.class));
                                break;
                            case BROWSER:
                                values.add(gson.fromJson(monitor, BrowserMonitor.class));
                                break;
                            case SCRIPT_BROWSER:
                                values.add(gson.fromJson(monitor, ScriptBrowserMonitor.class));
                                break;
                            case SCRIPT_API:
                                values.add(gson.fromJson(monitor, ScriptApiMonitor.class));
                                break;
                        }
                    }
                }
            }
        }
        return values;
    }
}
