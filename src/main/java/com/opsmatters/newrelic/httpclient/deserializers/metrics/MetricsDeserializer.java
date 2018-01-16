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

package com.opsmatters.newrelic.httpclient.deserializers.metrics;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Type;
import com.google.gson.*;
import com.opsmatters.newrelic.api.model.metrics.Metric;

/**
 * Deserializer class for metrics.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class MetricsDeserializer implements JsonDeserializer<Collection<Metric>>
{
    private static Gson gson = new Gson();

    /**
     * Gson invokes this call-back method during deserialization when it encounters a field of the specified type.
     * @param element The Json data being deserialized
     * @param type The type of the Object to deserialize to 
     * @param context The JSON deserialization context
     * @return The metrics 
     */
    @Override
    public Collection<Metric> deserialize(JsonElement element, Type type, JsonDeserializationContext context)
        throws JsonParseException
    {
        JsonObject obj = element.getAsJsonObject();
        JsonArray metrics = obj.getAsJsonArray("metrics");
        List<Metric> values = new ArrayList<Metric>();
        if(metrics != null && metrics.isJsonArray())
        {
            for(JsonElement metric : metrics)
                values.add(gson.fromJson(metric, Metric.class));
        }
        return values;
    }
}
