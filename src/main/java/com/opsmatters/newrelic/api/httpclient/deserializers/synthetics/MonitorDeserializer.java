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
public class MonitorDeserializer implements JsonDeserializer<Monitor>
{
    private static Gson gson = new Gson();

    /**
     * Gson invokes this call-back method during deserialization when it encounters a field of the specified type.
     * @param element The Json data being deserialized
     * @param type The type of the Object to deserialize to 
     * @param context The JSON deserialization context
     * @return The monitor 
     */
    @Override
    public Monitor deserialize(JsonElement element, Type type, JsonDeserializationContext context)
        throws JsonParseException
    {
        JsonObject obj = element.getAsJsonObject();
        JsonElement monitorType = obj.get("type");
        if(monitorType != null)
        {
            switch(Monitor.MonitorType.valueOf(monitorType.getAsString()))
            {
                case SIMPLE:
                    return gson.fromJson(obj, SimpleMonitor.class);
                case BROWSER:
                    return gson.fromJson(obj, BrowserMonitor.class);
                case SCRIPT_BROWSER:
                    return gson.fromJson(obj, ScriptBrowserMonitor.class);
                case SCRIPT_API:
                    return gson.fromJson(obj, ScriptApiMonitor.class);
            }
        }
        return null;
    }
}
