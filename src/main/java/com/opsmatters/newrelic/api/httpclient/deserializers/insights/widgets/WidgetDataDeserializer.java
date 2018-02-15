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

package com.opsmatters.newrelic.api.httpclient.deserializers.insights.widgets;

import java.lang.reflect.Type;
import com.google.gson.*;
import com.opsmatters.newrelic.api.model.insights.widgets.WidgetData;
import com.opsmatters.newrelic.api.model.insights.widgets.EventsData;
import com.opsmatters.newrelic.api.model.insights.widgets.MetricsData;
import com.opsmatters.newrelic.api.model.insights.widgets.MarkdownData;
import com.opsmatters.newrelic.api.model.insights.widgets.InventoryData;

/**
 * Deserializer class for insights widgets data.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class WidgetDataDeserializer implements JsonDeserializer<WidgetData>
{
    private static Gson gson = new Gson();

    /**
     * Gson invokes this call-back method during deserialization when it encounters a field of the specified type.
     * @param element The Json data being deserialized
     * @param type The type of the Object to deserialize to 
     * @param context The JSON deserialization context
     * @return The widget data
     */
    @Override
    public WidgetData deserialize(JsonElement element, Type type, JsonDeserializationContext context)
        throws JsonParseException
    {
        JsonObject obj = element.getAsJsonObject();

        // Check for an event data source
        JsonElement nrql = obj.get("nrql");
        if(nrql != null)
            return gson.fromJson(obj, EventsData.class);

        // Check for a metric data source
        JsonElement duration = obj.get("duration");
        if(duration != null)
            return gson.fromJson(obj, MetricsData.class);

        // Check for a markdown data source
        JsonElement source = obj.get("source");
        if(source != null)
            return gson.fromJson(obj, MarkdownData.class);

        // Check for an inventory data source
        JsonElement sources = obj.get("sources");
        if(sources != null)
            return gson.fromJson(obj, InventoryData.class);

        return null;
    }
}
