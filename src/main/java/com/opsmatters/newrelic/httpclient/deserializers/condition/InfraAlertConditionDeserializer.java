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

package com.opsmatters.newrelic.httpclient.deserializers.condition;

import java.lang.reflect.Type;
import com.google.gson.*;
import com.opsmatters.newrelic.api.model.condition.InfraAlertCondition;
import com.opsmatters.newrelic.api.model.condition.InfraMetricAlertCondition;
import com.opsmatters.newrelic.api.model.condition.InfraHostNotReportingAlertCondition;
import com.opsmatters.newrelic.api.model.condition.InfraProcessRunningAlertCondition;

/**
 * Deserializer class for infrastructure alert conditions.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class InfraAlertConditionDeserializer implements JsonDeserializer<InfraAlertCondition>
{
    private static Gson gson = new Gson();

    /**
     * Gson invokes this call-back method during deserialization when it encounters a field of the specified type.
     * @param element The Json data being deserialized
     * @param type The type of the Object to deserialize to 
     * @param context The JSON deserialization context
     * @return The alert condition 
     */
    @Override
    public InfraAlertCondition deserialize(JsonElement element, Type type, JsonDeserializationContext context)
        throws JsonParseException
    {
        JsonObject obj = element.getAsJsonObject();
        JsonElement condition = obj.get("data");
        if(condition != null && condition.isJsonObject())
        {
            JsonElement conditionType = condition.getAsJsonObject().get("type");
            if(conditionType != null)
            {
                switch(InfraAlertCondition.ConditionType.fromValue(conditionType.getAsString()))
                {
                    case METRIC:
                        return gson.fromJson(condition, InfraMetricAlertCondition.class);
                    case HOST_NOT_REPORTING:
                        return gson.fromJson(condition, InfraHostNotReportingAlertCondition.class);
                    case PROCESS_RUNNING:
                        return gson.fromJson(condition, InfraProcessRunningAlertCondition.class);
                }
            }
        }
        return null;
    }
}
