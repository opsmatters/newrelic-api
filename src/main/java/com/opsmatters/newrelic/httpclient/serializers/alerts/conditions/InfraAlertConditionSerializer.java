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

package com.opsmatters.newrelic.httpclient.serializers.alerts.conditions;

import java.lang.reflect.Type;
import com.google.gson.*;
import com.opsmatters.newrelic.api.model.alerts.conditions.InfraAlertCondition;

/**
 * Serializer class for infrastructure alert conditions.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class InfraAlertConditionSerializer implements JsonSerializer<InfraAlertCondition>
{
    private static Gson gson = new Gson();

    /**
     * Gson invokes this call-back method during serialization when it encounters a field of the specified type.
     * @param condition The alert condition being serialized
     * @param type The type of the Object to deserialize to 
     * @param context The JSON serialization context
     * @return The JSON data that was serialized
     */
    @Override
    public JsonElement serialize(InfraAlertCondition condition, Type type, JsonSerializationContext context)
    {
        JsonElement element = gson.toJsonTree(condition, type);
        JsonObject obj = new JsonObject();
        obj.add("data", element);
        return obj;
    }
}
