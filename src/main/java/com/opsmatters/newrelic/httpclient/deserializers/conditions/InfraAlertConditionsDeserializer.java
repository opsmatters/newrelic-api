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

package com.opsmatters.newrelic.httpclient.deserializers.conditions;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Type;
import com.google.gson.*;
import com.opsmatters.newrelic.api.model.conditions.InfraAlertCondition;
import com.opsmatters.newrelic.api.model.conditions.InfraMetricAlertCondition;
import com.opsmatters.newrelic.api.model.conditions.InfraHostNotReportingAlertCondition;
import com.opsmatters.newrelic.api.model.conditions.InfraProcessRunningAlertCondition;

/**
 * Deserializer class for infrastructure alert conditions.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class InfraAlertConditionsDeserializer implements JsonDeserializer<Collection<InfraAlertCondition>>
{
    private static Gson gson = new Gson();

    /**
     * Gson invokes this call-back method during deserialization when it encounters a field of the specified type.
     * @param element The Json data being deserialized
     * @param type The type of the Object to deserialize to 
     * @param context The JSON deserialization context
     * @return The alert conditions 
     */
    @Override
    public Collection<InfraAlertCondition> deserialize(JsonElement element, Type type, JsonDeserializationContext context)
        throws JsonParseException
    {
        JsonObject obj = element.getAsJsonObject();
        JsonArray conditions = obj.getAsJsonArray("data");
        List<InfraAlertCondition> values = new ArrayList<InfraAlertCondition>();
        if(conditions != null && conditions.isJsonArray())
        {
            for(JsonElement condition : conditions)
            {
                if(condition.isJsonObject())
                {
                    JsonElement conditionType = condition.getAsJsonObject().get("type");
                    if(conditionType != null)
                    {
                        switch(InfraAlertCondition.ConditionType.fromValue(conditionType.getAsString()))
                        {
                            case METRIC:
                                values.add(gson.fromJson(condition, InfraMetricAlertCondition.class));
                            case HOST_NOT_REPORTING:
                                values.add(gson.fromJson(condition, InfraHostNotReportingAlertCondition.class));
                            case PROCESS_RUNNING:
                                values.add(gson.fromJson(condition, InfraProcessRunningAlertCondition.class));
                        }
                    }
                }
            }
        }
        return values;
    }
}
