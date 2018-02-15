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
import com.opsmatters.newrelic.api.model.insights.widgets.Presentation;
import com.opsmatters.newrelic.api.model.insights.widgets.ThresholdPresentation;
import com.opsmatters.newrelic.api.model.insights.widgets.DrilldownPresentation;
import com.opsmatters.newrelic.api.model.insights.widgets.TrafficLightPresentation;

/**
 * Deserializer class for insights widgets presentation.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class PresentationDeserializer implements JsonDeserializer<Presentation>
{
    private static Gson gson = new Gson();

    /**
     * Gson invokes this call-back method during deserialization when it encounters a field of the specified type.
     * @param element The Json data being deserialized
     * @param type The type of the Object to deserialize to 
     * @param context The JSON deserialization context
     * @return The widget presentation
     */
    @Override
    public Presentation deserialize(JsonElement element, Type type, JsonDeserializationContext context)
        throws JsonParseException
    {
        JsonObject obj = element.getAsJsonObject();

        // Check for a threshold presentation
        JsonElement threshold = obj.get("threshold");
        if(threshold != null)
            return gson.fromJson(obj, ThresholdPresentation.class);

        // Check for a drilldown presentation
        JsonElement drilldownDashboardId = obj.get("drilldown_dashboard_id");
        if(drilldownDashboardId != null)
            return gson.fromJson(obj, DrilldownPresentation.class);

        // Check for a traffic light presentation
        JsonElement trafficLights = obj.get("traffic_lights");
        if(trafficLights != null)
            return gson.fromJson(obj, TrafficLightPresentation.class);

        return gson.fromJson(obj, Presentation.class);
    }
}
