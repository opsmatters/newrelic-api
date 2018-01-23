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

package com.opsmatters.newrelic.httpclient.deserializers.insights.widgets;

import java.lang.reflect.Type;
import com.google.gson.*;
import com.opsmatters.newrelic.api.model.insights.widgets.Widget;
import com.opsmatters.newrelic.api.model.insights.widgets.EventChart;
import com.opsmatters.newrelic.api.model.insights.widgets.BreakdownMetricChart;
import com.opsmatters.newrelic.api.model.insights.widgets.FacetChart;
import com.opsmatters.newrelic.api.model.insights.widgets.Inventory;
import com.opsmatters.newrelic.api.model.insights.widgets.Markdown;
import com.opsmatters.newrelic.api.model.insights.widgets.MetricLineChart;
import com.opsmatters.newrelic.api.model.insights.widgets.ThresholdEventChart;
import com.opsmatters.newrelic.api.model.insights.widgets.TrafficLightChart;
import com.opsmatters.newrelic.api.model.insights.widgets.WidgetData;
import com.opsmatters.newrelic.api.model.insights.widgets.Presentation;
import com.opsmatters.newrelic.httpclient.deserializers.insights.widgets.WidgetDataDeserializer;
import com.opsmatters.newrelic.httpclient.deserializers.insights.widgets.PresentationDeserializer;

/**
 * Deserializer class for insights widgets.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class WidgetDeserializer implements JsonDeserializer<Widget>
{
    private static Gson gson =  new GsonBuilder()
        .registerTypeAdapter(WidgetData.class, new WidgetDataDeserializer())
        .registerTypeAdapter(Presentation.class, new PresentationDeserializer())
        .create();

    /**
     * Gson invokes this call-back method during deserialization when it encounters a field of the specified type.
     * @param element The Json data being deserialized
     * @param type The type of the Object to deserialize to 
     * @param context The JSON deserialization context
     * @return The widget
     */
    @Override
    public Widget deserialize(JsonElement element, Type type, JsonDeserializationContext context)
        throws JsonParseException
    {
        JsonObject obj = element.getAsJsonObject();
        String visualization = obj.get("visualization").getAsString();
        if(visualization != null)
        {
            if(EventChart.Visualization.contains(visualization))
                return gson.fromJson(obj, EventChart.class);
            else if(BreakdownMetricChart.Visualization.contains(visualization))
                return gson.fromJson(obj, BreakdownMetricChart.class);
            else if(FacetChart.Visualization.contains(visualization))
                return gson.fromJson(obj, FacetChart.class);
            else if(Inventory.Visualization.contains(visualization))
                return gson.fromJson(obj, Inventory.class);
            else if(Markdown.Visualization.contains(visualization))
                return gson.fromJson(obj, Markdown.class);
            else if(MetricLineChart.Visualization.contains(visualization))
                return gson.fromJson(obj, MetricLineChart.class);
            else if(ThresholdEventChart.Visualization.contains(visualization))
                return gson.fromJson(obj, ThresholdEventChart.class);
            else if(TrafficLightChart.Visualization.contains(visualization))
                return gson.fromJson(obj, TrafficLightChart.class);
        }
        return null;
    }
}
