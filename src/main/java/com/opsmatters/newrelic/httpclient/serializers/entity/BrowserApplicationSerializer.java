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

package com.opsmatters.newrelic.httpclient.serializers.entity;

import java.lang.reflect.Type;
import com.google.gson.*;
import com.opsmatters.newrelic.api.model.entity.BrowserApplication;

/**
 * Deserializer class for Browser applications.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class BrowserApplicationSerializer implements JsonSerializer<BrowserApplication>
{
    private static Gson gson = new Gson();

    /**
     * Gson invokes this call-back method during serialization when it encounters a field of the specified type.
     * @param application The application being serialized
     * @param type The type of the Object to deserialize to 
     * @param context The JSON serialization context
     * @return The JSON data that was serialized
     */
    @Override
    public JsonElement serialize(BrowserApplication application, Type type, JsonSerializationContext context)
    {
        JsonElement element = gson.toJsonTree(application, type);
        JsonObject obj = new JsonObject();
        obj.add("browser_application", element);
        return obj;
    }
}
